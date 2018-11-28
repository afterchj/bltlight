package com;

import com.alibaba.fastjson.JSON;
import com.tpadsz.after.config.SpringConfig;
import com.tpadsz.after.dao80.TbkBindDao;
import com.tpadsz.after.entity.Person;
import com.tpadsz.after.entity.Pid;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by hongjian.chen on 2018/11/26.
 */

@ContextConfiguration(classes = {SpringConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigurationTest {

    @Autowired
    private XMemcachedClient xMemcachedClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    @Test
    public void testXmemcached() throws InterruptedException, MemcachedException, TimeoutException {
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setName("after");
        person.setAddress("苏州");
        person.setAge(24);
        list.add(person);
        Person person1 = new Person();
        person1.setName("admin");
        person1.setAddress("苏州");
        person1.setAge(25);
        list.add(person1);
        Person person2 = new Person();
        person2.setName("test");
        person2.setAddress("苏州");
        person2.setAge(23);
        list.add(person2);
//        xMemcachedClient.set("person1", 24 * 60 * 60, person);
//        xMemcachedClient.set("people", 24 * 60 * 60, list);
        List<Person> people = xMemcachedClient.get("people");
        for (Person p : people) {
            System.out.println(p.toJsonString());
        }
        Person p = xMemcachedClient.get("person1");
        System.out.println("person=" + p.toJsonString());
        // set: 第一个参数是key，第二个参数是超时时间，第三个参数是value
        xMemcachedClient.set("first", 3, "tianjin");//添加或者更新
        xMemcachedClient.set("second", 2, "chengdu");//添加,key不存在添加成功返回true,否则返回false
        xMemcachedClient.replace("first", 3, "Beijing");//替换,key已经存在替换成功返回true,不存在返回false
        System.out.println("first=======================" + xMemcachedClient.get("first"));
        System.out.println("second======================" + xMemcachedClient.get("second"));
        System.out.println("--------------------------------------------------------------");

        Thread.sleep(5000);
        System.out.println("first========================" + xMemcachedClient.get("first"));
        System.out.println("second=======================" + xMemcachedClient.get("second"));
    }

    @Test
    public void set() {
//		redisTemplate.opsForValue().set("mykey","test is ok");
        redisTemplate.opsForValue().set("mykey", "Test is ok!", 10, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("mykey"));
        try {
            new Thread().sleep(10000);
            System.out.println(redisTemplate.opsForValue().get("mykey"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObject() throws InterruptedException {
        Person person = new Person();
        person.setName("after");
        person.setAddress("苏州");
        person.setAge(24);
        Person person2 = new Person();
        person2.setName("test");
        person2.setAddress("苏州");
        person2.setAge(23);
        redisTemplate.opsForValue().set("person1", person, 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("person2", person2);
        System.out.println(redisTemplate.hasKey("person1") + "\t" + redisTemplate.hasKey("person2"));
        Person p1 = (Person) redisTemplate.opsForValue().get("person1");
        Person p2 = (Person) redisTemplate.opsForValue().get("person2");
        System.out.println("p2=" + p2.toJsonString());
        System.out.println("p1=" + p1.toJsonString());

//        System.out.println( redisTemplate.expire("person1", 10, TimeUnit.SECONDS));
        System.out.println("-----------------分隔线-----------------");
        new Thread().sleep(10000);
        System.out.println(redisTemplate.hasKey("person1") + "\t" + redisTemplate.hasKey("person2"));
        System.out.println("p2=" + redisTemplate.opsForValue().get("person2"));
        System.out.println("p1=" + redisTemplate.opsForValue().get("person1"));
    }

    @Test
    public void testHash() {
        Person person = new Person();
        person.setName("after");
        person.setAddress("苏州");
        person.setAge(24);
        Person person1 = new Person();
        person1.setName("admin");
        person1.setAddress("苏州");
        person1.setAge(25);
        Person person2 = new Person();
        person2.setName("test");
        person2.setAddress("苏州");
        person2.setAge(23);
        Map<String, Person> people = new HashMap();
        people.put("key1", person);
        people.put("key2", person1);
        people.put("key3", person2);

        List list = new ArrayList();
        for (String key : people.keySet()) {
            list.add(key);
        }

        System.out.println(redisTemplate.opsForHash().get("testMap", "pid"));
        List<Object> list1 = redisTemplate.opsForHash().multiGet("testMap", list);
        System.out.println(JSON.toJSONString(list1));

    }

    public String getValue(String uid) {
        Map map = new HashMap();
        map.put("uid", uid);
        map.put("is_used", false);
        String key = formatKey(uid);
        String adzone_id = (String) redisTemplate.opsForValue().get(key);
        if (uid == null) {
            sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
        }
        return adzone_id;
    }

    public String formatKey(String uid) {
        return String.format("pid_%s", uid);
    }

    @Test
    public void testSetKey() throws InterruptedException {
        String adzone_id = "54298700349";
        String uid = "9de2725281b44136b04e474d85061151";

        String key = formatKey(uid);
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        operations.set(key, adzone_id, 3, TimeUnit.SECONDS);
        System.out.println(redisTemplate.hasKey(key) + "\t" + getValue(adzone_id));
        System.out.println("-----------------分隔线-----------------");
//        Thread.sleep(3000);
//        System.out.println(redisTemplate.hasKey(key) + "\t" + getValue(adzone_id));
        System.out.println("uid=" + getValue(adzone_id));
    }


    @Test
    public void testBind() {
        Pid pid = sessionTemplate.getMapper(TbkBindDao.class).getPidInfo();
        String adzone_id = pid.getAdzone_id();
        System.out.println("adzone_id="+adzone_id);
        String uid = "e03bb58e3b834c739065bd6648017926";
        String key = formatKey(uid);
        Map map = new HashMap();
        map.put("pkey", pid.getPkey());
        map.put("is_used", true);
        map.put("uid", uid);
        map.put("last_bind_uid", uid);
        map.put("adzone_id", adzone_id);
        redisTemplate.opsForValue().set(key, adzone_id, 3, TimeUnit.SECONDS);
        sessionTemplate.getMapper(TbkBindDao.class).bindPid(map);
        sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
//        List<Pid> list = sessionTemplate.selectList("tbk.getPids");
        System.out.println(JSON.toJSONString(pid));
//        sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);

    }
}

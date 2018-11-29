package com;

import com.alibaba.fastjson.JSON;
import com.tpadsz.after.config.DBConfig;
import com.tpadsz.after.config.SpringConfig;
import com.tpadsz.after.dao80.TbkBindDao;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.Person;
import com.tpadsz.after.entity.Pid;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by hongjian.chen on 2018/11/26.
 */

@ContextConfiguration(classes = {SpringConfig.class, DBConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigurationTest {

    @Autowired
    private XMemcachedClient xMemcachedClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Autowired
//    private SqlSessionTemplate sessionTemplate;

    @Resource(name = "mySqlSessionTemplate")
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

    public String getValue(String adzone_id) {
        Map map = new HashMap();
        map.put("adzone_id", adzone_id);
        map.put("is_used", false);
        String key = formatKey(adzone_id);
        String uid = (String) redisTemplate.opsForValue().get(key);
        if (adzone_id == null) {
            sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
        }
        return uid;
    }

    public String formatKey(String adzone_id) {
        return String.format("pid_%s", adzone_id);
    }

    @Test
    public void testSetKey() throws InterruptedException {
        String adzone_id = "54298700349";
        String uid = "5bc9f45ab42e453f93ee8a966b5a9726";

        String key = formatKey(uid);
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        operations.set(key, adzone_id, 3, TimeUnit.SECONDS);
        System.out.println(redisTemplate.hasKey(key) + "\t" + getValue(uid));
        System.out.println("-----------------分隔线-----------------");
//        Thread.sleep(3000);
        System.out.println(redisTemplate.hasKey(key) + "\t" + getValue(uid));
    }


    @Test
    public void testBind() {
        Pid pid = sessionTemplate.getMapper(TbkBindDao.class).getPidInfo();
        String adzone_id = pid.getAdzone_id();
        System.out.println("adzone_id=" + adzone_id);
        String uid = "f0433a087dbb4b19967cc6b8da2e0558";
        String key = formatKey(adzone_id);
        Map map = new HashMap();
        map.put("pkey", pid.getPkey());
        map.put("is_used", true);
        map.put("uid", uid);
        map.put("last_bind_uid", uid);
        map.put("adzone_id", adzone_id);
        redisTemplate.opsForValue().set(key, uid, 3, TimeUnit.SECONDS);
        sessionTemplate.getMapper(TbkBindDao.class).bindPid(map);
        sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
//        List<Pid> list = sessionTemplate.selectList("tbk.getPids");
        System.out.println(JSON.toJSONString(pid));
//        sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
    }

    @Test
    public void testMybatisTemplate() {
        Pid pid = sessionTemplate.selectOne("com.tpadsz.after.dao80.TbkBindDao.getPidInfo");
        System.out.println("pid=" + sessionTemplate.getMapper(TbkBindDao.class).getPid("f0433a087dbb4b19967cc6b8da2e0558"));
        System.out.println(pid.getPid());
    }

    @Test
    public void testOrder() throws ParseException {
        java.util.Date create_time = new SimpleDateFormat("yyyy-MM-DD " +
                "HH:mm:ss").parse("2018-10-25 19:31:33");
        java.util.Date earning_time = new SimpleDateFormat("yyyy-MM-DD " +
                "HH:mm:ss").parse("2018-10-31 20:26:21");

        OrderFrom orderFrom = new OrderFrom(238125004584662714L,
                569310566675L, "29274312", "8696607", "487812dasdasdasdad",
                3, create_time, earning_time, "16.8000", "25.20", "16.80", 1,
                "0.0045", "0.0800", null, "得力展翔专卖店",
                "得力便利贴学生用便签贴纸创意儿童n次贴百事贴记事贴便条纸便签本可撕便利贴大号彩色记号贴批发包邮", "");
//        Gson gson = new Gson();
//        String src = gson.toJson(orderFrom);
//        ValueOperations<String, Object> stringObjectValueOperations =
//                redisTemplate.opsForValue();
        redisTemplate.opsForHash().put("orderFroms", orderFrom.getTrade_id(),
                orderFrom);
        OrderFrom orderFrom1 = (OrderFrom) redisTemplate.opsForHash().get
                ("orderFroms", 238125004584662717L);
        if (orderFrom1 == null) {
            System.out.println("aaaa");
        }
//        stringObjectValueOperations.set("orderFroms",orderFrom);
//        OrderFrom orderFrom1 = (OrderFrom) stringObjectValueOperations.get
// ("orderFroms");
//        System.out.println(orderFrom1.toString());
    }

    @Test
    public void test11(){
        redisTemplate.opsForValue().set(formatKey("29274312"), "487812dasdasdasdad", 3, TimeUnit.DAYS);
//        redisTemplate.opsForValue().set(formatKey("29274313"), "487812dasdasdasdad", 3, TimeUnit.DAYS);
//        System.out.println(redisTemplate.opsForValue().get(String.format("pid_%s", "29274313")));
    }
}

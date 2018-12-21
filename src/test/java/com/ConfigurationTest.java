package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.config.DBConfig;
import com.tpadsz.after.config.SpringConfig;
import com.tpadsz.after.dao80.TbkBindDao;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.Person;
import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.entity.dd.CommonParam;
import com.tpadsz.after.util.TaoBaoUtil;
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
        List<Person> people = new ArrayList<>();
        Person person = new Person();
        person.setName("after");
        person.setAddress("苏州");
        person.setAge(24);
        people.add(person);
        Person person1 = new Person();
        person1.setName("admin");
        person1.setAddress("苏州");
        person1.setAge(25);
        people.add(person1);
        Person person2 = new Person();
        person2.setName("test");
        person2.setAddress("苏州");
        person2.setAge(23);
        people.add(person2);
        xMemcachedClient.set("person1", 24 * 60 * 60, person1);
//        xMemcachedClient.set("people", 24 * 60 * 60, people);
        List<Person> list = xMemcachedClient.get("people");
        for (Person p : list) {
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
//		  redisTemplate.opsForValue().set("mykey","test is ok");
//        redisTemplate.opsForValue().set("mykey", "LogTest is ok!", 10, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get(formatKey("54307050006")));

//            new Thread().sleep(10000);
        System.out.println(redisTemplate.opsForValue().get(formatKey("54300050120")));

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
//        redisTemplate.opsForValue().set("person1", person, 10, TimeUnit.MINUTES);
//        redisTemplate.opsForValue().set("person2", person2);
        System.out.println(redisTemplate.hasKey("person1") + "\t" + redisTemplate.hasKey("person2"));
//        Person p1 = (Person) redisTemplate.opsForValue().get("person1");
        Person p2 = (Person) redisTemplate.opsForValue().get("person2");
        System.out.println("p2=" + p2.toJsonString());
//        System.out.println("p1=" + p1.toJsonString());
        System.out.println("uid=" + redisTemplate.opsForValue().get(formatKey("54299250243")));

//        System.out.println( redisTemplate.expire("person1", 10, TimeUnit.SECONDS));
//        System.out.println("-----------------分隔线-----------------");
//        new Thread().sleep(10000);
//        System.out.println(redisTemplate.hasKey("person1") + "\t" + redisTemplate.hasKey("person2"));
//        System.out.println("p2=" + redisTemplate.opsForValue().get("person2"));
//        System.out.println("p1=" + redisTemplate.opsForValue().get("person1"));
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
        if (uid == null) {
            sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
        }
        System.out.println("uid=" + uid);
        return uid;
    }

    public String formatKey(String adzone_id) {
        return String.format("pid_%s", adzone_id);
    }

    @Test
    public void testSetKey() throws InterruptedException {
        String adzone_id = "54300950058";
        String uid = "3bc9f45ab42e453f93ee8a966b5a9725";

        String key = formatKey(adzone_id);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, uid, 3, TimeUnit.SECONDS);
        System.out.println(redisTemplate.hasKey(key) + "\t" + operations.get(key));
        System.out.println("-----------------分隔线-----------------");
        Thread.sleep(3000);
        System.out.println(redisTemplate.hasKey(key) + "\t" + getValue(adzone_id));
    }


    @Test
    public void testBind() throws Exception {
        Pid pid = sessionTemplate.getMapper(TbkBindDao.class).getPidInfo();
        String adzone_id = pid.getAdzone_id();
        String uid = "3bc9f45ab42e453f93ee8a966b5a9725";
        String key = formatKey(adzone_id);
        int pkey = pid.getPkey();
        Map map = new HashMap();
        map.put("vekey", CommonParam.VEKEY.getValue());
        map.put("para", "578580044245");
        map.put("pid", pid.getPid());
        map.put("detail", "1");

//        map.put("pkey", pid.getPkey());
//        map.put("is_used", true);
//        map.put("uid", uid);
//        map.put("last_bind_uid", uid);
//        map.put("adzone_id", adzone_id);

//        String json = "{\"category_id\":\"50008163\",\"coupon_click_url\":\"https:\\/\\/uland.taobao.com\\/coupon\\/edetail?e=KpYXz%2BDAQ9IGQASttHIRqfzbS19XREsyiQ6MGVIHyzuAQiqSkK6nzbTm7F9ylnbMsIiiJ2KdX4Lyuh9GzUMEXocvvWHbqbxCsSKwS%2F%2FvFcEbUBaR%2FcKdMxemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRtlwZ8RA3DbvK08MqkibZ%2B&traceId=0b08079a15433978700963913e&union_lens=lensId:0b092931_0c05_16759ae8a21_8da9\",\"coupon_end_time\":\"2018-12-02\",\"coupon_info\":\"满1100元减1000元\",\"coupon_remain_count\":\"9400\",\"coupon_start_time\":\"2018-11-26\",\"coupon_total_count\":\"10000\",\"coupon_type\":\"3\",\"commission_rate\":\"30.00\",\"num_iid\":\"556602244435\",\"zk_final_price\":\"1128\",\"volume\":\"550\",\"user_type\":\"1\",\"title\":\"德兰帝斯泰国进口天然乳胶枕 保健枕头负离子护颈颈椎舒睡枕\",\"small_images\":[\"https:\\/\\/img.alicdn.com\\/i2\\/2454112044\\/O1CN011QyC1ncYMVGs5dX_!!2454112044.jpg\",\"https:\\/\\/img.alicdn.com\\/i1\\/2454112044\\/O1CN011QyC1kR1Nb7joQn_!!2454112044.jpg\",\"https:\\/\\/img.alicdn.com\\/i4\\/2454112044\\/O1CN011QyC1i4FXi362Mh_!!2454112044.jpg\",\"https:\\/\\/img.alicdn.com\\/i1\\/2454112044\\/O1CN011QyC1J2mcfMrChy_!!2454112044.jpg\"],\"seller_id\":\"2454112044\",\"reserve_price\":\"2689\",\"pict_url\":\"https:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i4\\/2454112044\\/O1CN01t8qI7G1QyC2RGW6w4_!!0-item_pic.jpg\",\"nick\":\"德兰帝斯旗舰店\",\"item_url\":\"https:\\/\\/detail.tmall.com\\/item.htm?id=556602244435\",\"cat_name\":\"床上用品\",\"cat_leaf_name\":\"枕头\\/枕芯\\/保健枕\\/颈椎枕\",\"tbk_pwd\":\"￥ScFebPGSr06￥\",\"coupon_short_url\":\"https:\\/\\/s.click.taobao.com\\/YgcaXJw\"}";
        JSONObject json = TaoBaoUtil.getHICPIInfo(map);
        ShopInfo shop = TaoBaoUtil.formatStr(json);
        shop.setPkey(pkey);
        shop.setUid(uid);
        shop.setGoods_info(json.toJSONString());
        shop.setResult_info("{\"msg\":\"测试\",\"code\":\"666\"}");

        redisTemplate.opsForValue().set(key, uid, 3, TimeUnit.DAYS);
        sessionTemplate.selectOne("com.tpadsz.after.dao80.ShopDao.saveShop", shop);
        System.out.println("result=" + shop.getOut_result());
        System.out.println("shop=" + JSON.toJSONString(shop));

//        sessionTemplate.getMapper(TbkBindDao.class).bindPid(map);
//        sessionTemplate.getMapper(TbkBindDao.class).insetShop(shop);
//        sessionTemplate.getMapper(TbkBindDao.class).insetShare(shop);
//        sessionTemplate.getMapper(TbkBindDao.class).insertHiPriceLog(shop);
//        sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
//        sessionTemplate.getMapper(TbkBindDao.class).insertBindLog(map);


        getValue(adzone_id);
//        List<Pid> list = sessionTemplate.selectList("tbk.getPids");
//        System.out.println(JSON.toJSONString(pid));
//        sessionTemplate.getMapper(TbkBindDao.class).updatePid(map);
    }

    @Test
    public void testMybatisTemplate() {
        Pid pid = sessionTemplate.selectOne("com.tpadsz.after.dao80.TbkBindDao.getPidInfo");

//        String json = "{\"category_id\":\"50008163\",\"coupon_click_url\":\"https:\\/\\/uland.taobao.com\\/coupon\\/edetail?e=KpYXz%2BDAQ9IGQASttHIRqfzbS19XREsyiQ6MGVIHyzuAQiqSkK6nzbTm7F9ylnbMsIiiJ2KdX4Lyuh9GzUMEXocvvWHbqbxCsSKwS%2F%2FvFcEbUBaR%2FcKdMxemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRtlwZ8RA3DbvK08MqkibZ%2B&traceId=0b08079a15433978700963913e&union_lens=lensId:0b092931_0c05_16759ae8a21_8da9\",\"coupon_end_time\":\"2018-12-02\",\"coupon_info\":\"满1100元减1000元\",\"coupon_remain_count\":\"9400\",\"coupon_start_time\":\"2018-11-26\",\"coupon_total_count\":\"10000\",\"coupon_type\":\"3\",\"commission_rate\":\"30.00\",\"num_iid\":\"556602244435\",\"zk_final_price\":\"1128\",\"volume\":\"550\",\"user_type\":\"1\",\"title\":\"德兰帝斯泰国进口天然乳胶枕 保健枕头负离子护颈颈椎舒睡枕\",\"small_images\":[\"https:\\/\\/img.alicdn.com\\/i2\\/2454112044\\/O1CN011QyC1ncYMVGs5dX_!!2454112044.jpg\",\"https:\\/\\/img.alicdn.com\\/i1\\/2454112044\\/O1CN011QyC1kR1Nb7joQn_!!2454112044.jpg\",\"https:\\/\\/img.alicdn.com\\/i4\\/2454112044\\/O1CN011QyC1i4FXi362Mh_!!2454112044.jpg\",\"https:\\/\\/img.alicdn.com\\/i1\\/2454112044\\/O1CN011QyC1J2mcfMrChy_!!2454112044.jpg\"],\"seller_id\":\"2454112044\",\"reserve_price\":\"2689\",\"pict_url\":\"https:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i4\\/2454112044\\/O1CN01t8qI7G1QyC2RGW6w4_!!0-item_pic.jpg\",\"nick\":\"德兰帝斯旗舰店\",\"item_url\":\"https:\\/\\/detail.tmall.com\\/item.htm?id=556602244435\",\"cat_name\":\"床上用品\",\"cat_leaf_name\":\"枕头\\/枕芯\\/保健枕\\/颈椎枕\",\"tbk_pwd\":\"￥ScFebPGSr06￥\",\"coupon_short_url\":\"https:\\/\\/s.click.taobao.com\\/YgcaXJw\"}";
//        ShopInfo shop= TaoBaoUtil.formatStr(json);
//        shop.setPkey(pid.getPkey());
//        shop.setUid("f0433a087dbb4b19967cc6b8da2e0558");
//        sessionTemplate.getMapper(TbkBindDao.class).insetShop(shop);
//        sessionTemplate.getMapper(TbkBindDao.class).insetShare(shop);
        Pid pid1 = sessionTemplate.getMapper(TbkBindDao.class).getPid("000002");
        System.out.println("pid=" + JSON.toJSONString(pid));
        System.out.println("pid1=" + JSON.toJSONString(pid1));
    }

    @Test
    public void testOrder() throws ParseException {
        java.util.Date create_time = new SimpleDateFormat("yyyy-MM-DD " +
                "HH:mm:ss").parse("2018-10-25 19:31:33");
        java.util.Date earning_time = new SimpleDateFormat("yyyy-MM-DD " +
                "HH:mm:ss").parse("2018-10-31 20:26:21");

//        OrderFrom orderFrom = new OrderFrom(111L, 111L, "11", "11", "11", 3, create_time,
//        earning_time, "11", "11", "11", 1, "11", "11", "11", "11", 3, 22.5,"天猫");
//        OrderFrom(long trade_id, Long num_iid, String adzone_id, String
//        site_id, String uid, Integer tk_status, Date create_time, Date
//        earning_time, String alipay_total_price, String price, String
//        pay_price, Integer item_num, String total_commission_rate, String
//        total_commission_fee, String
//        seller_shop_title, String item_title, Integer status, double rate_touid,String order_type)
//        Gson gson = new Gson();
//        String src = gson.toJson(orderFrom);
//        ValueOperations<String, Object> stringObjectValueOperations =
//                redisTemplate.opsForValue();
//        redisTemplate.opsForHash().put("orderFroms", orderFrom.getTrade_id(),
//                orderFrom);
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
    public void test11() {
        redisTemplate.opsForValue().set(formatKey("29274312"), "487812dasdasdasdad", 3, TimeUnit.DAYS);
//        redisTemplate.opsForValue().set(formatKey("29274313"), "487812dasdasdasdad", 3, TimeUnit.DAYS);
//        System.out.println(redisTemplate.opsForValue().get(String.format("pid_%s", "29274313")));
    }
}

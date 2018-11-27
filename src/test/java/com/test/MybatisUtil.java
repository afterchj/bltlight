package com.test;

import com.alibaba.fastjson.JSON;
import com.tpadsz.after.api.RecordBillService;
import com.tpadsz.after.dao80.OrderFromDao;
import com.tpadsz.after.dao.RecordBillDao;
import com.tpadsz.after.dao80.LightUserDao;
import com.tpadsz.after.entity.OrderFrom;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class MybatisUtil {
    private static ClassPathXmlApplicationContext atx;

    static {
        atx = new ClassPathXmlApplicationContext("applicationProvider.xml");
    }


    public static SqlSession getSession() {
        SqlSessionFactory factory = (SqlSessionFactory) atx.getBean("sqlSessionFactory80");
        return factory.openSession();
    }

    public static void main(String[] args) {
        RecordBillService recordBillService = (RecordBillService) atx.getBean("recordBillService");
        Map<String, String> binding = (Map) recordBillService.getByDeviceId("DBD370724B054036B5EF2DAB2312822");
        System.out.println(binding == null);
        System.out.println("binding:" + JSON.toJSONString(binding));
    }

    @Test
    public void Test() {
        String mobile = getSession().getMapper(LightUserDao.class).findLightUserByUid("5bc9f45ab42e453f93ee8a966b5a9726");
        System.out.println(mobile);
    }

    @Test
    public void testBind() {
        Map<String, String> binding = getSession().getMapper(RecordBillDao.class).getByDeviceId("3E94EE45B6164231A762BB8A6E531E0D");
        System.out.println(JSON.toJSONString(binding));

    }

    @Test
    public void testOperation() {
        Map<String, String> operation = getSession().getMapper(RecordBillDao.class).getByLightUid("5bc9f45ab42e453f93ee8a966b5a9726");
        System.out.println(JSON.toJSONString(operation));
    }

    @Test
    public void testYTD() {
        Map map = getSession().getMapper(RecordBillDao.class).getSumCharge("0016428081ec495b97edf124cb29d810");
        System.out.println(map == null);
        if (map != null) {
            BigDecimal total_charge = (BigDecimal) map.get("total_bill");
            double total = total_charge.doubleValue() / 1000;
            System.out.println("total=" + total);
        }
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void testList() {
        List<Map> list = getSession().getMapper(RecordBillDao.class).getChargeList("0016428081ec495b97edf124cb29d81");
        System.out.println(list.size());
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testUpdate() {
        Map map = new HashMap();
        map.put("uid", "a02e46e0f3404d59b7c21a742bffc38a");
        map.put("status", "1");
        getSession().getMapper(RecordBillDao.class).saveStatus(map);
    }

    @Test
    public void getUidList() {
        List<Map> uids = getSession().getMapper(RecordBillDao.class).getUidList();
        System.out.println("result=" + JSON.toJSONString(uids));
        for (Map<String, String> uid : uids) {
            if (!"1".equals(uid.get("status"))) {
                System.out.println("执行线程任务：" + uid.get("uid"));
            }
        }
    }

    @Test
    public void testOrderFrom() throws ParseException {
        String da="2018-10-25 19:47:09";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(da);
        OrderFrom orderFrom = new OrderFrom(238392046412662714L,13371072439L,"29274312","8696607","487812dasdasdasdad",3,date,date,"13.5000","13.50","13.50",1,"0.0180","0.2400","11","","");

        List<OrderFrom> orderFroms = getSession().getMapper(OrderFromDao.class).findByUidDone("487812dasdasdasdad");
        for (OrderFrom orderFrom1:orderFroms){
            System.out.println(orderFrom1.toString());
        }
    }
}

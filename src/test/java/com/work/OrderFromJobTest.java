package com.work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.config.DBConfig;
import com.tpadsz.after.config.SpringConfig;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.service.OrderFromService;
import com.tpadsz.after.service.TbkService;
import com.tpadsz.after.util.OrderFromUtil;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-11-27 15:20
 **/
@ContextConfiguration(classes = {SpringConfig.class, DBConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderFromJobTest {

    static ApplicationContext ac = null;

    static {
        ac = new ClassPathXmlApplicationContext("applicationProvider.xml");
    }

    OrderFromService orderFromService = ac.getBean("orderFromService",
            OrderFromService.class);
    TbkService tbkService = ac.getBean("tbkService",TbkService.class);
//    ShopService shopService = ac.getBean("shopService",ShopService.class);
    static final String vekey = "V00000585Y74210916";
    static final String span = "1200";
    static final String url = "http://apiorder.vephp.com/order";
    static final String order_query_type = "settle_time";
    static int yesterCount = 1;
    static int settleCount = 1;
    static String yesterTime = "00:00:00";
    static String settleTime = "00:00:00";
    static String preFirstDay = OrderFromUtil.getPreFirstDay();
    static String preLastDay = OrderFromUtil.getPreLastDay();
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        for (int i = 1; i <= 2232; i++) {
            getSettleOrder(preFirstDay);
        }
    }

    @Test
    public void test3() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String preDateByDate = OrderFromUtil.getPreDateByDate(date);
        for (int i = 1; i < 73; i++) {
            getYesterOrder(preDateByDate);
        }
    }

    @Test
    public void test4() throws ParseException {
        getEveryOrder(-20);
    }

    public void getEveryOrder(Integer num) throws ParseException {
        String date;
//        String start_time;
        String start_time = "2018-10-25%2019%3A30%3A49";
//        try {
        date = OrderFromUtil.getTimeByMinute(num);
//            start_time = java.net.URLEncoder.encode(date, "utf-8");
        setOrderFromResult(url, start_time, "-1");
        System.out.println(date + " 执行了每天接口");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    public void getYesterOrder(String preDateByDate) {
        String start_time;
        yesterTime = preDateByDate + " " + yesterTime;
        try {
            start_time = java.net.URLEncoder.encode(yesterTime, "utf-8");
            setOrderFromResult(url, start_time, "-1");
            System.out.println(yesterTime + " 执行了 : " + yesterCount +
                    "次---前一天接口");
            yesterCount++;
            yesterTime = OrderFromUtil.setPreDate(yesterTime, 1200000L);

            if (yesterCount > 72) {
                //调用完一天的接口
                System.out.println("---------前一天接口调用完毕------");
                return;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void getSettleOrder(String preDateByDate) {
        String start_time;
        settleTime = preDateByDate + " " + settleTime;
        try {
            start_time = java.net.URLEncoder.encode(settleTime, "utf-8");
            setOrderFromResult(url, start_time, order_query_type);
            System.out.println(settleTime + " 执行了 : " + settleCount +
                    "次-一个月的全部接口");
            //时间推进20分钟
            settleTime = OrderFromUtil.setPreDate(settleTime, 1200000L);
            settleCount++;
            if (settleCount > 72) {
                //一天接口执行完毕
                System.out.println("----一" + preDateByDate + "的接口执行完毕---");
                //重置时分秒
                settleTime = "00:00:00";
                settleCount = 1;//计数器重置
                //后一天
                String preDateDate;
                preDateDate = OrderFromUtil.getAftDateByDate
                        (preFirstDay);
                preFirstDay = preDateDate;
                Date firstDay = new SimpleDateFormat("yyyy-MM-dd").parse
                        (preFirstDay);
                Date lastDay = new SimpleDateFormat("yyyy-MM-dd").parse
                        (preLastDay);
                if (firstDay.getTime() > lastDay.getTime()) {
                    //循环执行到一个月的最后一天
                    System.out.println("-----一个月的全部接口循环完毕----");
                    //回退到前一个月的第一天
                    preFirstDay = OrderFromUtil.getPreFirstDay();
                    return;
                }
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setOrderFromResult(String url, String start_time, String
            order_query_type) throws ParseException {
        JSONArray array;
        String result;
        JSONObject jsonObject;
        String newDate;
        String param;
        int pageNo = 1;
        while (true) {
            if (order_query_type == "-1") {
                param = "vekey=" + vekey + "&start_time=" + start_time +
                        "&span=" + span + "&page_no=" + pageNo;
            } else {
                param = "vekey=" + vekey + "&start_time=" + start_time +
                        "&order_query_type=" + order_query_type +
                        "&span=" + span + "&page_no=" + pageNo;
            }
            result = OrderFromUtil.sendGet(url, param);
            jsonObject = JSONObject.parseObject(result);
            array = jsonObject.getJSONArray("data");
            newDate = jsonObject.getString("data");
            if (newDate != null) {
                OrderFrom orderFrom;
                OrderFrom orderFrom1;
                String adzoneId;
                Long tradeId;
                Map<String, Object> map = new HashedMap();
                for (int i = 0; i < array.size(); i++) {
                    orderFrom = setOrderFrom(array.getJSONObject(i));
                    //根据淘宝订单状态添加本地订单表的订单状态
                    setOrderStatus(orderFrom,order_query_type);
                    adzoneId = orderFrom.getAdzone_id();
                    tradeId = orderFrom.getTrade_id();
                    //查询本地订单表是否有该笔订单
                    orderFrom1 = orderFromService.findOrderFromById(tradeId);
                    //本地表无数据,需要插入数据
                    if (orderFrom1 == null) {
                        //查询pid-uid绑定关系
                        String uid;
//                        uid = shopService.getUid(adzoneId);
                        uid = (String) redisTemplate.opsForValue()
                                .get(String.format("pid_%s", adzoneId));
                        String num_iid = String.valueOf(orderFrom.getNum_iid());
                        map.put("uid",uid);
                        map.put("num_iid",num_iid);
                        Date shareTime = orderFromService.findShareLogByUidAndIid(map);
                        int days = 3;
                        //有分享时间
                        if (shareTime!=null){
                            //分享时间和下单时间日期差
                            days = timeDiff(shareTime,orderFrom);
                        }
                        //有绑定关系&&分享时间不超过三天
                        //有绑定关系
                        if (uid != null&&days<=3) {
                            //插入
                            orderFrom.setUid(uid);
                            orderFromService.insertOrderFrom(orderFrom);
                            System.out.println("插入数据成功: " + orderFrom
                                    .getTrade_id());
                            //修改预估和结算金额
                            setTbCoins(orderFrom,orderFrom1);
//                            if (orderFrom.getStatus()==3){
//                                待返佣状态下需要在预估表中插入数据
//                                tbkService.settleCoins(orderFrom);
//                            }else if (orderFrom.getStatus()==12){
//                                已结算状态下需要在结算表中插入数据
//                                tbkService.recordECoins(orderFrom);
//                            }
                        }
                    } else {//本地表有数据
                        //数据需要更新
                        if (!orderFrom.equals(orderFrom1)) {
                            //更新
                            orderFromService.updateOrderFrom(orderFrom);
                            System.out.println("更新数据成功: " + orderFrom
                                    .getTrade_id());
                            //修改预估和结算金额
                            setTbCoins(orderFrom,orderFrom1);
//                            if (orderFrom1.getStatus()!=orderFrom.getStatus()){
//                                if (orderFrom.getStatus()==3){
//                                    //待返佣状态下需要在预估表中插入数据
//                                    orderFrom.setUid(orderFrom1.getUid());
//                                    tbkService.settleCoins(orderFrom);
//
//                                }else if (orderFrom.getStatus()==12){
//                                    //已结算状态下需要在结算表中插入数据
//                                    orderFrom.setUid(orderFrom1.getUid());
//                                    tbkService.recordECoins(orderFrom);
//                                }
//                            }
                        }
                    }
                }
                if (array.size() < 100) {
                    break;
                }
            } else {
                //出错或查询为空
                break;
            }
            pageNo++;
        }
    }

    public OrderFrom setOrderFrom(JSONObject jsonObject) {
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setTrade_id(jsonObject.getLong("trade_id"));
        orderFrom.setNum_iid(jsonObject.getLong("num_iid"));
        orderFrom.setAdzone_id(jsonObject.getString("adzone_id"));
        orderFrom.setSite_id(jsonObject.getString("site_id"));
        orderFrom.setTk_status(jsonObject.getInteger("tk_status"));
        orderFrom.setCreate_time(jsonObject.getDate("create_time"));
        orderFrom.setEarning_time(jsonObject.getDate("earning_time"));
        orderFrom.setAlipay_total_price(jsonObject.getString
                ("alipay_total_price"));
        orderFrom.setPrice(jsonObject.getString("price"));
        orderFrom.setPay_price(jsonObject.getString("pay_price"));
        orderFrom.setItem_num(jsonObject.getInteger("item_num"));
        orderFrom.setTotal_commission_rate(jsonObject.getString
                ("total_commission_rate"));
        orderFrom.setTotal_commission_fee(jsonObject.getString
                ("total_commission_fee"));
//        orderFrom.setAction_type(jsonObject.getString("action_type"));
        orderFrom.setSeller_shop_title(jsonObject.getString
                ("seller_shop_title"));
        orderFrom.setItem_title(jsonObject.getString("item_title"));
        return orderFrom;

    }

    public void setTbCoins(OrderFrom orderFrom,OrderFrom orderFrom1){
        if (orderFrom1 == null) {
            if (orderFrom.getStatus()==3){
                //待返佣状态下需要在预估表中插入数据
                tbkService.settleCoins(orderFrom);
            }else if (orderFrom.getStatus()==12){
                //已结算状态下需要在结算表中插入数据

                tbkService.recordECoins(orderFrom);
            }
        }else {
            if (orderFrom1.getStatus()!=orderFrom.getStatus()){
                orderFrom.setUid(orderFrom1.getUid());
                if (orderFrom.getStatus()==3){
                    //待返佣状态下需要在预估表中插入数据
                    tbkService.settleCoins(orderFrom);
                }else if (orderFrom.getStatus()==12){
                    //已结算状态下需要在结算表中插入数据
                    tbkService.recordECoins(orderFrom);
                }
            }
        }
    }

    public Integer timeDiff(Date shareTime, OrderFrom orderFrom) throws
            ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String share ;
        share= sdf.format(shareTime);
        shareTime = sdf.parse(share);
        Date OrderTime = orderFrom.getCreate_time();
        String order = sdf.format(OrderTime);
        OrderTime = sdf.parse(order);
        return  (int) ((OrderTime.getTime() - shareTime.getTime()) / (1000*3600*24));
    }

    public void setOrderStatus(OrderFrom orderFrom,String
            order_query_type){
        Integer tbStatus = orderFrom.getTk_status();
        if (order_query_type == "-1"){
            //当天和隔天需要修改的本地订单表的订单状态
            if (tbStatus==3||tbStatus==14){
                //淘宝订单状态为结算和成功，本地订单状态修改为待返佣
                orderFrom.setStatus(12);
            }else {
                orderFrom.setStatus(orderFrom.getTk_status());
            }
        }else {
            //结算日需要修改的本地订单表的订单状态
            if (tbStatus==14){
                //淘宝订单状态为成功的修改本地订单表的订单状态为待返佣
                orderFrom.setStatus(12);
            }else {
                orderFrom.setStatus(orderFrom.getTk_status());
            }
        }
    }

    @Test
    public void test2() {
        System.out.println(redisTemplate.opsForValue().get(String.format("pid_%s", "54302800218")));
    }

    @Test
    public void test5() throws ParseException {
        Map<String, Object> map = new HashedMap();
        map.put("uid", "487812dasdasdasdad");
        map.put("num_iid", "13371072438");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date shareTime = orderFromService.findShareLogByUidAndIid(map);
        String share = sdf.format(shareTime);
        shareTime = sdf.parse(share);
        System.out.println(shareTime.getTime());
        Date OrderTime = new Date();
        String order = sdf.format(OrderTime);
        OrderTime = sdf.parse("2018-11-07");
        System.out.println(OrderTime.getTime());
        int days = (int) ((OrderTime.getTime() - shareTime.getTime()) / (1000*3600*24));
        System.out.println(days);

    }

}

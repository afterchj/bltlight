package com.work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.config.DBConfig;
import com.tpadsz.after.config.SpringConfig;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.service.OrderFromService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    static final String vekey = "V00000585Y74210916";
    static final String span = "1200";
    static final String url = "http://apiorder.vephp.com/order";
    static int count = 1;
    static String newTime = "00:00:00";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(date);
        String preDateByDate = getPreDateByDate(date);
        for (int i=1;i<121;i++){
            getUrlParams(-1,preDateByDate);
        }

    }

    public void getUrlParams(Integer num,String preDateByDate) {
        String date = getTimeByMinute(num);
        String code = null;
        try {
            if (num==-20){
                //每一分钟调用前20分钟的接口
                code = java.net.URLEncoder.encode(date, "utf-8");
                System.out.println(date);
            }else if (num==-1){
                //每天调用前一天的接口
                newTime = preDateByDate+" "+newTime;
                System.out.println(newTime + " 执行了 : " + count + "次");
                code = java.net.URLEncoder.encode(newTime, "utf-8");
                newTime = setPreDate(newTime, 1200000L);
                if (count>=73){
                    return;
                }
                count++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String start_time = "2018-10-25%2019%3A30%3A49";
        int page_no = 1;
        JSONArray array;
        while (true) {
//            String param = "vekey=" + vekey + "&start_time=" + start_time +
//                    "&span=" + span + "&page_no=" + page_no;
            String param = "vekey=" + vekey + "&start_time=" + code +
                    "&span=" + span + "&page_no=" + page_no;
            String result;
            JSONObject jsonObject;
            result = sendGet(url, param);
            jsonObject = JSONObject.parseObject(result);
            array = jsonObject.getJSONArray("data");
            String newDate = jsonObject.getString("data");
            OrderFrom orderFrom;
            if (newDate != null) {
                for (int i = 0; i < array.size(); i++) {
                    orderFrom = setOrderFrom(array.getJSONObject(i));
                    String adzoneId = orderFrom.getAdzone_id();
                    Long tradeId = orderFrom.getTrade_id();
                    //查询本地订单表是否有该笔订单
                    OrderFrom orderFrom1 = orderFromService
                            .findOrderFromById(tradeId);
                    //本地表无数据,需要插入数据
                    if (orderFrom1 == null) {
                        //查询pid-uid绑定关系
                        String uid = (String) redisTemplate.opsForValue()
                                .get(String.format("pid_%s", adzoneId));
                        //有绑定关系
                        if (uid != null) {
                            //插入
                            orderFrom.setUid(uid);
                            orderFromService.insertOrderFrom(orderFrom);
                        }
                    } else {//本地表有数据
                        //数据需要更新
                        if (!orderFrom.equals(orderFrom1)) {
                            //更新
                            orderFromService.updateOrderFrom(orderFrom);
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
            page_no++;
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
        System.out.println(jsonObject.getString("seller_shop_title"));
        orderFrom.setItem_title(jsonObject.getString("item_title"));
        return orderFrom;

    }

    //获取前几分钟的时间
    public static String getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar
                .getTime());
    }

    public static String sendGet(String url, String param) {
        String urlNameString = url + "?" + param;
        String result = null;
        StringBuffer sb = new StringBuffer();
        URL realUrl;
        URLConnection connection;
        BufferedReader in = null;
        try {
            realUrl = new URL(urlNameString);
            connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;" +
                            "SV1)");
            // 建立实际的连接
            String line;
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 获取前一天日期
     * @param strData 格式:yyyy-MM-DD
     * @return 格式:yyyy-MM-DD
     */
    public static String getPreDateByDate(String strData) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(strData);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - 1);
        preDate = sdf.format(c.getTime());
        return preDate;
    }

    /**
     * 指定日期前进几分钟
     * @param time yyyy-MM-DD
     * @param mis 毫秒
     * @return
     * @throws ParseException
     */
    public String setPreDate(String time,long mis) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        Date date2 = new Date(date1.getTime()+mis);
        return new SimpleDateFormat("HH:mm:ss").format(date2);
    }

}

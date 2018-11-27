package com.tpadsz.after.work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.URLEncoder;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.service.OrderFromService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @program: blt-light
 * @description: 订单
 * @author: Mr.Ma
 * @create: 2018-11-27 14:12
 **/
@Service("orderFromJob")
public class OrderFromJob {

    @Resource
    private OrderFromService orderFromService;
    static final String vekey = "V00000585Y74210916";
    static final String span = "1200";

    public void getUrlParams() {

        String date = getTimeByMinute(-20);
        try {
            String code = getUrlEncode(date);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://apiorder.vephp.com/order";
        String start_time = "2018-10-25%2019%3A30%3A49";
        int page_no = 1;
        JSONArray array;
        while (true) {
            String param = "vekey=" + vekey + "&start_time=" + start_time +
                    "&span=" + span + "&page_no=" + page_no;
            String urlNameString = url + "?" + param;
            URL realUrl;
            URLConnection connection;
            BufferedReader in = null;
            String result;
            StringBuffer sb = new StringBuffer();
            JSONObject jsonObject;
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
                jsonObject = JSONObject.parseObject(result);
                array = jsonObject.getJSONArray("data");
                String newDate = jsonObject.getString("data");
                OrderFrom orderFrom;
                if (newDate != null) {
                    for (int i=0;i<array.size();i++){
                        orderFrom = setOrderFrom(array.getJSONObject(i));
                        Long tradeId=orderFrom.getTrade_id();
                        Long newTradeId = orderFromService.findOrderFromById(tradeId);
                        if (newTradeId==null){
                            //插入
                            //查询pid-uidb绑定关系
                            orderFromService.insertOrderFrom(orderFrom);
                        }else {
                            //更新
                            orderFromService.updateOrderFrom(orderFrom);
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
        }
    }


    public OrderFrom setOrderFrom(JSONObject jsonObject){
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setTrade_id(jsonObject.getLong("trade_id"));
        orderFrom.setNum_iid(jsonObject.getLong("num_iid"));
        orderFrom.setAdzone_id(jsonObject.getString("adzone_id"));
        orderFrom.setSite_id(jsonObject.getString("site_id"));
        orderFrom.setTk_status(jsonObject.getInteger("tk_status"));
        orderFrom.setCreate_time(jsonObject.getDate("create_time"));
        orderFrom.setEarning_time(jsonObject.getDate("earning_time"));
        orderFrom.setAlipay_total_price(jsonObject.getString("alipay_total_price"));
        orderFrom.setPrice(jsonObject.getString("price"));
        orderFrom.setPay_price(jsonObject.getString("pay_price"));
        orderFrom.setItem_num(jsonObject.getInteger("item_num"));
        orderFrom.setTotal_commission_rate(jsonObject.getString("total_commission_rate"));
        orderFrom.setTotal_commission_fee(jsonObject.getString("total_commission_fee"));
//        orderFrom.setAction_type(jsonObject.getString("action_type"));
        orderFrom.setSeller_shop_title(jsonObject.getString("seller_shop_title"));
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


    public static String getUrlEncode(String code) throws
            UnsupportedEncodingException {
        String encode = code;
        String urlEncode = URLEncoder.encode(encode, "utf-8");
        return urlEncode;
    }


}

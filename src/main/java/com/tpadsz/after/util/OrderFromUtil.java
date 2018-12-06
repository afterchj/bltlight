package com.tpadsz.after.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: blt-light
 * @description: 订单
 * @author: Mr.Ma
 * @create: 2018-11-29 19:09
 **/
public class OrderFromUtil {
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
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
//            connection.setConnectTimeout(30000);
//            connection.setReadTimeout(3000);
            // 建立实际的连接
            String line;
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            return result;
        }  catch (ConnectException e2){
            System.out.println("连接超时！");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
     * @param strData 格式:yyyy-MM-dd
     * @return 格式:yyyy-MM-dd
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
     * 指定日期前几分钟
     * @param time yyyy-MM-dd
     * @param mis 毫秒
     * @return
     * @throws ParseException
     */
    public static String setPreDate(String time,long mis) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        Date date2 = new Date(date1.getTime()+mis);
        return new SimpleDateFormat("HH:mm:ss").format(date2);
    }

    /**
     * 获取前一个月第一天
     * @return yyyy-MM-dd
     */
    public static String getPreFirstDay(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar1.getTime());
        return firstDay;
    }

    /**
     * 获取前一个月最后一天
     * @return yyyy-MM-dd
     */
    public static String getPreLastDay(){
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar2.getTime());
        return lastDay;
    }
    /**
     * 获取后一天日期
     * @param strData 格式:yyyy-MM-dd
     * @return 格式:yyyy-MM-dd
     */
    public static String getAftDateByDate(String strData) {
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
        c.set(Calendar.DATE, day1 + 1);
        preDate = sdf.format(c.getTime());
        return preDate;
    }

}

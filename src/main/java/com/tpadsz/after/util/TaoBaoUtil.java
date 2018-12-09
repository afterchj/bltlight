package com.tpadsz.after.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkUatmFavoritesGetRequest;
import com.taobao.api.request.TbkUatmFavoritesItemGetRequest;
import com.taobao.api.response.TbkUatmFavoritesGetResponse;
import com.taobao.api.response.TbkUatmFavoritesItemGetResponse;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.entity.dd.CommonParam;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hongjian.chen on 2018/11/19.
 */


public class TaoBaoUtil {

    public static void main(String[] args) throws Exception {
//        System.out.println("tip="+getRatePrice(9.0,0.1));
//        favoritesGet();
//        System.out.println();
//        favoritesItemGet();
//        System.out.println();
//        urlEncode();

//        Map map = new HashMap();
//        map.put("vekey", CommonParam.VEKEY.getValue());
//        map.put("para", "564590311746");
//        map.put("pid", "mm_43238250_191900396_54298500491");
//        map.put("detail", "1");
//        JSONObject json = getHICPIInfo(map);
//        ShopInfo shop = formatStr(json);
//        System.out.println("response=" + HttpClientUtil.httpGet(CommonParam.VEHICPI.getValue(), map));
//        System.out.println("shop=" + JSON.toJSONString(shop));
//        System.out.println("data=" + JSON.toJSONString(getData(getHICPIInfo(map))));

//        map.put("start_time", "2018-10-25 19:48:24");
//        map.put("span", "1200");
//        System.out.println("response1:\n" + ret1);
//        System.out.println("response2:\n" + ret2);

        String str="满21元减15元";
        Double price=21.9;
        System.out.println(getQhPrice(str,price));
    }

    public static void favoritesGet() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(CommonParam.APPURL.getValue(), CommonParam.APPKER.getValue(), CommonParam.APPSECRET.getValue());
        TbkUatmFavoritesGetRequest req = new TbkUatmFavoritesGetRequest();
        req.setPageNo(1L);
        req.setPageSize(20L);
        req.setFields("favorites_title,favorites_id,type");
        req.setType(1L);
        TbkUatmFavoritesGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    public static void favoritesItemGet() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(CommonParam.APPURL.getValue(), CommonParam.APPKER.getValue(), CommonParam.APPSECRET.getValue());
        TbkUatmFavoritesItemGetRequest req = new TbkUatmFavoritesItemGetRequest();
        req.setPlatform(1L);
        req.setPageSize(20L);
        req.setAdzoneId(44740840L);
        req.setUnid("3456");
        req.setFavoritesId(18750819L);
        req.setPageNo(1L);
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,status,type");
        TbkUatmFavoritesItemGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    public static void urlEncode() throws UnsupportedEncodingException {
        String encode = "{\"itemNumId\":\"557691028742\"}";
        String urlEncode = URLEncoder.encode(encode, "utf-8");
        System.out.println(urlEncode);
    }

    public static JSONObject getHICPIInfo(Map map) throws Exception {
        String result = HttpClientUtil.httpGet(CommonParam.VEHICPI.getValue(), map);
        JSONObject str = JSON.parseObject(result);
        JSONObject data = str.getJSONObject("data");
        return data == null ? str : data;
    }

    public static Map getData(JSONObject jsonObject) {
        Map map = new HashMap();
        map.put("coupon_click_url", jsonObject.getString("coupon_click_url"));
        map.put("tbk_pwd", jsonObject.getString("tbk_pwd"));
        map.put("coupon_short_url", jsonObject.getString("coupon_short_url"));
        return map;
    }

    public static ShopInfo formatStr(JSONObject jsonObject) {
        ShopInfo shop = jsonObject.toJavaObject(ShopInfo.class);
        String str = jsonObject.getString("coupon_info");
        double price = Double.parseDouble(jsonObject.getString("zk_final_price"));
        double rate = Double.parseDouble(jsonObject.getString("commission_rate")) / 100;
        double qh_price = price;
        if (StringUtils.isNotEmpty(str)) {
            qh_price = getQhPrice(str, price);
        }
        shop.setQh_final_price(qh_price);
        shop.setRate_touid(getRatePrice(qh_price, rate));
        return shop;
    }

    public static double getQhPrice(String var, Double prince) {
        Double qh_final_price = prince;
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(var);
        String value = m.replaceAll("_").trim();
        String[] values = value.split("_");
        List<Integer> list = new ArrayList<>();
        for (String str : values) {
            if (StringUtils.isNotEmpty(str)) {
                list.add(Integer.parseInt(str));
            }
        }
        Integer p1 = list.get(0);
        Integer p2 = list.get(1);
        if (prince >= p1) {
            qh_final_price = prince - p2;
        }
        return qh_final_price;
    }

    public static double getRatePrice(double price, double rate) {
        Double tip = price * rate;
        if (tip > 1) {
            tip *= 0.8;
        }
        return tip;
    }
}

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
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Map map = new HashMap();
        map.put("vekey", CommonParam.VEKEY.getValue());
        map.put("para", "556602244435");
        map.put("pid", "mm_43238250_191900396_54298500491");
        map.put("detail", "1");
        String json=getHICPIInfo(map);
        ShopInfo shop = formatStr(json);
        System.out.println(json);
//        System.out.println(JSON.toJSONString(getData(getHICPIInfo(map))));

//        map.put("start_time", "2018-10-25 19:48:24");
//        map.put("span", "1200");
//        System.out.println("response1:\n" + ret1);
//        System.out.println("response2:\n" + ret2);
    }

    public static String getHICPIInfo(Map map) throws Exception {
        String result = HttpClientUtil.httpGet(CommonParam.VEHICPI.getValue(), map);
        JSONObject jsonObject = JSON.parseObject(result);
        String json = jsonObject.getString("data");
        return json;
    }

    public static Map getData(String json) {
        Map map = new HashMap();
        JSONObject jsonObject = JSONObject.parseObject(json);
        map.put("coupon_click_url", jsonObject.getString("coupon_click_url"));
        map.put("tbk_pwd", jsonObject.getString("tbk_pwd"));
        map.put("coupon_short_url", jsonObject.getString("coupon_short_url"));
        return map;
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
        req.setFavoritesId(18732551L);
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

    public String formatKey(String adzone_id) {
        return String.format("pid_%s", adzone_id);
    }

    @Test
    public void testFormat() throws ParseException {
        String key = formatKey("9de2725281b44136b04e474d85061151");
        System.out.println(key);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = format.parse("2018-11-23");
        String dateTime = format.format(new Date());
        Date date1 = format.parse(dateTime);
        System.out.printf("年-月-日格式：%tF%n", date1);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n", date1);
        System.out.println("dateTime=" + dateTime);
    }

    //    @Test
    public static ShopInfo formatStr(String json) {
//        String var = "满80元减5元";
//        Double price = 58.00;
        JSONObject jsonObject = JSON.parseObject(json);
        ShopInfo shop = jsonObject.toJavaObject(ShopInfo.class);
        String str = jsonObject.getString("coupon_info");
        Double price = Double.parseDouble(jsonObject.getString("zk_final_price"));
        Double rate = Double.parseDouble(jsonObject.getString("commission_rate")) / 100;
        Double qh_price = price;
        if (StringUtils.isNotEmpty(str)) {
            qh_price = TaoBaoUtil.getQhPrice(str, price);
            shop.setQh_final_price(qh_price);
        }
        shop.setRate_touid(TaoBaoUtil.getRatePrice(qh_price, rate));
        System.out.println("zk_final_price=" + price + "，qh_final_price=" + getQhPrice(str, price));
        return shop;
    }

    public static Double getQhPrice(String var, Double prince) {
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

    public static Double getRatePrice(Double price, Double rate) {
        Double tip = price * rate;
        if (tip > 1) {
            tip *= 0.8;
        }
        return tip;
    }
}

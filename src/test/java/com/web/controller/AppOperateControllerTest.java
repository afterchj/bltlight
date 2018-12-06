package com.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tpadsz.after.entity.LightOperation;
import com.tpadsz.after.entity.OpenApp;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.service.AppOperateService;
import com.tpadsz.after.service.LightUserService;
import com.tpadsz.after.service.OrderFromService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-08-22 11:43
 **/
public class AppOperateControllerTest {

    static ApplicationContext ac = null;
    static {
        ac = new ClassPathXmlApplicationContext("applicationProvider.xml");
    }
    AppOperateService appOperateService = ac.getBean("appOperateService",AppOperateService.class);
    LightUserService lightUserService = ac.getBean("lightUserService", LightUserService.class);
    OrderFromService orderFromService = ac.getBean("orderFromService", OrderFromService.class);


    @Test
    public void OpenAppTest(){
        String str = "{\"firmware\":{\"clientVersion\":\"4.1.3\"," +
                "\"imei\":\"861790031089497\",\"imsi\":\"460022062043546\"," +
                "\"fm\":\"\",\"os\":\"android-6.0\",\"model\":\"Le_X620\"," +
                "\"operators\":\"YD\",\"resolution\":\"1080*1920\"," +
                "\"netEnv\":\"WIFI\",\"pkg\":\"com.change.unlock\"}," +
                "\"uid\":\"\",\"group\":\"123456\",\"appid\":\"11\"}";
        JSONObject params = JSONObject.parseObject(str);
        OpenApp openApp = setOPenApp(params);
        appOperateService.openAppLog(openApp);
    }

    @Test
    public void LightOperationTest(){
        String str = "{\"uid\":\"032abd148fc84835997ceb40dbcd6b15\"," +
                "\"lightId\":\"88750561a7bb453e8dad5fe04b28a675\"," +
                "\"behavior\":\"0\"}";
        JSONObject params = JSONObject.parseObject(str);
        LightOperation lightOperation = setLightOperation(params);
        appOperateService.lightOperationLog(lightOperation);
    }

    public OpenApp setOPenApp(JSONObject params){
        String uid = params.getString("uid");
        String behavior = null;
        OpenApp openApp = new OpenApp();
        if (StringUtils.isBlank(uid)){
            behavior = "install";
        }else {
            behavior = "visit";
        }
        JSONObject firmware = params.getJSONObject("firmware");
        openApp.setId(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
        openApp.setUid(uid);
        openApp.setAppid("11");
//        openApp.setGroup(params.getString("group"));
        openApp.setCreate_date(new Date());
        openApp.setBehavior(behavior);
        openApp.setImei(firmware.getString("imei"));
        openApp.setImsi(firmware.getString("imsi"));
        openApp.setFm(firmware.getString("fm"));
        openApp.setOs(firmware.getString("os"));
        openApp.setOperators(firmware.getString("operators"));
        openApp.setResolution(firmware.getString("resolution"));
        openApp.setPkg(firmware.getString("pkg"));
        openApp.setModel(firmware.getString("model"));
        openApp.setClientVersion(firmware.getString("clientVersion"));
        openApp.setNetEnv(firmware.getString("netEnv"));
        return openApp;
    }

    public LightOperation setLightOperation(JSONObject params){
        LightOperation lightOperation = new LightOperation();
        String uid = params.getString("uid");
        String lightId = params.getString("lightId");
        String behavior = params.getString("behavior");//'1'为开；'0'为关
        String id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
        String mobile = lightUserService.findLightUserByUid(uid);
        String isRegister = null;
        if (StringUtils.isBlank(mobile)){
            isRegister = "0";//未注册
        }else {
            isRegister = "1";//已注册
        }
        lightOperation.setId(id);
        lightOperation.setUid(uid);
        lightOperation.setBehavior(behavior);
//        lightOperation.setLightId(lightId);
        lightOperation.setIsRegister(isRegister);
        lightOperation.setCreate_date(new Date());
        return lightOperation;
    }

    @Test
    public void testOrder(){
        PageInfo pageInfos = orderFromService.findAll("487812dasdasdasdad",1,1);
        List<OrderFrom> orderFroms = pageInfos.getList();
        for (OrderFrom orderFrom:orderFroms){
            System.out.println(orderFrom.toString());
        }
    }
    @Test
    public void testPidUid(){
//        String pidAndUidByZdId = orderFromService.findPidAndUidByZdId
//                ("542987003");
//        System.out.println(pidAndUidByZdId);
//        ShopInfo shopImageByNumIid = orderFromService.findShopImageByNumIid
//                ("580728102184");
//        System.out.println(shopImageByNumIid.getPict_url()+" "+shopImageByNumIid.getRate_touid());
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setRate_touid(5.54);
        orderFrom.setItem_num(1);
        orderFrom.setPrePrice((orderFrom.getRate_touid())*(orderFrom.getItem_num()));
        System.out.println(getType(orderFrom.getPrePrice()));
    }
    /**
     * 获取数据类型
     * @param object
     * @return
     */
    public static String getType(Object object){
        String typeName=object.getClass().getName();
        int length= typeName.lastIndexOf(".");
        String type =typeName.substring(length+1);
        return type;
    }

    @Test
    public void testSubString() throws ParseException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(date);
    }


}

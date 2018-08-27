//package com.web.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.tpadsz.after.entity.LightOperation;
//import com.tpadsz.after.entity.OpenApp;
//import com.tpadsz.after.service.AppOperateService;
//import com.tpadsz.after.service.LightUserService;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.Date;
//import java.util.UUID;
//
///**
// * @program: blt-light
// * @description:
// * @author: Mr.Ma
// * @create: 2018-08-22 11:43
// **/
//public class AppOperateControllerTest {
//
//    static ApplicationContext ac = null;
//    static {
//        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//    }
//    AppOperateService appOperateService = ac.getBean("appOperateService",AppOperateService.class);
//    LightUserService lightUserService = ac.getBean("lightUserService", LightUserService.class);
//
//    @Test
//    public void OpenAppTest(){
//        String str = "{\"firmware\":{\"clientVersion\":\"4.1.3\"," +
//                "\"imei\":\"861790031089497\",\"imsi\":\"460022062043546\"," +
//                "\"fm\":\"\",\"os\":\"android-6.0\",\"model\":\"Le_X620\"," +
//                "\"operators\":\"YD\",\"resolution\":\"1080*1920\"," +
//                "\"netEnv\":\"WIFI\",\"pkg\":\"com.change.unlock\"}," +
//                "\"uid\":\"\",\"group\":\"123456\",\"appid\":\"11\"}";
//        JSONObject params = JSONObject.parseObject(str);
//        OpenApp openApp = setOPenApp(params);
//        appOperateService.openAppLog(openApp);
//    }
//
//    @Test
//    public void LightOperationTest(){
//        String str = "{\"uid\":\"032abd148fc84835997ceb40dbcd6b15\"," +
//                "\"lightId\":\"88750561a7bb453e8dad5fe04b28a675\"," +
//                "\"behavior\":\"0\"}";
//        JSONObject params = JSONObject.parseObject(str);
//        LightOperation lightOperation = setLightOperation(params);
//        appOperateService.lightOperationLog(lightOperation);
//    }
//
//    public OpenApp setOPenApp(JSONObject params){
//        String uid = params.getString("uid");
//        String behavior = null;
//        OpenApp openApp = new OpenApp();
//        if (StringUtils.isBlank(uid)){
//            behavior = "install";
//        }else {
//            behavior = "visit";
//        }
//        JSONObject firmware = params.getJSONObject("firmware");
//        openApp.setId(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
//        openApp.setUid(uid);
//        openApp.setAppid("11");
//        openApp.setGroup(params.getString("group"));
//        openApp.setCreate_date(new Date());
//        openApp.setBehavior(behavior);
//        openApp.setImei(firmware.getString("imei"));
//        openApp.setImsi(firmware.getString("imsi"));
//        openApp.setFm(firmware.getString("fm"));
//        openApp.setOs(firmware.getString("os"));
//        openApp.setOperators(firmware.getString("operators"));
//        openApp.setResolution(firmware.getString("resolution"));
//        openApp.setPkg(firmware.getString("pkg"));
//        openApp.setModel(firmware.getString("model"));
//        openApp.setClientVersion(firmware.getString("clientVersion"));
//        openApp.setNetEnv(firmware.getString("netEnv"));
//        return openApp;
//    }
//
//    public LightOperation setLightOperation(JSONObject params){
//        LightOperation lightOperation = new LightOperation();
//        String uid = params.getString("uid");
//        String lightId = params.getString("lightId");
//        String behavior = params.getString("behavior");//'1'为开；'0'为关
//        String id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
//        String mobile = lightUserService.findLightUserByUid(uid);
//        String isRegister = null;
//        if (StringUtils.isBlank(mobile)){
//            isRegister = "0";//未注册
//        }else {
//            isRegister = "1";//已注册
//        }
//        lightOperation.setId(id);
//        lightOperation.setUid(uid);
//        lightOperation.setBehavior(behavior);
//        lightOperation.setLightId(lightId);
//        lightOperation.setIsRegister(isRegister);
//        lightOperation.setCreate_date(new Date());
//        return lightOperation;
//    }
//}

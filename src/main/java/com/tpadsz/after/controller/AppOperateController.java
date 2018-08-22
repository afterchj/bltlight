package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.BluethoothConnect;
import com.tpadsz.after.entity.LightOperation;
import com.tpadsz.after.entity.OpenApp;
import com.tpadsz.after.entity.ResultDict;
import com.tpadsz.after.service.AppOperateService;
import com.tpadsz.after.service.LightUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-08-21 14:58
 **/
@Controller
@RequestMapping("/appOperate")
public class AppOperateController extends BaseDecodedController{

    @Resource
    private AppOperateService appOperateService;
    @Resource
    private LightUserService lightUserService;

    @RequestMapping(value = "/blue",method = RequestMethod.POST)
    public void bluethoothConnect(@ModelAttribute("decodedParams") JSONObject params, ModelMap model){

        String uid = params.getString("uid");
        JSONArray array = params.getJSONArray("lightGroup");
        String id= null;
        BluethoothConnect bluethoothConnect = null;
        for (int i=0;i<array.size();i++){
            id= StringUtils.replace(UUID.randomUUID().toString(), "-", "");
            bluethoothConnect = new BluethoothConnect();
            bluethoothConnect.setId(id);
            bluethoothConnect.setUid(uid);
            bluethoothConnect.setLightId(array.getJSONObject(i).getString("lightId"));
            bluethoothConnect.setConnectStatus(array.getJSONObject(i).getString("connectStatus"));
            appOperateService.connectToBluetoothLog(bluethoothConnect);
        }
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message",ResultDict.SUCCESS.getValue());
//        return null;
    }

    @RequestMapping("/open")
    public void openApp(@ModelAttribute("decodedParams") JSONObject params, ModelMap model){
        OpenApp openApp = setOPenApp(params);
        appOperateService.openAppLog(openApp);
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message",ResultDict.SUCCESS.getValue());
        model.put("uid",openApp.getUid());
        model.put("group",openApp.getGroup());
    }

    @RequestMapping("/operation")
    public void LightOperation(@ModelAttribute("decodedParams") JSONObject params, ModelMap model){
        LightOperation lightOperation = setLightOperation(params);
        appOperateService.lightOperationLog(lightOperation);
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message",ResultDict.SUCCESS.getValue());
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
        openApp.setGroup(params.getString("group"));
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
        lightOperation.setLightId(lightId);
        lightOperation.setIsRegister(isRegister);
        lightOperation.setCreate_date(new Date());
        return lightOperation;
    }

}

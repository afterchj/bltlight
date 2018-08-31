package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.*;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.AppOperateService;
import com.tpadsz.after.service.LightUserService;
import com.tpadsz.after.service.PairingService;
import com.tpadsz.after.api.RecordBillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-08-21 14:58
 **/
@Controller
@RequestMapping("/appOperate")
public class AppOperateController extends BaseDecodedController {

    @Resource
    private AppOperateService appOperateService;
    @Resource
    private LightUserService lightUserService;
    @Resource
    private PairingService pairingService;

    @Autowired
    private RecordBillService billService;

    @RequestMapping(value = "/blue", method = RequestMethod.POST)
    public void bluethoothConnect(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {

        String uid = params.getString("uid");
        JSONArray array = params.getJSONArray("lightGroup");
        String id;
        String isLogin;
        BluethoothConnect bluethoothConnect;
        LightPairing lightPairing;
        for (int i = 0; i < array.size(); i++) {
            id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
            bluethoothConnect = new BluethoothConnect();
            bluethoothConnect.setId(id);
            bluethoothConnect.setUid(uid);
            bluethoothConnect.setDevice_id(array.getJSONObject(i).getString("deviceId"));
            bluethoothConnect.setConnectStatus(array.getJSONObject(i).getString("connectStatus"));
            appOperateService.connectToBluetoothLog(bluethoothConnect);

            //蓝牙配对和绑定关系判断
            //根据uid查询配对表
            lightPairing = pairingService
                    .findPairingInfoByLightUid(uid);
            if (lightPairing == null) {
                pairingService.savePairingInfo(uid, array.getJSONObject(i).getString("deviceId"));
            } else {
                String deviceIds = lightPairing.getName();
                net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(deviceIds);
                if (!jsonArray.contains(array.getJSONObject(i).getString("deviceId"))) {
                    jsonArray.add(array.getJSONObject(i).getString("deviceId"));
                    pairingService.updatePairingInfo(uid, jsonArray
                            .toString());
                }
            }
            isLogin = pairingService.findLoginState(uid);
            if ("1".equals(isLogin)) {
                LightBinding lightBinding = pairingService
                        .findBindingInfoByDeviceId(array.getJSONObject(i).getString("deviceId"));
                if (lightBinding == null) {
                    LightBinding lightBinding2 = new LightBinding(array.getJSONObject(i).getString("deviceId"),
                            array.getJSONObject(i).getString("mac"), uid, null, new Date(), null, null);
                    pairingService.saveBindingInfo(lightBinding2);
                } else if (!uid.equals(lightBinding.getLightUid())) {
                    pairingService.updateBindingInfo(uid, array.getJSONObject(i).getString("deviceId"));
                    if (lightBinding.getBossUid() != null) {
                        pairingService.updateBossBindingInfo(lightBinding
                                .getBossUid());
                    }
                }
            }
        }
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message", ResultDict.SUCCESS.getValue());
    }

    @RequestMapping("/open")
    public void openApp(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        OpenApp openApp = setOPenApp(params);
        appOperateService.openAppLog(openApp);
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message", ResultDict.SUCCESS.getValue());
        model.put("uid", openApp.getUid());
        model.put("group", openApp.getGroup());
    }

    @RequestMapping("/operation")
    public void LightOperation(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        LightOperation lightOperation = setLightOperation(params);
        appOperateService.lightOperationLog(lightOperation);
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message", ResultDict.SUCCESS.getValue());
        //生成电费表，beginning...
        String deviceId = params.getString("deviceId");
        Map map = new HashMap();
        try {
            Map<String, String> binding = (Map) billService.getByDeviceId(deviceId);
            Map<String, String> operation = (Map) billService.getByLightUid(binding.get("lightUid"));
            map.put("uid", binding.get("bossUid"));
            map.put("isRegister", operation.get("is_register"));
            map.put("result", ResultDict.SUCCESS.getCode());
            map.putAll(binding);
        } catch (Exception e) {
            map.put("result", ResultDict.UID_NOT_EXIST.getCode());
        }
        billService.insetBill(map);
        //生成电费表，ending...
    }

    public OpenApp setOPenApp(JSONObject params) {
        String uid = params.getString("uid");
        String behavior;
        OpenApp openApp = new OpenApp();
        if (StringUtils.isBlank(uid)) {
            behavior = "install";
        } else {
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

    public LightOperation setLightOperation(JSONObject params) {
        LightOperation lightOperation = new LightOperation();
        String uid = params.getString("uid");
        String deviceId = params.getString("deviceId");
        String behavior = params.getString("behavior");//'1'为开；'0'为关
        String id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
        String mobile = lightUserService.findLightUserByUid(uid);
        String isRegister;
        if (StringUtils.isBlank(mobile)) {
            isRegister = "0";//未注册
        } else {
            isRegister = "1";//已注册
        }
        lightOperation.setId(id);
        lightOperation.setUid(uid);
        lightOperation.setBehavior(behavior);
        lightOperation.setDevice_id(deviceId);
        lightOperation.setIsRegister(isRegister);
        lightOperation.setCreate_date(new Date());
        return lightOperation;
    }

}

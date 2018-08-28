package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.*;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.PairingService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/light")
public class LightController extends BaseDecodedController {

    private PairingService pairingService;

    @RequestMapping(value = "/pairing", method = RequestMethod.POST)
    private String pairing(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String lightUid = params.getString("uid");
        JSONObject firmware = JSONObject.parseObject(params.getString("firmware"));
        String deviceModel = firmware.getString("model");
        String name = firmware.getString("name");
        String mac = firmware.getString("mac");
        String version = firmware.getString("version");
        String channel = firmware.getString("channel");
        String deviceId = "";
        String isLogin = "";
        try {
            LightActive lightActive = pairingService.findActiveInfoByMacAddress(mac);
            if (lightActive == null) {
                deviceId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
                LightActive lightActive2 = new LightActive(deviceId, deviceModel, name, mac, version, channel, null);
                pairingService.saveActiveInfo(lightActive2);
            } else {
                deviceId = lightActive.getDeviceId();
            }
            LightPairing lightPairing = pairingService.findPairingInfoByLightUid(lightUid);
            if (lightPairing == null) {
                pairingService.savePairingInfo(lightUid, deviceId);
            } else {
                String deviceIds = lightPairing.getName();
                JSONArray jsonArray = JSONArray.fromObject(deviceIds);
                if (!jsonArray.contains(deviceId)) {
                    jsonArray.add(deviceId);
                    pairingService.updatePairingInfo(lightUid, jsonArray.toString());
                }
            }
            isLogin = pairingService.findLoginState(lightUid);
            if ("1".equals(isLogin)) {
                LightBinding lightBinding = pairingService.findBindingInfoByDeviceId(deviceId);
                if (lightBinding == null) {
                    LightBinding lightBinding2 = new LightBinding(deviceId, mac, lightUid,null,new Date(),null, null);
                    pairingService.saveBindingInfo(lightBinding2);
                } else if (!lightUid.equals(lightBinding.getLightUid())) {
                    pairingService.updateBindingInfo(lightUid, deviceId);
                    if(lightBinding.getBossUid()!=null){
                        pairingService.updateBossBindingInfo(lightBinding.getBossUid());
                    }
                }
            }
            PairingLog pairingLog = new PairingLog(0, lightUid, isLogin, deviceId, firmware.toJSONString(),
                    "pairing_success", new Date(), "");
            pairingService.savePairingLog(pairingLog);
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("result_message", "设备配对成功");
            model.put("deviceId", deviceId);
        } catch (Exception e) {
            PairingLog pairingLog = new PairingLog(0, lightUid, isLogin, deviceId, firmware.toJSONString(),
                    "pairing_fail", new Date(), "");
            pairingService.savePairingLog(pairingLog);
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
            model.put("result_message", "设备配对失败");
            model.put("deviceId", "");
        }
        return null;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private String delete(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String lightUid = params.getString("uid");
        String deviceId = params.getString("deviceId");
        String isLogin = "";
        try {
            LightPairing lightPairing = pairingService.findPairingInfoByLightUid(lightUid);
            if (lightPairing != null) {
                String deviceIds = lightPairing.getName();
                JSONArray jsonArray = JSONArray.fromObject(deviceIds);
                if (jsonArray.contains(deviceId)) {
                    jsonArray.remove(deviceId);
                    pairingService.updatePairingInfo(lightUid, jsonArray.toString());
                }
            }
            isLogin = pairingService.findLoginState(lightUid);
            if ("1".equals(isLogin)) {
                LightBinding lightBinding = pairingService.findBindingInfoByDeviceId(deviceId);
                if (lightBinding != null) {
                    pairingService.deleteBindingInfo(deviceId);
                    if(lightBinding.getBossUid()!=null){
                        pairingService.updateBossBindingInfo(lightBinding.getBossUid());
                    }
                }
            }
            PairingLog pairingLog = new PairingLog(0, lightUid, isLogin, deviceId, "",
                    "delete_success", new Date(), "");
            pairingService.savePairingLog(pairingLog);
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("result_message", "设备删除成功");
        } catch (Exception e) {
            PairingLog pairingLog = new PairingLog(0, lightUid, isLogin, deviceId, "",
                    "delete_fail", new Date(), "");
            pairingService.savePairingLog(pairingLog);
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
            model.put("result_message", "设备删除失败");
        }
        return null;
    }


    @Autowired
    public void setPairingService(PairingService pairingService) {
        this.pairingService = pairingService;
    }

}

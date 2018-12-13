package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.FileRecord;
import com.tpadsz.after.entity.NewestFile;
import com.tpadsz.after.entity.UpdateInfo;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.UpdateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 淘宝客升级接口
 */
@Controller
@RequestMapping("/tpadsz_update")
public class UpdateController extends BaseDecodedController {

    @Resource
    UpdateService updateService;

    @RequestMapping(value = "/update_tbk",method = RequestMethod.POST)
    public void updateTbk(@ModelAttribute("decodedParams") JSONObject params, ModelMap model){
        UpdateInfo updateInfo ;
        try {
            updateInfo= setUpdateInfo(params);
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("data", updateInfo);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        }
    }


    public UpdateInfo setUpdateInfo(JSONObject params){
        UpdateInfo updateInfo = new UpdateInfo();
        String appId = params.getString("appId");
        String versionCode = params.getString("versionCode");
        NewestFile newestFile = updateService.getNewestFile(appId);
        if (Integer.parseInt(versionCode) < newestFile.getVersionCode()) {
            FileRecord fileRecord = updateService.getFileRecords(appId, newestFile.getVersionCode());
            updateInfo.setUpdate(true);
            updateInfo.setApkUrl(fileRecord.getPath());
            updateInfo.setVersionCode(fileRecord.getVersionCode());
            updateInfo.setVersion(fileRecord.getVersionName());
            updateInfo.setSize(fileRecord.getSize());
            updateInfo.setMd5(fileRecord.getMd5());
            updateInfo.setUpdateLog(fileRecord.getUpdateLog());
            updateInfo.setForce(fileRecord.getForceUpdate() == 1);
        } else {
            updateInfo.setUpdate(false);
        }
        updateInfo.setResultCode("000");
        return updateInfo;
    }


}

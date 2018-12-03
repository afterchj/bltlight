package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.ShareLog;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.TbkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/tbk")
public class TbkController extends BaseDecodedController {

    private TbkService tbkService;

    @RequestMapping(value = "/shareLog", method = RequestMethod.POST)
    private String shareLog(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String num_iid = params.getString("num_iid");
        String uid = params.getString("uid");
        String adzone_id = params.getString("adzone_id");
        String goods_share_message = params.getString("goods_share_message");
        try {
            ShareLog shareLog = new ShareLog(UUID.randomUUID().toString().replaceAll("-", ""), num_iid, uid,
                    adzone_id, goods_share_message, new Date(), "");
            tbkService.saveShareLog(shareLog);
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("result_message", ResultDict.SUCCESS.getValue());
        } catch (Exception e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
            model.put("result_message", ResultDict.SYSTEM_ERROR.getValue());
        }
        return null;
    }

    @RequestMapping(value = "/search/coins", method = RequestMethod.POST)
    private void searchCoins(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String uid = params.getString("uid");
        String avail = tbkService.findAvail(uid);


    }




    @Autowired
    public void setTbkService(TbkService tbkService) {
        this.tbkService = tbkService;
    }

}

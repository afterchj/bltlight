package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.api.RecordBillService;
import com.tpadsz.after.entity.dd.ResultDict;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/8/27.
 */

@Controller
@RequestMapping(value = "/show")
public class RecordChargeController extends BaseDecodedController {

    @Autowired
    private RecordBillService billService;

    @RequestMapping(value = "/ytdCharge")
    public void showYTDCharge(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {

        String result = ResultDict.SUCCESS.getCode();
        String msg = ResultDict.SUCCESS.getValue();

        String uid = params.getString("uid");
        Map map = billService.getSumCharge(uid);
        if (StringUtils.isEmpty(uid)) {
            result = ResultDict.UID_NOT_EXIST.getCode();
            msg = ResultDict.UID_NOT_EXIST.getValue();
        }
        if (map == null) {
            result = ResultDict.RECORD_NULL.getCode();
            msg = ResultDict.RECORD_NULL.getValue();
        } else {
            model.put("chargeInfo", map);
        }
        model.put("result", result);
        model.put("result_message", msg);

    }

    @RequestMapping(value = "/listCharge")
    public void showChargeList(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String result = ResultDict.SUCCESS.getCode();
        String msg = ResultDict.SUCCESS.getValue();
        String uid = params.getString("uid");
        List<Map> list = billService.getChargeList(uid);
        if (StringUtils.isEmpty(uid)) {
            result = ResultDict.UID_NOT_EXIST.getCode();
            msg = ResultDict.UID_NOT_EXIST.getValue();
        }
        if (list == null) {
            result = ResultDict.RECORD_NULL.getCode();
            msg = ResultDict.RECORD_NULL.getValue();
        } else {
            model.put("chargeList", list);
        }
        model.put("result", result);
        model.put("result_message", msg);
    }

}

package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.CoinsInfo;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.ShareLog;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.TbkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/tbk")
public class TbkController extends BaseDecodedController {

    private TbkService tbkService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    private String searchCoins(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String uid = params.getString("uid");
        try {
            int avail = tbkService.findAvail(uid);
            int todayEcoins = tbkService.findTodayEcoins(uid);
            int presentMonthEcoins = tbkService.findPresentMonthEcoins(uid);
            int lastMonthEcoins = tbkService.findLastMonthEcoins(uid);
            int lastMonthCoins = tbkService.findLastMonthCoins(uid);
            int consume = tbkService.findConsumeFromPayOrder(uid);
            int todayOrders = tbkService.findTodayOrders(uid);
            int yesterdayOrders = tbkService.findYesterdayOrders(uid);
            int yesterdayEcoins = tbkService.findYesterdayEcoins(uid);
            CoinsInfo coinsInfo = new CoinsInfo((float)avail/1000,(float)todayEcoins/1000,(float)presentMonthEcoins/1000,(float)lastMonthEcoins/1000,(float)lastMonthCoins/1000,(float)consume/1000,todayOrders,yesterdayOrders,(float)yesterdayEcoins/1000);
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("coinsInfo", coinsInfo);
        }catch (Exception e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        }
        return null;
    }


    @Autowired
    public void setTbkService(TbkService tbkService) {
        this.tbkService = tbkService;
    }

}

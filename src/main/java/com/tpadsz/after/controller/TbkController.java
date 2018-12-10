package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.tpadsz.after.entity.*;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.TbkService;
import com.tpadsz.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/tbk")
public class TbkController extends BaseDecodedController {
    private TbkService tbkService;
    public static final int PAGE_SIZE = 50;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = "/shareLog", method = RequestMethod.POST)
    private String shareLog(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String num_iid = params.getString("num_iid");
        String uid = params.getString("uid");
        String goods_share_message = params.getString("goods_share_message");
        try {
            ShareLog shareLog = tbkService.findIdFromHipriceLog(uid,num_iid);
            JSONObject jsonObject=JSONObject.parseObject(shareLog.getGoods_share_message());
            if("000".equals(jsonObject.get("code"))){
                tbkService.saveShareLog(num_iid, uid,shareLog.getId(),goods_share_message);
            };
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
            CoinsInfo coinsInfo = new CoinsInfo((float) avail / 1000, (float) todayEcoins / 1000, (float)
                    presentMonthEcoins / 1000, (float) lastMonthEcoins / 1000, (float) lastMonthCoins / 1000, (float)
                    consume / 1000, todayOrders, yesterdayOrders, (float) yesterdayEcoins / 1000);
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("coinsInfo", coinsInfo);
        } catch (Exception e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        }
        return null;
    }


    @RequestMapping(value = "/search/incomeDetails/p_{pageNo}", method = RequestMethod.POST)
    private String loglistOfIncome(@ModelAttribute("decodedParams") JSONObject params, @PathVariable("pageNo")
            Integer pageNo, ModelMap model) {
        String uid = params.getString("uid");
        try {
            List<DailyAccount> dailyAccounts = tbkService.findLoglistOfIncome(uid, (pageNo - 1) * PAGE_SIZE,PAGE_SIZE);
            List<TradeLog> incomelogsVo = Lists.newArrayList();
            for (DailyAccount log : dailyAccounts) {
                TradeLog incomelog = BeanMapper.map(log, TradeLog.class);
                incomelog.setTrade_name("佣金结算收益");
                incomelog.setPrice((float) log.getEarn() / 1000);
                if (log.getDate() != null) {
                    incomelog.setDate(sdf.format(log.getDate()));
                }
                incomelogsVo.add(incomelog);
            }
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("incomelogs", incomelogsVo);
        } catch (Exception e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        }
        return null;
    }


    @RequestMapping(value = "/search/consumeDetails/p_{pageNo}", method = RequestMethod.POST)
    private String loglistOfConsume(@ModelAttribute("decodedParams") JSONObject params, @PathVariable("pageNo")
            Integer pageNo, ModelMap model) {
        String uid = params.getString("uid");
        try {
            List<DailyAccount> dailyAccounts = tbkService.findLoglistOfConsume(uid, (pageNo - 1) * PAGE_SIZE,PAGE_SIZE);
            List<TradeLog> consumelogsVo = Lists.newArrayList();
            for (DailyAccount log : dailyAccounts) {
                TradeLog consumelog = BeanMapper.map(log, TradeLog.class);
                consumelog.setTrade_name("微信提现");
                consumelog.setPrice((float) log.getConsume() / 1000);
                if (log.getDate() != null) {
                    consumelog.setDate(sdf.format(log.getDate()));
                }
                consumelogsVo.add(consumelog);
            }
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("consumelogs", consumelogsVo);
        } catch (Exception e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        }
        return null;
    }


    @RequestMapping(value="/tbkSave", method=RequestMethod.POST)
    public String tbksearch(@ModelAttribute("decodedParams")JSONObject params, ModelMap model){
        try {
            String uid = params.getString("uid");
            String searchName = params.getString("searchName");
            tbkService.tbkSave(uid,searchName);
            model.put("result", ResultDict.SUCCESS.getCode());
        } catch (Exception e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        }
        return null;
    }

    @Autowired
    public void setTbkService(TbkService tbkService) {
        this.tbkService = tbkService;
    }

}

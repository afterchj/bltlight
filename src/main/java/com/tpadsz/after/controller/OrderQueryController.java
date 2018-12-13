package com.tpadsz.after.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.OrderFromLog;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.OrderFromService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: blt-light
 * @description: 订单查询
 * @author: Mr.Ma
 * @create: 2018-11-27 13:21
 **/
@Controller
@RequestMapping("/orderQuery")
public class OrderQueryController extends BaseDecodedController{

    @Resource
    private  OrderFromService orderFromService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public void page(@ModelAttribute("decodedParams") JSONObject
                                 params, ModelMap model) {
        String uid = params.getString("uid");
        Integer pageNum=params.getInteger("pageNum");
        Integer status = params.getInteger("status");
        if (StringUtils.isBlank(uid)||pageNum==null||status==null){
            model.put("result", ResultDict.PARAMS_BLANK.getCode());
            return;
        }
        int size=100;
        int page=size*(pageNum-1);
        OrderFrom orderFrom1 = new OrderFrom();
        orderFrom1.setPage(page);
        orderFrom1.setSize(size);
        orderFrom1.setUid(uid);
        orderFrom1.setStatus(status);
        List<OrderFrom> allOrderFromByUid = orderFromService.selectAll(orderFrom1);
        if (allOrderFromByUid.size()==0){
            model.put("orderFroms",allOrderFromByUid);
            model.put("result", ResultDict.SUCCESS.getCode());
            return;
        }
        List<OrderFrom> orderFromList = new ArrayList<>();
        Long numId;
        ShopInfo shopInfo;
        for (OrderFrom orderFrom:allOrderFromByUid){
            numId=orderFrom.getNum_iid();
            //插入图片
            shopInfo = orderFromService.findShopImageByNumIid
                    (String.valueOf(numId));
            if (shopInfo!=null){
                orderFrom.setPrice(String.valueOf(shopInfo.getZk_final_price()));
                if (shopInfo.getPict_url()!=null){
                    orderFrom.setImage(shopInfo.getPict_url());
                }
                if (shopInfo.getRate_touid()!=null){
                    orderFrom.setRate_touid(shopInfo.getRate_touid());
                    orderFrom.setPrePrice((orderFrom.getRate_touid())*(orderFrom.getItem_num()));
                }
            }
            orderFromList.add(orderFrom);
        }
        //订单日志记录
        OrderFromLog orderFromLog = new OrderFromLog();
        orderFromLog.setStatus(status);
        orderFromLog.setPageNum(pageNum);
        orderFromLog.setUid(uid);
        orderFromService.insertOrderLog(orderFromLog);
        model.put("orderFroms",orderFromList);
        model.put("result", ResultDict.SUCCESS.getCode());
    }

}
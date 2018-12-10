package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.OrderFromService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
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
        PageInfo pageInfos = orderFromService.findAll(uid,pageNum,status);
        List<OrderFrom> orderFroms = pageInfos.getList();
        for (OrderFrom orderFrom:orderFroms){
            Long numId=orderFrom.getNum_iid();
            //插入图片
            ShopInfo shopInfo = orderFromService.findShopImageByNumIid
                    (String.valueOf(numId));
            if (shopInfo!=null){
                if (shopInfo.getPict_url()!=null){
                    orderFrom.setImage(shopInfo.getPict_url());
                }
                if (shopInfo.getRate_touid()!=null){
                    orderFrom.setRate_touid(shopInfo.getRate_touid());
                    orderFrom.setPrePrice((orderFrom.getRate_touid())*(orderFrom.getItem_num()));
                }
            }
        }
        model.put("orderFroms",pageInfos.getList());
        model.put("result", ResultDict.SUCCESS.getCode());
    }
}
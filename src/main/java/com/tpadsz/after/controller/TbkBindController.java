package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.entity.dd.CommonParam;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.ShopService;
import com.tpadsz.after.service.TbkBindService;
import com.tpadsz.after.util.TaoBaoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/26.
 */

@Controller
@RequestMapping("/tbk")
public class TbkBindController extends BaseDecodedController {


    @Autowired
    private TbkBindService bindService;

    @Autowired
    private ShopService shopService;

    @RequestMapping("/shopShare")
    public void shopShare(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        String result = ResultDict.SUCCESS.getCode();
        String msg = ResultDict.SUCCESS.getValue();
        String para = params.getString("num_iid");
        String uid = params.getString("uid");
        Pid info = bindService.getPidInfo();
        Pid pud = bindService.getPid(uid);
        Pid pid = pud == null ? info : pud;
        shopService.setKey(pid.getAdzone_id(), uid);
        int pkey = pid.getPkey();
        ShopInfo shop = new ShopInfo();
        shop.setPkey(pkey);
        shop.setUid(uid);
        shop.setNum_iid(para);
        Map map = new HashMap();
        map.put("vekey", CommonParam.VEKEY.getValue());
        map.put("para", para);
        map.put("pid", pid.getPid());
        map.put("detail", "1");
        JSONObject jsonObject = new JSONObject();
        JSONObject json = null;
        try {
            json = TaoBaoUtil.getHICPIInfo(map);
        } catch (Exception e) {
            result = ResultDict.SYSTEM_ERROR.getCode();
            msg = ResultDict.SYSTEM_ERROR.getValue();
            jsonObject.put("code", result);
            jsonObject.put("msg", msg);
            shop.setResult_info(jsonObject.toJSONString());
            shopService.saveShop(shop);
            System.out.println("result=" + shop.getOut_result());
        }
        String error = json.getString("error");
        if (StringUtils.isNotEmpty(error)) {
            result = ResultDict.EXPIRY_ERROR.getCode();
            msg = ResultDict.EXPIRY_ERROR.getValue();
        } else {
            shop = TaoBaoUtil.formatStr(json);
            shop.setPkey(pkey);
            shop.setUid(uid);
            shop.setGoods_info(json.toJSONString());
            model.put("data", TaoBaoUtil.getData(json));
        }
        model.put("result", result);
        model.put("result_message", msg);
        jsonObject.put("code", result);
        jsonObject.put("msg", msg);
        shop.setResult_info(jsonObject.toJSONString());
        shopService.saveShop(shop);
    }
}

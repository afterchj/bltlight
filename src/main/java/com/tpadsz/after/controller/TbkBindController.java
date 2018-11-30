package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.dao80.TbkBindDao;
import com.tpadsz.after.entity.Person;
import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.entity.dd.CommonParam;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.TbkBindService;
import com.tpadsz.after.util.HttpClientUtil;
import com.tpadsz.after.util.TaoBaoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/26.
 */

@Controller
@RequestMapping("/tbk")
public class TbkBindController extends BaseDecodedController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TbkBindService bindService;

    @ResponseBody
    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    public Person getPerson() {
        System.out.println("getPerson...");
        return (Person) redisTemplate.opsForValue().get("person2");
    }

    @ResponseBody
    @RequestMapping(value = "/getPid", method = RequestMethod.GET)
    public List<Pid> getPid() {
        System.out.println("getPid...");
        return bindService.getPids();
    }

    @RequestMapping("/shopShare")
    public void shopShare(@ModelAttribute("decodedParams") JSONObject params, ModelMap model) {
        System.out.println("params=" + params);
        String result = ResultDict.SUCCESS.getCode();
        String msg = ResultDict.SUCCESS.getValue();
        String para = params.getString("num_iid");
        String uid = params.getString("uid");
        Pid pid = bindService.getPid(uid);
        int pkey = pid.getPkey();
        ShopInfo shop = new ShopInfo();
        shop.setPkey(pkey);
        shop.setUid(uid);
        shop.setNum_iid(para);
        System.out.println("pid=" + JSON.toJSONString(pid));
        Map map = new HashMap();
        map.put("vekey", CommonParam.VEKEY.getValue());
        map.put("para", para);
        map.put("pid", pid.getPid());
        map.put("detail", "1");
        JSONObject jsonObject = new JSONObject();
        try {
            String json = TaoBaoUtil.getHICPIInfo(map);
            jsonObject.put("code", result);
            jsonObject.put("msg", msg);
            shop = TaoBaoUtil.formatStr(json);
            shop.setPkey(pkey);
            shop.setUid(uid);
            shop.setResult_info(jsonObject.toJSONString());
            shop.setGoods_info(json);
            System.out.println("shop=" + JSON.toJSONString(shop));
            model.put("data", TaoBaoUtil.getData(json));
        } catch (Exception e) {
            result = ResultDict.SYSTEM_ERROR.getCode();
            msg = ResultDict.SYSTEM_ERROR.getValue();
            jsonObject.put("code", result);
            jsonObject.put("msg", msg);
            shop.setResult_info(jsonObject.toJSONString());
        } finally {
            model.put("result", result);
            model.put("result_message", msg);
            bindService.insertHiPriceLog(shop);
        }
    }

}

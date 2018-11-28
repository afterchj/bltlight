package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.dao80.TbkBindDao;
import com.tpadsz.after.entity.Person;
import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.entity.dd.CommonParam;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.TbkBindService;
import com.tpadsz.after.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/26.
 */

@RestController
@RequestMapping("/tbk")
public class TbkBindController extends BaseDecodedController{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TbkBindService bindService;



    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    public Person getPerson() {
        System.out.println("getPerson...");
        return (Person) redisTemplate.opsForValue().get("person2");
    }

    @RequestMapping(value = "/getPid", method = RequestMethod.GET)
    public List<Pid> getPid() {
        System.out.println("getPid...");
        return bindService.getPids();
    }

    @RequestMapping("/shopShare")
    public String shopShare(@ModelAttribute("decodedParams") JSONObject params, ModelMap model){
        String result = ResultDict.SUCCESS.getCode();
        String msg = "";

        Map map = new HashMap();
        map.put("vekey", CommonParam.VEKEY.getValue());
        map.put("para", "558825175392");
        map.put("pid", "mm_43238250_191900396_54300950044");
        map.put("notkl", "1");
        map.put("detail", "1");
        map.put("noshortlink  ", "1");
        try {
            String ret1 = HttpClientUtil.httpGet(CommonParam.VEHICPI.getValue(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.put("result", result);
        model.put("result_message", msg);
        return "";
    }
}

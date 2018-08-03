package com.tpadsz.after;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
public class Main {

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("result", "000");
        object.put("msg", "成功");
        System.out.println(JSON.toJSONString(object));
    }
}

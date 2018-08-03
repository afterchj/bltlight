package com.tpadsz.after.controller;


import com.tpadsz.after.util.Constants;
import com.tpadsz.after.util.WSClientUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
@Controller
public class HomeController {


    private Logger log = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/webchat/{username}")
    @ResponseBody
    public void webchat(@PathVariable String username, HttpSession session) {
        System.out.println("username=" + username);
        session.setAttribute(Constants.SESSION_USERNAME.value(), username);
    }

    @ResponseBody
    @RequestMapping(value = "/switch", produces = "application/json; charset=utf-8")
    private String switchController(HttpServletRequest req, HttpServletResponse res) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        String params = req.getParameter("switchFlag");
        System.out.println("params=" + params);
        String msg = "{\"result\":\"000\",\"msg\":\"成功\"}";
        if (params == null) {
            params = msg;
        }
        final String finalParams = params;
        new Thread(new Runnable() {
            public void run() {
                WSClientUtil.sendMsg(finalParams);
            }
        }).start();
        log.info("show-->" + params);
        System.out.println("params=" + params);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/test", produces = "application/json; charset=utf-8")
    public String test(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        System.out.println("uid=" + uid);
        return "{\"uid\":" + "\"" + uid + "\"}";
    }

    @RequestMapping(value = "/toChat")
    public String toChat(HttpSession session) {
        System.out.println("url=" + "/toChat");
        session.setAttribute(Constants.SESSION_USERNAME.value(), "systemUser");
        return "websocket";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession session) {
        session.setAttribute(Constants.SESSION_USERNAME.value(), "systemUser");
        return "userInfo";
    }

    @RequestMapping(value = "/meshSystem")
    public String toMeshSystem() {
        return "meshSystem";
    }

    @RequestMapping(value = "/productInfo")
    public String toProductInfo() {
        return "productInfo";
    }

    @RequestMapping(value = "/lightInfo")
    public String toLightInfo() {
        return "lightInfo";
    }

    @RequestMapping(value = "/meshInfo")
    public String toMeshInfo() {
        return "meshInfo";
    }

    @RequestMapping(value = "/userInfo")
    public String toUserInfo() {
        return "userInfo";
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut() {
        return "forward:/toChat";
    }
}

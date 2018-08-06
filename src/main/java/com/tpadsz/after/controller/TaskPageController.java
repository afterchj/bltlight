package com.tpadsz.after.controller;

import com.tpadsz.after.util.WSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

/**
 * Created by Administrator on 2016/8/28.
 */
@Controller
public class TaskPageController {

    @RequestMapping("/qrcpa")
    private void qrcpa(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        String params = req.getParameter("params");
        System.out.println("params="+params);
        PrintWriter out = res.getWriter();

        String location1 = "ws://ctc-hq.tpadsz.com/blt_light/websocket";
        URI location = URI.create(location1);
        WSClient ws = new WSClient(location);
        ws.connect(location1);
        String msg = "000";
        out.print(msg);
    }

}

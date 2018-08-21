package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.LightActive;
import com.tpadsz.after.service.PairingService;
import com.tpadsz.after.util.WSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class LightController extends BaseDecodedController{

    private PairingService pairingService;

    @RequestMapping(value="/pairing", method= RequestMethod.POST)
    private String pairing(@ModelAttribute("decodedParams")JSONObject params, ModelMap model) throws ServletException, IOException {
        String searchName = params.getString("searchName");
//        System.out.println("params="+params);
//        PrintWriter out = res.getWriter();
        LightActive lightActive = pairingService.findMacAddress("FC-AA-14-2B-22-FB");


        return null;
    }

    @Autowired
    public void setPairingService(PairingService pairingService) {
        this.pairingService = pairingService;
    }

}

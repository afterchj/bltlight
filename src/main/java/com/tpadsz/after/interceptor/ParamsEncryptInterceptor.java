package com.tpadsz.after.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.ResultDict;
import com.tpadsz.after.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ParamsEncryptInterceptor implements HandlerInterceptor {
    private Logger system = LoggerUtils.SYSTEM;
    private Logger daylog = LoggerUtils.DAYLOG;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String params = request.getParameter("params");
        JSONObject paramMap = new JSONObject();
        if (StringUtils.isNotBlank(params)) {
            try {
                String decodedParams = new String(DESCoder.decrypt(Encodes.decodeBase64(URLDecoder.decode(params, "utf-8"))), "utf-8");
                paramMap = JSONObject.parseObject(decodedParams);
                try {
                    //判断用户是否使用的是虚拟机
                    if (paramMap.containsKey("firmware")) {
                        JSONObject firmware = paramMap.getJSONObject("firmware");
                        Boolean isVM = false;
                        //电压和温度都为0
                        if (!isVM && firmware.containsKey("voltage") && firmware.containsKey("temperature")) {
                            int voltage = firmware.getInteger("voltage");
                            int temperature = firmware.getInteger("temperature");
                            if (voltage == 0 && temperature == 0) {
                                isVM = true;
                            }
                        }
                        //imsi号不为空，imsi号不是46开头，且imsi号不是20404开头，且imsi号不为“0”，不为"000000000000000"
                        if (!isVM && firmware.containsKey("imsi")) {
                            String imsi = firmware.getString("imsi");
                            if (StringUtils.isNotBlank(imsi)
                                    && !imsi.startsWith("46")
                                    && !imsi.startsWith("20404")
                                    && !"0".equals(imsi)
                                    && !"000000000000000".equals(imsi)) {
                                isVM = true;
                            }
                        }
                        //机型为Q8H   Q8  M10(双核）  ONDA_MID  H15  Droid4X-WIN   TianTian  Memu   iToolsVM  virtual_machine 或者机型中含有iphone字样的
                        if (!isVM && firmware.containsKey("model")) {
                            String model = firmware.getString("model");
                            if ("Q8H".equals(model)
                                    || "Q8".equals(model)
                                    || "M10(双核)".equals(model)
                                    || "ONDA_MID".equals(model)
                                    //|| "H15".equals(model)
                                    || "Droid4X-WIN".equals(model)
                                    || "TianTian".equals(model)
                                    || "Memu".equals(model)
                                    || "iToolsVM".equals(model)
                                    || "virtual_machine".equals(model)
                                    || model.contains("iphone")) {
                                isVM = true;
                            }
                        }
                        //fingerprint中含有 vbox   dolphin
                        if (!isVM && firmware.containsKey("fingerprint")) {
                            String fingerprint = firmware.getString("fingerprint");
                            if (fingerprint.contains("vbox") || fingerprint.contains("dolphin")) {
                                isVM = true;
                            }
                        }
                        if (isVM) {
                            String result = "406";
                            String ip = request.getHeader("x-forwarded-for").split(",")[0];
                            logAccess(paramMap, result, null, request.getRequestURI(), ip);
                            Map<String, String> data = new HashMap<>();
                            data.put("result", result);
                            HttpServletUtil.renderText(response, Encryption.encode(JSONObject.toJSONString(data)));
                            return false;
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                request.setAttribute("decodedParams", paramMap);
                return true;
            } catch (Exception e) {
                Map<String, String> data = new HashMap<>();
                data.put("result", ResultDict.PARAMS_NOT_PARSED.getCode());
                HttpServletUtil.renderText(response, Encryption.encode(JSONObject.toJSONString(data)));
                return false;
            }
        } else {
            String result = ResultDict.PARAMS_BLANK.getCode();
            logAccess(null, result, null, request.getRequestURI(), null);
            Map<String, String> data = new HashMap<>();
            data.put("result", result);
            HttpServletUtil.renderText(response, Encryption.encode(JSONObject.toJSONString(data)));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        Object paramMap = modelAndView.getModel().remove("decodedParams");
        String ip = "127.0.0.1";
//        String ip = request.getHeader("x-forwarded-for").split(",")[0];
        logAccess(paramMap, String.valueOf(modelAndView.getModel().get("result")), modelAndView.getModel(), request.getRequestURI(), ip);
        modelAndView.getModel().put("encrypted", true);
    }

    private void logAccess(Object params, String resultCode, Object value, String uri, String ip) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("params", params);
        jsonObj.put("uri", uri);
        jsonObj.put("result", resultCode);
        jsonObj.put("ip", ip);
//        daylog.warn(jsonObj.toJSONString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}

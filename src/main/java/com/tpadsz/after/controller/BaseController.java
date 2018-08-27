package com.tpadsz.after.controller;

import javax.validation.ValidationException;

import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.util.LoggerUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.rpc.RpcException;

public class BaseController {

    protected Logger system = LoggerUtils.SYSTEM;
    protected boolean encryption = false;

    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleException(ValidationException re) {
        ModelAndView mv = new ModelAndView();
        ModelMap model = mv.getModelMap();
        model.put("result", ResultDict.PARAMS_NOT_PARSED.getCode());
        model.put("reason", re.getMessage());
        return mv.addObject("encrypted", encryption);
    }

    @ExceptionHandler(RpcException.class)
    public ModelAndView handleException(RpcException re) {
        if (re.getCause() instanceof ValidationException) {
            return handleException((ValidationException) re.getCause());
        } else {
            return handleException((RuntimeException) re);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleException(RuntimeException re) {
        ModelAndView mv = new ModelAndView();
        ModelMap model = mv.getModelMap();
        system.error("严重 :: ", re);
        model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        return mv.addObject("encrypted", encryption);
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable t) {
        ModelAndView mv = new ModelAndView();
        ModelMap model = mv.getModelMap();
        system.error("严重 :: ", t);
        model.put("result", ResultDict.SYSTEM_ERROR.getCode());
        return mv.addObject("encrypted", encryption);
    }

}

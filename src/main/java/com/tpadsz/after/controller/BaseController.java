package com.tpadsz.after.controller;

import com.tpadsz.after.util.LoggerUtils;
import org.apache.log4j.Logger;

public class BaseController {

    protected Logger system = LoggerUtils.SYSTEM;
    protected boolean encryption = false;

//    @ExceptionHandler(ValidationException.class)
//    public ModelAndView handleException(ValidationException re) {
//        ModelAndView mv = new ModelAndView();
//        ModelMap model = mv.getModelMap();
//        model.put("result", ResultDict.PARAMS_NOT_PARSED.getCode());
//        model.put("reason", re.getMessage());
//        return mv.addObject(Constants.BACKGROUND_ENCRPTION_KEY, encryption);
//    }
//
//    @ExceptionHandler(RpcException.class)
//    public ModelAndView handleException(RpcException re) {
//        if (re.getCause() instanceof ValidationException) {
//            return handleException((ValidationException) re.getCause());
//        } else {
//            return handleException((RuntimeException) re);
//        }
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView handleException(RuntimeException re) {
//        ModelAndView mv = new ModelAndView();
//        ModelMap model = mv.getModelMap();
//        system.error("严重 :: ", re);
//        model.put("result", ResultDict.SYSTEM_ERROR.getCode());
//        return mv.addObject(Constants.BACKGROUND_ENCRPTION_KEY, encryption);
//    }
//
//    @ExceptionHandler(Throwable.class)
//    public ModelAndView handleException(Throwable t) {
//        ModelAndView mv = new ModelAndView();
//        ModelMap model = mv.getModelMap();
//        system.error("严重 :: ", t);
//        model.put("result", ResultDict.SYSTEM_ERROR.getCode());
//        return mv.addObject(Constants.BACKGROUND_ENCRPTION_KEY, encryption);
//    }

}

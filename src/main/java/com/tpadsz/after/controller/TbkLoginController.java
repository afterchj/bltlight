package com.tpadsz.after.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.TbkLoginLog;
import com.tpadsz.after.entity.TbkUser;
import com.tpadsz.after.entity.dd.ResultDict;
import com.tpadsz.after.service.TbkService;
import com.tpadsz.exception.SystemAlgorithmException;
import com.tpadsz.uic.user.api.ValidationManager;
import com.tpadsz.uic.user.api.exception.InvalidValidationCodeException;
import com.tpadsz.uic.user.api.exception.UserAlreadyRegisteredException;
import com.tpadsz.uic.user.api.exception.ValidationNotSendedException;
import com.tpadsz.uic.user.api.vo.MobileValidateOffer;
import com.tpadsz.uic.user.api.vo.MobileVerifyOffer;
import com.tpadsz.uic.user.api.vo.MobileVerifyType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@Controller("tbkLoginController")
@RequestMapping("/tbk")
public class TbkLoginController extends BaseDecodedController {

    @Resource
    private TbkService tbkService;
    @Resource
    private ValidationManager validationManager;

    static final String URL = "http://www.uichange.com/boss-locker/tbk/";

    /**
     * 用户安装app记录
     *
     * @param params
     * @param model
     * @return
     */
    @RequestMapping(value = "/handy-register", method = RequestMethod.POST)
    public String handyRegister(@ModelAttribute("decodedParams") JSONObject
                                        params, ModelMap model) {
        TbkUser firmware = JSONObject.parseObject(params.getString
                ("firmware"), TbkUser.class);
        String id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
        firmware.setId(id);
        firmware.setCreate_date(new Date());
        firmware.setAppid("12");
        tbkService.saveHandyRegister(firmware);
        TbkUser tbkUser = tbkService.findTbkUserById(id);
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message", ResultDict.SUCCESS.getValue());
        model.put("user", tbkUser);
        return null;
    }

    /**
     * 手机登录发送验证码
     *
     * @param params
     * @param model
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public void verify(@ModelAttribute("decodedParams") JSONObject
                               params, ModelMap model) throws
            UserAlreadyRegisteredException, SystemAlgorithmException,
            ValidationNotSendedException {
        String mobile = params.getString("mobile");
        if (StringUtils.isBlank(mobile)) {
            model.put("result", ResultDict.PARAMS_BLANK.getCode());
            model.put("result_message", ResultDict.PARAMS_BLANK.getValue());
        }
        TbkUser tbkUser = tbkService.findTbkUserByMobile(mobile);
        TbkLoginLog tbkLoginLog = new TbkLoginLog();
        if (tbkUser == null) {
            //没有注册过
            TbkUser firmware = JSONObject.parseObject(params.getString
                    ("firmware"), TbkUser.class);
            String uid = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
            String id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
            firmware.setId(uid);
            firmware.setCreate_date(new Date());
            firmware.setAppid("12");
            firmware.setMobile(mobile);
            tbkService.save(firmware);
            //记录注册日志
            tbkLoginLog.setId(id);
            tbkLoginLog.setCreate_date(new Date());
            tbkLoginLog.setUid(uid);
            tbkLoginLog.setBehavior(URL + "verify.json");
            tbkLoginLog.setMobile(mobile);
            tbkService.saveLoginLog(tbkLoginLog);
        }
        try {
            validationManager.updateCheckOut2(new MobileValidateOffer
                    (mobile, "12"));
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("result_message", ResultDict.SUCCESS.getValue());
        } catch (SystemAlgorithmException | ValidationNotSendedException e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
            model.put("result_message", ResultDict.SYSTEM_ERROR.getValue());
        }
    }

    /**
     * 登录 记录登录日志
     *
     * @param params
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("decodedParams") JSONObject
                                params, ModelMap model) {
        String mobile = params.getString("mobile");
        String code = params.getString("code");
        try {
            validationManager.checkValidation(new MobileVerifyOffer(mobile,
                    MobileVerifyType.UPDATE, code, "12"));
            TbkUser tbkUser = tbkService.findTbkUserByMobile(mobile);
            tbkUser.setIcon("");
            TbkLoginLog tbkLoginLog = new TbkLoginLog();
            String id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
            tbkLoginLog.setId(id);
            tbkLoginLog.setUid(tbkUser.getId());
            tbkLoginLog.setLogin_date(new Date());
            tbkLoginLog.setBehavior(URL + "login.json");
            tbkLoginLog.setMobile(mobile);
            //记录登录日志
            tbkService.saveLoginLog(tbkLoginLog);
            model.put("user", tbkUser);
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("result_message", ResultDict.SUCCESS.getValue());
            model.put("check", true);
        } catch (InvalidValidationCodeException e) {
            model.put("result", ResultDict.SUCCESS.getCode());
            model.put("result_message", ResultDict.SUCCESS.getValue());
            model.put("check", false);
        } catch (SystemAlgorithmException e) {
            model.put("result", ResultDict.SYSTEM_ERROR.getCode());
            model.put("result_message", ResultDict.SYSTEM_ERROR.getValue());
        }
        return null;
    }

    /**
     * 退出登录 记录退出登录时间
     *
     * @param params
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public String loginOut(@ModelAttribute("decodedParams") JSONObject
                                   params, ModelMap model) {
        String id = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
        String uid = params.getString("uid");
//        String mobile = params.getString("mobile");
        Date loginOutDate = new Date();
        TbkLoginLog tbkLoginLog = new TbkLoginLog();
        tbkLoginLog.setId(id);
        tbkLoginLog.setLoginout_date(loginOutDate);
        tbkLoginLog.setUid(uid);
//        tbkLoginLog.setMobile(mobile);
        tbkLoginLog.setBehavior(URL + "loginOut.json");
        //记录退出登录时间日志
        tbkService.saveLoginLog(tbkLoginLog);
        model.put("result", ResultDict.SUCCESS.getCode());
        model.put("result_message", ResultDict.SUCCESS.getValue());
        return null;
    }

}

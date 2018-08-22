package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao.AppOperateDao;
import com.tpadsz.after.entity.BluethoothConnect;
import com.tpadsz.after.entity.LightOperation;
import com.tpadsz.after.entity.OpenApp;
import com.tpadsz.after.service.AppOperateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-08-21 13:23
 **/
@Service("appOperateService")
public class AppOperateServiceImpl implements AppOperateService{

    @Resource
    private AppOperateDao appOperateDao;

    public void openAppLog(OpenApp openApp) {
        appOperateDao.openAppLog(openApp);
    }

    public void connectToBluetoothLog(BluethoothConnect bluethoothConnect) {
        appOperateDao.connectToBluetoothLog(bluethoothConnect);
    }

    public void lightOperationLog(LightOperation lightOperation) {
        appOperateDao.lightOperationLog(lightOperation);
    }
}

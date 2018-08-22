package com.tpadsz.after.dao;

import com.tpadsz.after.entity.BluethoothConnect;
import com.tpadsz.after.entity.LightOperation;
import com.tpadsz.after.entity.OpenApp;
import org.springframework.stereotype.Repository;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-08-21 13:22
 **/
@Repository
public interface AppOperateDao {

    void openAppLog(OpenApp openApp);//打开app日志记录

    void connectToBluetoothLog(BluethoothConnect bluethoothConnect);//灯控app设备蓝牙连接日志记录

    void lightOperationLog(LightOperation lightOperation);//灯控app设备操作记录
}

package com.tpadsz.after.dao;


import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightCharge;
import com.tpadsz.after.entity.LightOperation;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/8/27.
 */
public interface RecordBillDao {

    Map getByDeviceId(String deviceId);

    Map getByLightUid(String uid);

    void insetBill(Map lightCharge);

    List<Map> getChargeList(String uid);

    Map getSumCharge(String uid);
}

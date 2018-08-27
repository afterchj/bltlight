package com.tpadsz.after.service;

import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightCharge;
import com.tpadsz.after.entity.LightOperation;

import java.util.Map;

/**
 * Created by hongjian.chen on 2018/8/27.
 */
public interface RecordBillService {
    LightBinding getByUid(String uid);

    LightOperation getByLightUid(String light_uid);

    void insetBill(LightBinding binding, LightOperation operation,LightCharge lightCharge);

    Map getChargeList(String uid);

    Map getSumCharge(String uid);
}

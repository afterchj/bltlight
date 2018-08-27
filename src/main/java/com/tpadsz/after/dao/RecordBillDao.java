package com.tpadsz.after.dao;


import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightCharge;
import com.tpadsz.after.entity.LightOperation;

import java.util.Map;

/**
 * Created by hongjian.chen on 2018/8/27.
 */
public interface RecordBillDao {

    LightBinding getByUid(String uid);

    LightOperation getByLightUid(String light_uid);

    void insetBill(LightCharge lightCharge);

    Map getChargeList(String uid);

    Map getSumCharge(String uid);
}

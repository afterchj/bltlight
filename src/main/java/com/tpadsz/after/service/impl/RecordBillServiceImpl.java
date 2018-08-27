package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao.RecordBillDao;
import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightCharge;
import com.tpadsz.after.entity.LightOperation;
import com.tpadsz.after.service.RecordBillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hongjian.chen on 2018/8/27.
 */

@Service
public class RecordBillServiceImpl implements RecordBillService {

    @Autowired
    private RecordBillDao billDao;

    @Override
    public LightBinding getByUid(String uid) {
        return billDao.getByUid(uid);
    }

    @Override
    public LightOperation getByLightUid(String light_uid) {
        return billDao.getByLightUid(light_uid);
    }

    @Override
    public void insetBill(LightBinding binding, LightOperation operation, LightCharge lightCharge) {
        if (isBinding(binding) && isBLTOperation(operation)) {
            billDao.insetBill(lightCharge);
        }
    }

    @Override
    public Map getChargeList(String uid) {
        return billDao.getChargeList(uid);
    }

    @Override
    public Map getSumCharge(String uid) {
        return billDao.getSumCharge(uid);
    }

    public boolean isBinding(LightBinding lightBinding) {
        boolean flag = false;
        if (lightBinding != null) {
            if (StringUtils.isNotEmpty(lightBinding.getBossUid()) && StringUtils.isNotEmpty(lightBinding.getLightUid()) && StringUtils.isNotEmpty(lightBinding.getDeviceId())) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isBLTOperation(LightOperation lightOperation) {
        boolean flag = false;
        if (lightOperation != null && lightOperation.getIsRegister().equals(1)) {
            flag = true;
        }
        return flag;
    }
}

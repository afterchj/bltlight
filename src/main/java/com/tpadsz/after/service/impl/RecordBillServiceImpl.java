package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao.RecordBillDao;
import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightCharge;
import com.tpadsz.after.entity.LightOperation;
import com.tpadsz.after.service.RecordBillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/8/27.
 */

@Service
public class RecordBillServiceImpl implements RecordBillService {

    @Autowired
    private RecordBillDao billDao;

    @Override
    public LightBinding getByUid(String deviceId) {
        return billDao.getByUid(deviceId);
    }

    @Override
    public LightOperation getByLightUid(String uid) {
        return billDao.getByLightUid(uid);
    }

    @Override
    public void insetBill(LightBinding binding, LightOperation operation, LightCharge lightCharge) {
        if (isBinding(binding) && isBLTOperation(operation)) {
            billDao.insetBill(lightCharge);
        }
    }

    @Override
    public List<Map> getChargeList(String uid) {
        List<Map> mapList = billDao.getChargeList(uid);
        if (mapList != null) {
            for (Map map : mapList) {
                map.put("electric_bill", 0.1);
            }
        }
        return mapList;
    }

    @Override
    public Map getSumCharge(String uid) {
        Map map = billDao.getSumCharge(uid);
        if (map != null) {
            BigDecimal total = (BigDecimal) map.get("total_bill");
            double total_bill = total.doubleValue() / 1000;
            map.put("total_bill", total_bill);
            map.put("ytd_charge", 0.1);
        }
        return map;
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
        System.out.println("lightOperation.getIsRegister()=" + lightOperation.getIsRegister());
        boolean flag = false;
        if (lightOperation != null && lightOperation.getIsRegister().equals("1")) {
            flag = true;
        }
        return flag;
    }
}

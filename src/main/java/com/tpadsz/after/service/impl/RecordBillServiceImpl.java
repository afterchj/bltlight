package com.tpadsz.after.service.impl;
import com.tpadsz.after.api.RecordBillService;
import com.tpadsz.after.dao.RecordBillDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by hongjian.chen on 2018/8/27.
 */

@Service("recordBillService")
public class RecordBillServiceImpl implements RecordBillService<Map> {

    @Autowired
    private RecordBillDao billDao;

    @Override
    public Map getByDeviceId(String deviceId) {

        return billDao.getByDeviceId(deviceId);
    }

    @Override
    public Map getByLightUid(String uid) {

        return billDao.getByLightUid(uid);
    }

    @Override
    public void insetBill(Map map) {
        if (isBinding(map) && isBLTOperation(map)) {
            billDao.insetBill(map);
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

    public boolean isBinding(Map<String, String> map) {
        boolean flag = false;
        if (map != null) {
            if (StringUtils.isNotEmpty(map.get("bossUid")) && StringUtils.isNotEmpty(map.get("lightUid")) && StringUtils.isNotEmpty(map.get("deviceId"))) {
                flag = true;
            }
        }
        System.out.println("flag=" + flag);
        return flag;
    }

    public boolean isBLTOperation(Map<String, String> map) {
        System.out.println("lightOperation.getIsRegister()=" + map.get("isRegister"));
        boolean flag = false;
        if (map != null && map.get("isRegister").equals("1")) {
            flag = true;
        }
        return flag;
    }
}

package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.TbkDao;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.ShareLog;
import com.tpadsz.after.service.TbkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tbkService")
public class TbkServiceImpl implements TbkService {
    @Resource(name = "tbkDao")
    private TbkDao tbkDao;


    @Override
    public void saveShareLog(ShareLog shareLog) {
        tbkDao.saveShareLog(shareLog);
    }

    @Override
    public void settleCoins(OrderFrom orderFrom) {
        int earn;
        earn = (int) (tbkDao.findEarnByNumiid(String.valueOf(orderFrom.getNum_iid())) * 1000);
        tbkDao.insertOrUpdateAccount(orderFrom.getUid(), "12", earn, earn);
        tbkDao.insertOrUpdateDailyAccount(orderFrom.getUid(), "12", orderFrom.getCreate_time(), "1", earn);
    }

    @Override
    public void recordECoins(OrderFrom orderFrom) {
        int earn;
        earn = (int) (tbkDao.findEarnByNumiid(String.valueOf(orderFrom.getNum_iid())) * 1000);
        tbkDao.insertOrUpdateDailyEstimatedAccount(orderFrom.getTrade_id(), orderFrom.getUid(), "12", orderFrom
                .getCreate_time(), "1", orderFrom.getTk_status(), earn);
    }


    @Override
    public String findAvail(String uid) {
        String avail = tbkDao.findAvail(uid);
        return avail;
    }

}

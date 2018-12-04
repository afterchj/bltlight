package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.TbkDao;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.ShareLog;
import com.tpadsz.after.service.TbkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        tbkDao.insertDailyAccount(orderFrom.getUid(), "12", orderFrom.getCreate_time(), "1", earn);
    }

    @Override
    public void recordECoins(OrderFrom orderFrom) {
        int earn;
        earn = (int) (tbkDao.findEarnByNumiid(String.valueOf(orderFrom.getNum_iid())) * 1000);
        tbkDao.insertOrUpdateDailyEstimatedAccount(orderFrom.getTrade_id(), orderFrom.getUid(), "12", orderFrom
                .getCreate_time(), "1", orderFrom.getStatus(), earn);
    }


    @Override
    public int findAvail(String uid) {
        return tbkDao.findAvail(uid);
    }

    @Override
    public int findTodayEcoins(String uid) {
        return tbkDao.findTodayEcoins(uid);
    }

    @Override
    public int findPresentMonthEcoins(String uid) {
        return tbkDao.findPresentMonthEcoins(uid);
    }

    @Override
    public int findLastMonthEcoins(String uid) {
        return tbkDao.findLastMonthEcoins(uid);
    }

    @Override
    public int findLastMonthCoins(String uid) {
        return tbkDao.findLastMonthCoins(uid);
    }

    @Override
    public int findYesterdayEcoins(String uid) {
        return tbkDao.findYesterdayEcoins(uid);
    }

    @Override
    public int findConsumeFromPayOrder(String uid) {
        return tbkDao.findConsumeFromPayOrder(uid);
    }

    @Override
    public int findTodayOrders(String uid) {
        return tbkDao.findTodayOrders(uid);
    }

    @Override
    public int findYesterdayOrders(String uid) {
        return tbkDao.findYesterdayOrders(uid);
    }


}

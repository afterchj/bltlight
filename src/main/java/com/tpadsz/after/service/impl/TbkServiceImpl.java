package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.TbkDao;
import com.tpadsz.after.entity.*;
import com.tpadsz.after.service.TbkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("tbkService")
public class TbkServiceImpl implements TbkService {
    @Resource(name = "tbkDao")
    private TbkDao tbkDao;


    @Override
    public ShareLog findIdFromHipriceLog(String uid, String num_iid) {
        return tbkDao.findIdFromHipriceLog(uid,num_iid);
    }

    @Override
    public void saveShareLog(String num_iid, String uid, int share_id, String goods_share_message) {
        tbkDao.saveShareLog(num_iid,uid,share_id,goods_share_message);
    }

    @Override
    public void settleCoins(OrderFrom orderFrom) {
        int earn;
        earn = (int) (tbkDao.findEarnByNumiid(String.valueOf(orderFrom.getNum_iid())) * 1000);
        tbkDao.insertOrUpdateAccount(orderFrom.getUid(), "12", earn, earn);
        tbkDao.insertDailyAccount(orderFrom.getUid(), "12", orderFrom.getCreate_time(), "1001", earn);
    }

    @Override
    public void recordECoins(OrderFrom orderFrom) {
        int earn;
        earn = (int) (tbkDao.findEarnByNumiid(String.valueOf(orderFrom.getNum_iid())) * 1000);
        tbkDao.insertOrUpdateDailyEstimatedAccount(orderFrom.getTrade_id(), orderFrom.getUid(), "12", orderFrom
                .getCreate_time(), "1001", orderFrom.getStatus(), earn);
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

    @Override
    public List<DailyAccount> findLoglistOfIncome(String uid, int var1, int var2) {
        return tbkDao.findLoglistOfIncome(uid,var1,var2);
    }

    @Override
    public List<DailyAccount> findLoglistOfConsume(String uid, int var1, int var2) {
        return tbkDao.findLoglistOfConsume(uid,var1,var2);
    }

    @Override
    public void tbkSave(String uid, String searchName) {
        tbkDao.save(uid,searchName);
    }

    @Override
    public void saveHandyRegister(TbkUser firmware) {
        tbkDao.saveHandyRegister(firmware);
    }

    @Override
    public TbkUser findTbkUserById(String id) {
        return tbkDao.findTbkUserById(id);
    }

    @Override
    public TbkUser findTbkUserByMobile(String mobile) {
        return tbkDao.findTbkUserByMobile(mobile);
    }

    @Override
    public void save(TbkUser firmware) {
        tbkDao.save(firmware);
    }

    @Override
    public void saveLoginLog(TbkLoginLog tbkLoginLog) {
        tbkDao.saveLoginLog(tbkLoginLog);
    }

}

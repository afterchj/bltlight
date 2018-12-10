package com.tpadsz.after.service;


import com.tpadsz.after.entity.*;

import java.util.List;

/**
 * Created by chenhao.lu on 2018/11/27.
 */
public interface TbkService {

    ShareLog findIdFromHipriceLog(String uid, String num_iid);

    void saveShareLog(String num_iid, String uid, int share_id, String goods_share_message);

    //每月记录结算金额
    void settleCoins(OrderFrom orderFrom);

    //实时记录预估金额
    void recordECoins(OrderFrom orderFrom);

    int findAvail(String uid);

    int findTodayEcoins(String uid);

    int findPresentMonthEcoins(String uid);

    int findLastMonthEcoins(String uid);

    int findLastMonthCoins(String uid);

    int findYesterdayEcoins(String uid);

    int findConsumeFromPayOrder(String uid);

    int findTodayOrders(String uid);

    int findYesterdayOrders(String uid);

    List<DailyAccount> findLoglistOfIncome(String uid, int var1, int var2);

    List<DailyAccount> findLoglistOfConsume(String uid, int var1, int var2);

    void tbkSave(String uid, String searchName);

    void saveHandyRegister(TbkUser firmware);

    TbkUser findTbkUserById(String id);

    TbkUser findTbkUserByMobile(String mobile);

    void save(TbkUser firmware);

    void saveLoginLog(TbkLoginLog tbkLoginLog);

}

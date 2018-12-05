package com.tpadsz.after.service;


import com.tpadsz.after.entity.DailyAccount;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.ShareLog;

import java.util.List;

/**
 * Created by chenhao.lu on 2018/11/27.
 */
public interface TbkService {

    void saveShareLog(ShareLog shareLog);

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

}

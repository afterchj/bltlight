package com.tpadsz.after.service;


import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.ShareLog;

/**
 * Created by chenhao.lu on 2018/11/27.
 */
public interface TbkService {

    void saveShareLog(ShareLog shareLog);

    //每月记录结算金额
    void settleCoins(OrderFrom orderFrom);

    //实时记录预估金额
    void recordECoins(OrderFrom orderFrom);

    String findAvail(String uid);


}

package com.tpadsz.after.dao80;

import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.entity.ShopInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/27.
 */
public interface TbkBindDao {

    void bindPid(Map map);

    void insertBindLog(Map map);

    void insetShop(ShopInfo shop);

    void insetShare(ShopInfo shop);

    void insertHiPriceLog(ShopInfo shop);

    Pid getPidInfo();

    Pid getPid(String uid);

    void updatePid(Map map);


}

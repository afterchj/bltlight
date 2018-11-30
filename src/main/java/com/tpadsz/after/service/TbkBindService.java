package com.tpadsz.after.service;

import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.entity.ShopInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/28.
 */
public interface TbkBindService {

    void bindPid(Map map);

    void insertHiPriceLog(ShopInfo shopInfo);

    Pid getPidInfo();

    List<Pid> getPids();

    Pid getPid(String uid);

    void updatePid(Map map);
}

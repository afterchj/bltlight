package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.TbkBindDao;
import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.service.TbkBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/28.
 */

@Service
public class TbkBindServiceImpl implements TbkBindService {

    @Autowired
    private TbkBindDao bindDao;

    @Override
    public void bindPid(Map map) {
        bindDao.bindPid(map);
    }

    @Override
    public void insertBindLog(Map map) {
        bindDao.insertBindLog(map);
    }

    @Override
    public void insetShop(ShopInfo shop) {
        bindDao.insetShop(shop);
    }

    @Override
    public void insetShare(ShopInfo shop) {
        bindDao.insetShare(shop);
    }

    @Override
    public void insertHiPriceLog(ShopInfo shop) {
        bindDao.insertHiPriceLog(shop);
    }

    @Override
    public Pid getPidInfo() {
        return bindDao.getPidInfo();
    }

    @Override
    public Pid getPid(String uid) {
        return bindDao.getPid(uid);
    }

    @Override
    public void updatePid(Map map) {
        bindDao.updatePid(map);
    }
}

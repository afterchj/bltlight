package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.TbkBindDao;
import com.tpadsz.after.entity.Pid;
import com.tpadsz.after.service.TbkBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Pid getPidInfo() {
        return bindDao.getPidInfo();
    }

    @Override
    public List<Pid> getPids() {
        return bindDao.getPids();
    }

    @Override
    public String getPid(String uid) {
        return bindDao.getPid(uid);
    }

    @Override
    public void updatePid(Map map) {
        bindDao.updatePid(map);
    }
}

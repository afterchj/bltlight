package com.tpadsz.after.service;

import com.tpadsz.after.entity.Pid;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/28.
 */
public interface TbkBindService {

    void bindPid(Map map);

    Pid getPidInfo();

    List<Pid> getPids();

    String getPid(String uid);

    void updatePid(Map map);
}

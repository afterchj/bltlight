package com.tpadsz.after.dao80;

import com.tpadsz.after.entity.Pid;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/27.
 */
public interface TbkBindDao {

    void bindPid(Map map);

    Pid getPidInfo();

    String getPid(String uid);

    List<Pid> getPids();

    void updatePid(Map map);

}

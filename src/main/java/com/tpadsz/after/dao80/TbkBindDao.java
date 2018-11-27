package com.tpadsz.after.dao80;

import com.tpadsz.after.entity.Pid;

import java.util.Map;

/**
 * Created by hongjian.chen on 2018/11/27.
 */
public interface TbkBindDao {

    void bindPid(Map map);

    Pid getPidInfo();

    void updatePid(Map map);

}

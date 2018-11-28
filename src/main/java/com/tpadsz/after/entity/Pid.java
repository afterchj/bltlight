package com.tpadsz.after.entity;

import java.io.Serializable;

/**
 * Created by hongjian.chen on 2018/11/26.
 */
public class Pid implements Serializable{

    private int pkey;
    private String pid;
    private String adzone_id;
    public int getPkey() {
        return pkey;
    }

    public void setPkey(int pkey) {
        this.pkey = pkey;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAdzone_id() {
        return adzone_id;
    }

    public void setAdzone_id(String adzone_id) {
        this.adzone_id = adzone_id;
    }
}

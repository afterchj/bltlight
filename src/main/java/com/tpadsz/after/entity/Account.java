package com.tpadsz.after.entity;

import java.io.Serializable;


public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uid;
    private String app_id;
    private int avail;
    private int earn;
    private int consume;
    private String other;

    public Account() {
        super();
    }

    public Account(String uid, String app_id, int avail, int earn, int consume, String other) {
        super();
        this.uid = uid;
        this.app_id = app_id;
        this.avail = avail;
        this.earn = earn;
        this.consume = consume;
        this.other = other;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    public int getEarn() {
        return earn;
    }

    public void setEarn(int earn) {
        this.earn = earn;
    }

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

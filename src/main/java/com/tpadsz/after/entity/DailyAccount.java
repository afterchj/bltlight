package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;


public class DailyAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uid;
    private String app_id;
    private Date date;
    private String trade_type;
    private int earn;
    private int consume;
    private String other;

    public DailyAccount() {
        super();
    }

    public DailyAccount(String uid, String app_id, Date date, String trade_type, int earn, int consume, String other) {
        super();
        this.uid = uid;
        this.app_id = app_id;
        this.date = date;
        this.trade_type = trade_type;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
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

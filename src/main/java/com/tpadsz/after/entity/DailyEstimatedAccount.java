package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;


public class DailyEstimatedAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private long trade_id;
    private String uid;
    private String app_id;
    private Date date;
    private String trade_type;
    private int tk_status;
    private int price;
    private String other;

    public DailyEstimatedAccount() {
        super();
    }

    public DailyEstimatedAccount(long trade_id, String uid, String app_id, Date date, String trade_type, int tk_status, int price, String other) {
        super();
        this.trade_id = trade_id;
        this.uid = uid;
        this.app_id = app_id;
        this.date = date;
        this.trade_type = trade_type;
        this.tk_status = tk_status;
        this.price = price;
        this.other = other;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
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

    public int getTk_status() {
        return tk_status;
    }

    public void setTk_status(int tk_status) {
        this.tk_status = tk_status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

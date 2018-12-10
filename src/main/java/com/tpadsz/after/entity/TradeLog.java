package com.tpadsz.after.entity;

import java.io.Serializable;

/**
 * Created by chenhao.lu on 2018/12/3.
 */
public class TradeLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uid;
    private String trade_type;
    private String trade_name;
    private float price;
    private String date;


    public TradeLog() {
        super();
    }

    public TradeLog(String uid, String trade_type, String trade_name, float price, String date) {
        super();
        this.uid = uid;
        this.trade_type = trade_type;
        this.trade_name = trade_name;
        this.price = price;
        this.date = date;
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

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

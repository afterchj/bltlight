package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;


public class ShareLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String num_iid;
    private String uid;
    private int share_id;
    private String goods_share_message;
    private Date share_time;
    private String other;

    public ShareLog() {
        super();
    }

    public ShareLog(int id, String num_iid, String uid, int share_id, String goods_share_message,Date share_time, String other) {
        super();
        this.id = id;
        this.num_iid = num_iid;
        this.uid = uid;
        this.share_id = share_id;
        this.goods_share_message = goods_share_message;
        this.share_time = share_time;
        this.other = other;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getShare_id() {
        return share_id;
    }

    public void setShare_id(int share_id) {
        this.share_id = share_id;
    }

    public Date getShare_time() {
        return share_time;
    }

    public void setShare_time(Date share_time) {
        this.share_time = share_time;
    }

    public String getGoods_share_message() {
        return goods_share_message;
    }

    public void setGoods_share_message(String goods_share_message) {
        this.goods_share_message = goods_share_message;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

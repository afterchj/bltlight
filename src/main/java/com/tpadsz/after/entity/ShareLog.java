package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;


public class ShareLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String num_iid;
    private String uid;
    private String adzone_id;
    private String goods_share_message;
    private Date share_date;
    private String other;

    public ShareLog() {
        super();
    }

    public ShareLog(String id, String num_iid, String uid, String adzone_id, String goods_share_message,Date share_date, String other) {
        super();
        this.id = id;
        this.num_iid = num_iid;
        this.uid = uid;
        this.adzone_id = adzone_id;
        this.goods_share_message = goods_share_message;
        this.share_date = share_date;
        this.other = other;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAdzone_id() {
        return adzone_id;
    }

    public void setAdzone_id(String adzone_id) {
        this.adzone_id = adzone_id;
    }

    public String getGoods_share_message() {
        return goods_share_message;
    }

    public void setGoods_share_message(String goods_share_message) {
        this.goods_share_message = goods_share_message;
    }

    public Date getShare_date() {
        return share_date;
    }

    public void setShare_date(Date share_date) {
        this.share_date = share_date;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

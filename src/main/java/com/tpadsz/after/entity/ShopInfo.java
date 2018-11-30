package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hongjian.chen on 2018/11/28.
 */
public class ShopInfo implements Serializable {
    private String uid;
    private int pkey;
    private String num_iid;
    private String user_type;
    private Double zk_final_price;
    private String coupon_info;
    private Double qh_final_price;
    private String pict_url;
    private Double commission_rate;
    private Double rate_touid;
    private String goods_info;
    private String result_info;
    private Date share_date;
    private int out_result;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPkey() {
        return pkey;
    }

    public void setPkey(int pkey) {
        this.pkey = pkey;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public Double getZk_final_price() {
        return zk_final_price;
    }

    public void setZk_final_price(Double zk_final_price) {
        this.zk_final_price = zk_final_price;
    }

    public String getCoupon_info() {
        return coupon_info;
    }

    public void setCoupon_info(String coupon_info) {
        this.coupon_info = coupon_info;
    }

    public Double getQh_final_price() {
        return qh_final_price;
    }

    public void setQh_final_price(Double qh_final_price) {
        this.qh_final_price = qh_final_price;
    }

    public String getPict_url() {
        return pict_url;
    }

    public void setPict_url(String pict_url) {
        this.pict_url = pict_url;
    }

    public Double getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(Double commission_rate) {
        this.commission_rate = commission_rate;
    }

    public Double getRate_touid() {
        return rate_touid;
    }

    public void setRate_touid(Double rate_touid) {
        this.rate_touid = rate_touid;
    }

    public String getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(String goods_info) {
        this.goods_info = goods_info;
    }

    public String getResult_info() {
        return result_info;
    }

    public void setResult_info(String result_info) {
        this.result_info = result_info;
    }

    public Date getShare_date() {
        return share_date;
    }

    public void setShare_date(Date share_date) {
        this.share_date = share_date;
    }

    public int getOut_result() {
        return out_result;
    }

    public void setOut_result(int out_result) {
        this.out_result = out_result;
    }
}

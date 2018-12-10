package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: odelic
 * @description: 订单
 * @author: Mr.Ma
 * @create: 2018-11-26 15:45
 **/
public class OrderFrom implements Serializable{
    private static final long serialVersionUID =1L;
    private Long trade_id;
    private Long num_iid;
    private String adzone_id;
    private String site_id;
    private String uid;
    private Integer tk_status;
    private Date create_time;
    private Date earning_time;
    private String alipay_total_price;
    private String price;
    private String pay_price;
    private Integer item_num;
    private String total_commission_rate;
    private String total_commission_fee;
    private String action_type;
    private String seller_shop_title;
    private String item_title;
    private String image;
    private String update_time;
    private Integer status;
    private Double rate_touid;//实际佣金
    private Double prePrice;//预估佣金 rate_touid*item_num
    private String order_type;//订单类型，如天猫，淘宝

    private Integer size;
    private Integer page;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public OrderFrom() {
    }

    public Double getRate_touid() {
        return rate_touid;
    }

    public void setRate_touid(Double rate_touid) {
        this.rate_touid = rate_touid;
    }

    public Double getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(Double prePrice) {
        this.prePrice = prePrice;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getSeller_shop_title() {
        return seller_shop_title;
    }

    public void setSeller_shop_title(String seller_shop_title) {
        this.seller_shop_title = seller_shop_title;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public Long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Long trade_id) {
        this.trade_id = trade_id;
    }

    public void setNum_iid(Long num_iid) {
        this.num_iid = num_iid;
    }

    public Long getNum_iid() {
        return num_iid;
    }

    public String getAdzone_id() {
        return adzone_id;
    }

    public void setAdzone_id(String adzone_id) {
        this.adzone_id = adzone_id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getTk_status() {
        return tk_status;
    }

    public void setTk_status(Integer tk_status) {
        this.tk_status = tk_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getEarning_time() {
        return earning_time;
    }

    public void setEarning_time(Date earning_time) {
        this.earning_time = earning_time;
    }

    public String getAlipay_total_price() {
        return alipay_total_price;
    }

    public void setAlipay_total_price(String alipay_total_price) {
        this.alipay_total_price = alipay_total_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPay_price() {
        return pay_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }

    public Integer getItem_num() {
        return item_num;
    }

    public void setItem_num(Integer item_num) {
        this.item_num = item_num;
    }

    public String getTotal_commission_rate() {
        return total_commission_rate;
    }

    public void setTotal_commission_rate(String total_commission_rate) {
        this.total_commission_rate = total_commission_rate;
    }

    public String getTotal_commission_fee() {
        return total_commission_fee;
    }

    public void setTotal_commission_fee(String total_commission_fee) {
        this.total_commission_fee = total_commission_fee;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    @Override
    public String toString() {
        return "OrderFrom{" +
                "trade_id=" + trade_id +
                ", num_iid=" + num_iid +
                ", adzone_id='" + adzone_id + '\'' +
                ", site_id='" + site_id + '\'' +
                ", uid='" + uid + '\'' +
                ", tk_status=" + tk_status +
                ", create_time=" + create_time +
                ", earning_time=" + earning_time +
                ", alipay_total_price='" + alipay_total_price + '\'' +
                ", price='" + price + '\'' +
                ", pay_price='" + pay_price + '\'' +
                ", item_num=" + item_num +
                ", total_commission_rate='" + total_commission_rate + '\'' +
                ", total_commission_fee='" + total_commission_fee + '\'' +
                ", action_type='" + action_type + '\'' +
                ", seller_shop_title='" + seller_shop_title + '\'' +
                ", item_title='" + item_title + '\'' +
                ", image='" + image + '\'' +
                ", update_time='" + update_time + '\'' +
                ", status=" + status +
                ", rate_touid=" + rate_touid +
                ", prePrice=" + prePrice +
                ", order_type='" + order_type + '\'' +
                '}';
    }
}

package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hongjian.chen on 2018/8/27.
 */
public class LightCharge implements Serializable{

    private int id;
    private String uid;
    private int electric_bill;
    private Date create_time;
    private Date log_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getElectric_bill() {
        return electric_bill;
    }

    public void setElectric_bill(int electric_bill) {
        this.electric_bill = electric_bill;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLog_time() {
        return log_time;
    }

    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }
}

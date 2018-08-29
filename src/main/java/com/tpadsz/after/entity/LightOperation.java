package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: blt-light
 * @description: 灯控App操作蓝牙灯
 * @author: Mr.Ma
 * @create: 2018-08-21 13:35
 **/
public class LightOperation implements Serializable{

    private String id;
    private String uid;
    private String isRegister;
    private Date create_date;
    private String device_id;
    private String behavior;

    public LightOperation() {
    }

    public LightOperation(String id, String uid, String isRegister, Date
            create_date, String device_id, String behavior) {
        this.id = id;
        this.uid = uid;
        this.isRegister = isRegister;
        this.create_date = create_date;
        this.device_id = device_id;
        this.behavior = behavior;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}

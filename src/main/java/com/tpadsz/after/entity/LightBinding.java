package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;


public class LightBinding implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deviceId;
    private String mac;
    private String lightUid;
    private String bossUid;
    private Date createTime;
    private Date updateTime;
    private String other;

    public LightBinding() {
        super();
    }

    public LightBinding(String deviceId, String mac, String lightUid,String bossUid,Date createTime,Date updateTime, String other) {
        super();
        this.deviceId = deviceId;
        this.mac = mac;
        this.lightUid = lightUid;
        this.bossUid = bossUid;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.other = other;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getLightUid() {
        return lightUid;
    }

    public void setLightUid(String lightUid) {
        this.lightUid = lightUid;
    }

    public String getBossUid() {
        return bossUid;
    }

    public void setBossUid(String bossUid) {
        this.bossUid = bossUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "LightBinding{" +
                "deviceId='" + deviceId + '\'' +
                ", mac='" + mac + '\'' +
                ", lightUid='" + lightUid + '\'' +
                ", bossUid='" + bossUid + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", other=" + other +
                '}';
    }
}

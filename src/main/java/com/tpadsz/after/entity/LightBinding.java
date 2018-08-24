package com.tpadsz.after.entity;

import java.io.Serializable;

/**
 * Created by nannan.li on 2017/2/7.
 */
public class LightBinding implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deviceId;
    private String mac;
    private String lightUid;
    private String bossUid;
    private String other;

    public LightBinding() {
        super();
    }

    public LightBinding(String deviceId, String mac, String lightUid,String bossUid, String other) {
        super();
        this.deviceId = deviceId;
        this.mac = mac;
        this.lightUid = lightUid;
        this.bossUid = bossUid;
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
                ", other=" + other +
                '}';
    }
}

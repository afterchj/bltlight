package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;


public class PairingLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String lightUid;
    private String isLogin;
    private String deviceId;
    private String firmware;
    private String result;
    private Date accessTime;
    private String other;

    public PairingLog() {
        super();
    }

    public PairingLog(Integer id, String lightUid, String isLogin, String deviceId, String firmware, String result, Date accessTime,String other) {
        super();
        this.id = id;
        this.lightUid = lightUid;
        this.isLogin = isLogin;
        this.deviceId = deviceId;
        this.firmware = firmware;
        this.result = result;
        this.accessTime = accessTime;
        this.other = other;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLightUid() {
        return lightUid;
    }

    public void setLightUid(String lightUid) {
        this.lightUid = lightUid;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

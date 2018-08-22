package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: boss-locker
 * @description: 灯控App User
 * @author: Mr.Ma
 * @create: 2018-08-13 13:44
 **/
public class OdelicUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;//uid
    private Date create_date;//注册时间
    private String imei;
    private String imsi;
    private String fm;
    private String os;
    private String model;
    private String operators;
    private String resolution;
    private String pkg;//包名
    private String mobile;//手机号
    private String clientVersion;//客户端版本
    private String appid;
    private String netEnv;//网络类型

    public OdelicUser() {
    }

    public OdelicUser(String id, Date create_date, String imei, String imsi,
                      String fm, String os, String model, String operators,
                      String resolution, String pkg, String mobile, String
                              clientVersion, String appid, String netEnv) {
        this.id = id;
        this.create_date = create_date;
        this.imei = imei;
        this.imsi = imsi;
        this.fm = fm;
        this.os = os;
        this.model = model;
        this.operators = operators;
        this.resolution = resolution;
        this.pkg = pkg;
        this.mobile = mobile;
        this.clientVersion = clientVersion;
        this.appid = appid;
        this.netEnv = netEnv;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNetEnv() {
        return netEnv;
    }

    public void setNetEnv(String netEnv) {
        this.netEnv = netEnv;
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getFm() {
        return fm;
    }

    public void setFm(String fm) {
        this.fm = fm;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "OdelicUser{" +
                "id='" + id + '\'' +
                ", create_date=" + create_date +
                ", imei='" + imei + '\'' +
                ", imsi='" + imsi + '\'' +
                ", fm='" + fm + '\'' +
                ", os='" + os + '\'' +
                ", model='" + model + '\'' +
                ", operators='" + operators + '\'' +
                ", resolution='" + resolution + '\'' +
                ", pkg='" + pkg + '\'' +
                ", mobile='" + mobile + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", appid='" + appid + '\'' +
                ", netEnv='" + netEnv + '\'' +
                '}';
    }
}

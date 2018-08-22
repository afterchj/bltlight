package com.tpadsz.after.entity;

import java.util.Date;

/**
 * @program: blt-light
 * @description:灯控App打开日志 entity
 * @author: Mr.Ma
 * @create: 2018-08-21 13:29
 **/
public class OpenApp {

    private String id;
    private String uid;
    private Date create_date;
    private String imei;
    private String imsi;
    private String fm;
    private String os;
    private String model;
    private String operators;
    private String resolution;
    private String pkg;
//    private String mobile;
    private String clientVersion;
    private String appid;
    private String netEnv;
    private String behavior;
    private String group;

    public OpenApp() {
    }

//    public OpenApp(String id, String uid, Date create_date, String imei,
//                   String imsi, String fm, String os, String model, String
//                           operators, String resolution, String pkg, String
//                           mobile, String clientVersion, String appid, String
//                           netEnv, String behavior, String group) {
//        this.id = id;
//        this.uid = uid;
//        this.create_date = create_date;
//        this.imei = imei;
//        this.imsi = imsi;
//        this.fm = fm;
//        this.os = os;
//        this.model = model;
//        this.operators = operators;
//        this.resolution = resolution;
//        this.pkg = pkg;
//        this.mobile = mobile;
//        this.clientVersion = clientVersion;
//        this.appid = appid;
//        this.netEnv = netEnv;
//        this.behavior = behavior;
//        this.group = group;
//    }

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

//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }

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

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

public class TbkUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;//uid
    private String appid;
    private Date create_date;//注册时间
    private String mobile;//手机号
    private String username;
    private String nickname;
    private String login_name;
    private String icon;
    private String imei;
    private String imsi;
    private String fm;
    private String os;
    private String model;
    private String operators;
    private String resolution;
    private String pkg;//包名
    private String clientVersion;//客户端版本
    private String netEnv;//网络类型
    private String other;

    public TbkUser() {
    }

    public TbkUser(String id, String appid, Date create_date, String mobile, String username, String nickname, String login_name, String icon, String imei, String imsi, String fm, String os, String model, String operators, String resolution, String pkg, String clientVersion, String netEnv, String other) {
        this.id = id;
        this.appid = appid;
        this.create_date = create_date;
        this.mobile = mobile;
        this.username = username;
        this.nickname = nickname;
        this.login_name = login_name;
        this.icon = icon;
        this.imei = imei;
        this.imsi = imsi;
        this.fm = fm;
        this.os = os;
        this.model = model;
        this.operators = operators;
        this.resolution = resolution;
        this.pkg = pkg;
        this.clientVersion = clientVersion;
        this.netEnv = netEnv;
        this.other = other;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        return "TbkUser{" +
                "id='" + id + '\'' +
                ", appid='" + appid + '\'' +
                ", create_date=" + create_date + '\'' +
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", login_name='" + login_name + '\'' +
                ", icon='" + icon + '\'' +
                ", imei='" + imei + '\'' +
                ", imsi='" + imsi + '\'' +
                ", fm='" + fm + '\'' +
                ", os='" + os + '\'' +
                ", model='" + model + '\'' +
                ", operators='" + operators + '\'' +
                ", resolution='" + resolution + '\'' +
                ", pkg='" + pkg + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", netEnv='" + netEnv + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}

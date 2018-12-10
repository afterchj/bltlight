package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;


public class FileRecord implements Serializable {
    String appId;
    Integer versionCode;
    String versionName;
    String path;
    String md5;
    Integer size;
    String updateLog;
    Integer forceUpdate;
    String forceUpdateOp;
    String forceUpdateValue;
    Date createDate;
    Date updateDate;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public Integer getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getForceUpdateOp() {
        return forceUpdateOp;
    }

    public void setForceUpdateOp(String forceUpdateOp) {
        this.forceUpdateOp = forceUpdateOp;
    }

    public String getForceUpdateValue() {
        return forceUpdateValue;
    }

    public void setForceUpdateValue(String forceUpdateValue) {
        this.forceUpdateValue = forceUpdateValue;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
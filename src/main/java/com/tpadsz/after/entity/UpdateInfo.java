package com.tpadsz.after.entity;

/**
 * Created by chenhao.lu on 2018/6/7.
 */
public class UpdateInfo {

    private String resultCode;
    private String resultMsg;
    private boolean update;
    private String apkUrl;
    private Integer versionCode;
    private String version;
    private Integer size;
    private String md5;
    private String updateLog;
    private boolean force;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    @Override
    public String toString() {
        return "{" +
                "\"resultCode\":\"" + resultCode + '\"' +
                ", \"resultMsg\":\"" + resultMsg + '\"' +
                ", \"update\":" + update  +
                ", \"apkUrl\":\"" + apkUrl + '\"' +
                ", \"versionCode\":" + versionCode +
                ", \"version\":\"" + version + '\"' +
                ", \"size\":" + size +
                ", \"md5\":\"" + md5 + '\"' +
                ", \"updateLog\":\"" + updateLog + '\"' +
                ", \"force\":" + force +
                "}";
    }
}

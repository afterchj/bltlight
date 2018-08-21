package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nannan.li on 2017/2/7.
 */
public class LightPairing implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lightUid;
    private String name;
    private Date createTime;
    private Date updateTime;
    private String other;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLightUid() {
        return lightUid;
    }

    public void setLightUid(String lightUid) {
        this.lightUid = lightUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "LightPairing{" +
                "lightUid='" + lightUid + '\'' +
                ", name=" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", other=" + other +
                '}';
    }
}

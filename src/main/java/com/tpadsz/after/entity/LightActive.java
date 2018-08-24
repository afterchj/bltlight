package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nannan.li on 2017/2/7.
 */
public class LightActive implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deviceId;
    private String model;
    private String name;
    private String mac;
    private String version;
    private String channel;
    private String other;

    public LightActive() {
        super();
    }

    public LightActive(String deviceId, String model, String name,String mac, String version, String channel,String other) {
        super();
        this.deviceId = deviceId;
        this.model = model;
        this.name = name;
        this.mac = mac;
        this.version = version;
        this.channel = channel;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "LightActive{" +
                "deviceId='" + deviceId + '\'' +
                ", model='" + model + '\'' +
                ", name=" + name + '\'' +
                ", mac='" + mac + '\'' +
                ", version='" + version + '\'' +
                ", channel='" + channel + '\'' +
                ", other=" + other +
                '}';
    }
}

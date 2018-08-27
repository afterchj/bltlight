package com.tpadsz.after.entity;

/**
 * @program: blt-light
 * @description: 蓝牙连接
 * @author: Mr.Ma
 * @create: 2018-08-21 13:33
 **/
public class BluethoothConnect {

    private String id;
    private String uid;
    private String device_id;
    private String connectStatus;

    public BluethoothConnect() {
    }

    public BluethoothConnect(String id, String uid, String device_id, String
            connectStatus) {
        this.id = id;
        this.uid = uid;
        this.device_id = device_id;
        this.connectStatus = connectStatus;
    }

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

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(String connectStatus) {
        this.connectStatus = connectStatus;
    }
}

package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

public class TbkLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private Date login_date;
    private Date loginout_date;
    private String behavior;
    private String uid;
    private String mobile;
    private Date create_date;

    public TbkLoginLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public Date getLoginout_date() {
        return loginout_date;
    }

    public void setLoginout_date(Date loginout_date) {
        this.loginout_date = loginout_date;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "TbkLoginLog{" +
                "id='" + id + '\'' +
                ", login_date=" + login_date +
                ", loginout_date=" + loginout_date +
                ", behavior='" + behavior + '\'' +
                ", uid='" + uid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", create_date='" + create_date + '\'' +
                '}';
    }
}

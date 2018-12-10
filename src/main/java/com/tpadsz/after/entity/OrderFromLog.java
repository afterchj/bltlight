package com.tpadsz.after.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-12-06 17:08
 **/
public class OrderFromLog implements Serializable {
    private static final long serialVersionUID =1L;
    private Integer pageNum;
    private Integer status;
    private Date createTime;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

package com.tpadsz.after.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by hongjian.chen on 2018/11/22.
 */
public class Person implements Serializable{
    private String name;
    private String address;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toJsonString() {

        return JSON.toJSONString(this);
    }
}

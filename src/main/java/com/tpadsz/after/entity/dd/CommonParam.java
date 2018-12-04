package com.tpadsz.after.entity.dd;

/**
 * Created by hongjian.chen on 2018/11/19.
 */
public enum CommonParam {
    APPURL("https://eco.taobao.com/router/rest"), VEORDER("http://apiorder.vephp.com/order"), VEHICPI("http://api.vephp.com/hcapi"), //
    APPKER("25208405"), APPSECRET("d0a228c9cca6bd42a9e0ecd537db0ad1"), VEKEY("V00000585Y74210916");//
    private String value;

    CommonParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        for (CommonParam param : CommonParam.values()) {
            System.out.println(param.value);
        }
    }
}

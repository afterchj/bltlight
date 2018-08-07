package com.tpadsz.after.util;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
public enum Constants {
    SESSION_USERNAME("USERNAME"),
    WEB_SSM("ws://ctc-hq.tpadsz.com/web_ssm/websocket"),
    BLT_LIGHT("ws://ctc-hq.tpadsz.com/blt_light/websocket"),
    UICHANGE_BLT("ws://uichange.com/blt_light/websocket"),
    TEST_URL("ws://localhost:8080/blt_light/websocket");

    private String username;

    Constants(String username) {
        this.username = username;
    }

    public String value() {
        return this.username;
    }

//    public static void main(String[] args) {
//        System.out.println(Constants.TEST_URL.value());
//    }

}

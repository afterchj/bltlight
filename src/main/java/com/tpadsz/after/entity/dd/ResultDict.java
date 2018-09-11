package com.tpadsz.after.entity.dd;

public enum ResultDict {

    SUCCESS("000", "成功"),
    UID_NOT_EXIST("60001", "您的请求参数出错，请稍后重试"),
    DEVICE_UNBIND("60012", "您还未购买灯泡或还未绑定过灯泡或已经解绑"),
    RECORD_NULL("60013", "您还没有产生电费哦，请根据要求连接灯泡和使用小熊灯控"),
    PARAMS_BLANK("301", "参数不能够为空"), PARAMS_NOT_PARSED("302", "参数解析错误"),
    SYSTEM_ERROR("200", "系统错误"), TIMEOUT("201", "系统超时");


    ResultDict(String code, String value) {
        this.value = value;
        this.code = code;
    }

    private String value;
    private String code;

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

}

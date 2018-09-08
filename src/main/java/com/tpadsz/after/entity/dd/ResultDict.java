package com.tpadsz.after.entity.dd;

public enum ResultDict {

    SUCCESS("000", "成功"),
    UID_NOT_EXIST("60001", "uid不存在"),
    DEVICE_UNBIND("60012", "未绑定或已解绑"),
    RECORD_NULL("60013", "电费累计表内无该熊猫赚uid"),
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

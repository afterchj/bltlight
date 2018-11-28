package com.tpadsz.after.entity.dd;

public enum TimeQuery {

    a1(1, "00:00"), a2(2,"00:20"),a3(3,"00:40"),a4(4,"01:00"),a5(5,"01:20"),a72(72,"11:");


    TimeQuery(Integer code, String value) {
        this.value = value;
        this.code = code;
    }

    private String value;
    private Integer code;

    public String getValue() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

}

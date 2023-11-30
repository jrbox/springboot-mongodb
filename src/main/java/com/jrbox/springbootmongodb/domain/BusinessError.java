package com.jrbox.springbootmongodb.domain;

public enum BusinessError implements CommonBusinessError {
    E40001("E40001"),
    E40002("E40002"),
    E40003("E40003");

    private String code;

    BusinessError(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}

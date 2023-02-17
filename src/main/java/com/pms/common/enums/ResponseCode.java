package com.pms.common.enums;

import lombok.Getter;

/**
 * @author liangrj
 * @since 2023/02/16
 */
@Getter
public enum ResponseCode {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 请求报错
     */
    FAIL(500, "请求报错");

    private final int code;

    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}

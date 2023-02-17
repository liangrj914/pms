package com.pms.common.model;

import com.pms.common.enums.ResponseCode;
import lombok.Data;

/**
 * 统一封装响应实体
 *
 * @author liangrj
 * @since 2023/02/16
 */
@Data
public class Response {

    /**
     * 状态码：
     * 200：成功
     * 403：无权限
     * 404：路由错误
     * 500：服务器错误
     */
    private Integer code;

    /**
     * 描述
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public Response() {
    }

    public Response(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response base(Integer code, String message, Object data) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static Response success(String message, Object data) {
        return base(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static Response success(Object data) {
        return success(ResponseCode.SUCCESS.getMessage(), data);
    }

    public static Response success() {
        return success(null);
    }

    public static Response fail(int code, String message) {
        return base(code, message, null);
    }

    public static Response fail(String message) {
        return fail(ResponseCode.FAIL.getCode(), message);
    }

    public static Response fail() {
        return fail(ResponseCode.FAIL.getMessage());
    }

}

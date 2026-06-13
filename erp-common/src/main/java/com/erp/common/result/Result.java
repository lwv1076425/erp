package com.erp.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结果
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 状态码 */
    private int code;

    /** 消息 */
    private String msg;

    /** 数据 */
    private T data;

    public static <T> Result<T> ok() {
        return restResult(null, 200, "操作成功");
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, 200, "操作成功");
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, 200, msg);
    }

    public static <T> Result<T> fail() {
        return restResult(null, 500, "操作失败");
    }

    public static <T> Result<T> fail(String msg) {
        return restResult(null, 500, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }
}

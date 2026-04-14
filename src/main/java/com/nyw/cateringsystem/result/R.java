package com.nyw.cateringsystem.result;

import com.nyw.cateringsystem.consts.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应体对象Response
 * @author 宁有为
 * @version 1.0.0
 * @className R
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {

    /**
     * 状态码
     **/
    private int code;

    /**
     * 提示信息
     **/
    private String msg;

    /**
     * 时间戳
     **/
    private Long timestamp;

    /**
     * 数据
     **/
    private T data;


    public static <T> R<T> ok(T data) {
        return R.<T>builder()
                .code(CodeEnum.OK.getCode())
                .msg(CodeEnum.OK.getMsg())
                .timestamp(System.currentTimeMillis())
                .data(data)
                .build();
    }

    public static <T> R<T> ok() {
        return R.<T>builder()
                .code(CodeEnum.OK.getCode())
                .msg(CodeEnum.OK.getMsg())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> R<T> ok(String msg) {
        return R.<T>builder()
                .code(CodeEnum.OK.getCode())
                .msg(msg)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> R<T> ok(String msg, T data) {
        return R.<T>builder()
                .code(CodeEnum.OK.getCode())
                .msg(msg)
                .timestamp(System.currentTimeMillis())
                .data(data)
                .build();
    }

    public static <T> R<T> fail() {
        return R.<T>builder()
                .code(CodeEnum.FAIL.getCode())
                .msg(CodeEnum.FAIL.getMsg())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> R<T> fail(CodeEnum codeEnum) {
        return R.<T>builder()
                .code(codeEnum.getCode())
                .msg(codeEnum.getMsg())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> R<T> fail(String msg) {
        return R.<T>builder()
                .code(CodeEnum.FAIL.getCode())
                .msg(msg)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> R<T> fail(String msg, CodeEnum codeEnum) {
        return R.<T>builder()
                .code(codeEnum.getCode())
                .msg(msg)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}

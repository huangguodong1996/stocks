package com.seek.stocks.enumitem;

import com.seek.stocks.exception.BaseError;

public enum CommonEnum implements BaseError {

    // 数据操作错误定义

    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!"),
    UNAUTHORIZED("555","无权限访问！"),
    FORBIDDEN("444","禁止访问!");


    /** 错误码 */
    private String code;

    /** 错误描述 */
    private String msg;

    CommonEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }


}

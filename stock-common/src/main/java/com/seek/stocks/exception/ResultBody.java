package com.seek.stocks.exception;

import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.enumitem.CommonEnum;

public class ResultBody {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public ResultBody() {
    }

    public ResultBody(BaseError errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMsg();
    }

    /**
     * 禁止访问
     * @param message
     * @return
     */
    public static ResultBody forbidden(String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(CommonEnum.FORBIDDEN.getCode());
        rb.setMessage(message);
        return rb;
    }

    public static ResultBody unauthorized(String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(CommonEnum.FORBIDDEN.getCode());
        rb.setMessage(message);
        return rb;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultBody success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(CommonEnum.SUCCESS.getCode());
        rb.setMessage(CommonEnum.SUCCESS.getMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(BaseError errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getCode());
        rb.setMessage(errorInfo.getMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String code, String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error( String message) {
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

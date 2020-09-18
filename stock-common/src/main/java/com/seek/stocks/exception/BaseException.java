package com.seek.stocks.exception;

public class BaseException extends RuntimeException{

    private String errorCode;
    private String errorMsg;


    public BaseException(){
        super();
    }

    public BaseException(BaseError error) {
        super(error.getCode());
        this.errorCode = error.getCode();
        this.errorMsg = error.getMsg();
    }

    public BaseException(BaseError error, Throwable cause) {
        super(error.getCode(), cause);
        this.errorCode = error.getCode();
        this.errorMsg = error.getMsg();
    }

    public BaseException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

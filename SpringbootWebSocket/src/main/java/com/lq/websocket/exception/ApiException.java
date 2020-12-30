package com.lq.websocket.exception;

/**
 * @Description
 * @Date 2020/12/29 14:21
 * @Author lq
 */
public class ApiException extends RuntimeException {
    private String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

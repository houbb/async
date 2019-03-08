package com.github.houbb.async.core.exception;

/**
 * 异步运行时异常
 * @author binbin.hou
 * @date 2019/3/8
 * @since 0.0.2
 */
public class AsyncRuntimeException extends RuntimeException{

    public AsyncRuntimeException() {
    }

    public AsyncRuntimeException(String message) {
        super(message);
    }

    public AsyncRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AsyncRuntimeException(Throwable cause) {
        super(cause);
    }

    public AsyncRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

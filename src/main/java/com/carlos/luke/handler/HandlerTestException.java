package com.carlos.luke.handler;

public class HandlerTestException extends RuntimeException{
    private static final long serialVersionUID = 4872955409094780147L;

    public HandlerTestException() {
        super();
    }

    public HandlerTestException(String message) {
        super(message);
    }

    public HandlerTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerTestException(Throwable cause) {
        super(cause);
    }
}

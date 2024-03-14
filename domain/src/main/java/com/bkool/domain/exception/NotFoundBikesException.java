package com.bkool.domain.exception;

import java.io.Serial;

public class NotFoundBikesException extends Exception {

    @Serial
    private static final long serialVersionUID = -8618601744247517166L;

    private final String message;

    public NotFoundBikesException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

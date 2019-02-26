package com.codenation.mapfood.exception;

public class NoMotoboyInRangeException extends Exception {

    public final String message = "There is no motoboy in range";

    public NoMotoboyInRangeException() {
        super();
    }

    public NoMotoboyInRangeException(String message) {
        super(message);
    }
}

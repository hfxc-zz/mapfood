package com.codenation.mapfood.exception;

public class NoMotoboyInRangeException extends Exception {

    String message = "There is no motoboy in range";

    public NoMotoboyInRangeException() {
        super();
    }

    public NoMotoboyInRangeException(String message) {
        super(message);
    }
}

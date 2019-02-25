package com.codenation.mapfood.exception;

public class NoRouteFoundException extends Exception {
    String message = "There is no route";

    public NoRouteFoundException() {
        super();
    }

    public NoRouteFoundException(String message) {
        super(message);
    }
}


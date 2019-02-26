package com.codenation.mapfood.exception;

public class ResourceNotFoundException extends Exception {

    public final String message = "Resource not found";

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

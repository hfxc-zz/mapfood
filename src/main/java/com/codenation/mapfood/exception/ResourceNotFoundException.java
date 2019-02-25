package com.codenation.mapfood.exception;

/**
 * Created by hfxc on 24/02/19.
 */
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

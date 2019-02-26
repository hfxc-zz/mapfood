package com.codenation.mapfood.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDTO {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;

    public ErrorDTO(HttpStatus httpStatus, String message) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

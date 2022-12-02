package com.mangtaeblog.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MangtaeBlogException extends RuntimeException{

    private final Map<String, String> validation = new HashMap<>();

    public MangtaeBlogException(String message) {
        super(message);
    }

    public MangtaeBlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName,message);
    }
}

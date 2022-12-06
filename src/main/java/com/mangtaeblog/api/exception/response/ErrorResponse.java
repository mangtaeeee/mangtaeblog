package com.mangtaeblog.api.exception.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

//에러 응답을 위한 클래스
@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public void addValidation(String filedName, String message) {
        this.validation.put(filedName,message);
    }
}

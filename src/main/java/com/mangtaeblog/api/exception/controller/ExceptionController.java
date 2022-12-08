package com.mangtaeblog.api.exception.controller;

import com.mangtaeblog.api.exception.MangtaeBlogException;
import com.mangtaeblog.api.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class) //@Valid를 통해 떨어지는 예외가 MethodArgumentNotValidException
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();


        for (FieldError error : e.getFieldErrors()) {
            errorResponse.addValidation(error.getField(), error.getDefaultMessage());
        }
        return errorResponse;

    }




    @ResponseBody
    @ExceptionHandler(MangtaeBlogException.class)
    public ResponseEntity<ErrorResponse> mangtaeBlogException(MangtaeBlogException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);

        return response;

    }


}

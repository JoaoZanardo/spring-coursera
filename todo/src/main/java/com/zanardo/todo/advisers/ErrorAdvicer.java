package com.zanardo.todo.advisers;

import com.zanardo.todo.models.Errors.CustomError;
import com.zanardo.todo.models.Errors.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvicer {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleOtherExceptions(CustomError e) {
        int code = e.getCode();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (code == HttpStatus.UNAUTHORIZED.value()) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        if (code == HttpStatus.FORBIDDEN.value()) {
            httpStatus = HttpStatus.FORBIDDEN;
        }
        if (code == HttpStatus.NOT_FOUND.value()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (code == HttpStatus.CONFLICT.value()) {
            httpStatus = HttpStatus.CONFLICT;
        }
        if (code == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }
        if (code == HttpStatus.TOO_MANY_REQUESTS.value()) {
            httpStatus = HttpStatus.TOO_MANY_REQUESTS;
        }
        if (code == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (code == HttpStatus.NOT_IMPLEMENTED.value()) {
            httpStatus = HttpStatus.NOT_IMPLEMENTED;
        }
        if (code == HttpStatus.BAD_GATEWAY.value()) {
            httpStatus = HttpStatus.BAD_GATEWAY;
        }
        if (code == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        }
        if (code == HttpStatus.GATEWAY_TIMEOUT.value()) {
            httpStatus = HttpStatus.GATEWAY_TIMEOUT;
        }

        return new ResponseEntity<ErrorModel>(e.format(), httpStatus);
    }
}

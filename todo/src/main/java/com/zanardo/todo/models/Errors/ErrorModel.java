package com.zanardo.todo.models.Errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel {
    private String message;
    private String status;
    private int code;

    public ErrorModel(
            String message,
            String status,
            int code
    ) {
        System.out.println("ASd as as dasdSA A AA A A A ");
        this.message = message;
        this.status = status;
        this.code = code;
    }
}

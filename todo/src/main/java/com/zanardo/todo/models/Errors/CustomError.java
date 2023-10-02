package com.zanardo.todo.models.Errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomError extends RuntimeException {
    private String status;
    private int code;

    public CustomError (String message) {
        super(message);
    }

    public ErrorModel format () {
        return new ErrorModel(
                this.getMessage(),
                this.getStatus(),
                this.getCode()
        );
    }
}

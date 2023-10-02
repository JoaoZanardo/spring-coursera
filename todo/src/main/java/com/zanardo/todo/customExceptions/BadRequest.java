package com.zanardo.todo.customExceptions;

import com.zanardo.todo.models.Errors.CustomError;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BadRequest extends CustomError {
    public BadRequest(String message) {
        super(message);

        this.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        this.setCode(HttpStatus.BAD_REQUEST.value());
    }
}

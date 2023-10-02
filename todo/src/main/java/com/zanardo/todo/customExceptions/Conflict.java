package com.zanardo.todo.customExceptions;

import com.zanardo.todo.models.Errors.CustomError;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class Conflict extends CustomError {
    public Conflict(String message) {
        super(message);

        this.setStatus(HttpStatus.CONFLICT.getReasonPhrase());
        this.setCode(HttpStatus.CONFLICT.value());
    }
}

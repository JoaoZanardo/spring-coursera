package com.zanardo.todo.customExceptions;

import com.zanardo.todo.models.Errors.CustomError;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFound extends CustomError {
    public NotFound(String message) {
        super(message);

        this.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
        this.setCode(HttpStatus.NOT_FOUND.value());
    }
}

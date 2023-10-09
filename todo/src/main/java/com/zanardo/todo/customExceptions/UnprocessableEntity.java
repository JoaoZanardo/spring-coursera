package com.zanardo.todo.customExceptions;

import com.zanardo.todo.models.Errors.CustomError;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UnprocessableEntity extends CustomError {
    public UnprocessableEntity(String message) {
        super(message);

        this.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        this.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }
}

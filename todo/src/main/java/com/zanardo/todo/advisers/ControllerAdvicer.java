package com.zanardo.todo.advisers;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zanardo.todo.customExceptions.BadRequest;
import com.zanardo.todo.customExceptions.Conflict;
import com.zanardo.todo.customExceptions.NotFound;
import com.zanardo.todo.customExceptions.UnprocessableEntity;
import com.zanardo.todo.models.Errors.CustomError;
import com.zanardo.todo.models.Errors.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ControllerAdvicer {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorModel> handleAccessDeniedException(AccessDeniedException e) {
        ErrorModel error = new ErrorModel(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorModel> handleTokenExpiredException(TokenExpiredException e) {
        ErrorModel error = new ErrorModel(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorModel> handleJWTVerificationException(JWTVerificationException e) {
        ErrorModel error = new ErrorModel(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ErrorModel> handleJWTCreationException(JWTCreationException e) {
        ErrorModel error = new ErrorModel(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<ErrorModel> handleUnprocessableEntity(UnprocessableEntity e) {
        ErrorModel error = new ErrorModel(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                HttpStatus.UNPROCESSABLE_ENTITY.value()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorModel> handleNotFoundException(NotFound e) {
        return new ResponseEntity<>(e.format(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Conflict.class)
    public ResponseEntity<ErrorModel> handleConflictException(Conflict e) {
        return new ResponseEntity<>(e.format(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ErrorModel> handleBadRequestException(BadRequest e) {
        return new ResponseEntity<>(e.format(), HttpStatus.BAD_REQUEST);
    }
}

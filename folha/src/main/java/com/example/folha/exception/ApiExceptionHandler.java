package com.example.folha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ApiRequestForbidden.class})
    public ResponseEntity<Object> handleApiRequestForbidden(ApiRequestForbidden e){
        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        ApiException apiException = new ApiException(
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, forbidden);
    }

    @ExceptionHandler(value = {ApiRequestConflict.class})
    public ResponseEntity<Object> handleApiRequestForbidden(ApiRequestConflict e){
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, conflict);
    }
}

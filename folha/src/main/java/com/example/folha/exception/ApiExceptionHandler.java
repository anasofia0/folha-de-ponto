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
        ApiExp apiExp = new ApiExp(
                e.getMessage()
        );
        return new ResponseEntity<>(apiExp, badRequest);
    }

    @ExceptionHandler(value = {ApiRequestForbidden.class})
    public ResponseEntity<Object> handleApiRequestForbidden(ApiRequestForbidden e){
        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        ApiExp apiExp = new ApiExp(
                e.getMessage()
        );
        return new ResponseEntity<>(apiExp, forbidden);
    }

    @ExceptionHandler(value = {ApiRequestConflict.class})
    public ResponseEntity<Object> handleApiRequestForbidden(ApiRequestConflict e){
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiExp apiExp = new ApiExp(
                e.getMessage()
        );
        return new ResponseEntity<>(apiExp, conflict);
    }
}
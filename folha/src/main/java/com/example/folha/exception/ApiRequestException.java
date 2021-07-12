package com.example.folha.exception;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String mensagem) {
        super(mensagem);
    }
}

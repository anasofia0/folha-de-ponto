package com.example.folha.exception;

public class ApiRequestExcept extends RuntimeException {

    public ApiRequestExcept(String mensagem) {
        super(mensagem);
    }
}

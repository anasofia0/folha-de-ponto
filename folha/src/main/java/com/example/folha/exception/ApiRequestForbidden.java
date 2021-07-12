package com.example.folha.exception;

public class ApiRequestForbidden extends RuntimeException {
    public ApiRequestForbidden(String mensagem) {
        super(mensagem);
    }
}

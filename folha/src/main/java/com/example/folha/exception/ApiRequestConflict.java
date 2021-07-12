package com.example.folha.exception;

public class ApiRequestConflict extends RuntimeException {
    public ApiRequestConflict(String mensagem) {
        super(mensagem);
    }
}

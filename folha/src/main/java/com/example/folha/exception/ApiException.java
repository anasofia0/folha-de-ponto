package com.example.folha.exception;

public class ApiException {
    private final String mensagem;

    public ApiException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}

package com.example.folha.exception;

public class ApiExp {
    private final String mensagem;

    public ApiExp(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}

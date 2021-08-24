package com.example.folha.utils;

public enum Constante {

    INICIO_EXPEDIENTE(7),
    FIM_EXPEDIENTE(22),
    MAX_HORARIOS_REGISTRADOS(4),
    ULTIMA_POSICAO_STRING_DATA(10),
    UMA_HORA_SEGUNDO(3600);

    private int valor;

    Constante(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

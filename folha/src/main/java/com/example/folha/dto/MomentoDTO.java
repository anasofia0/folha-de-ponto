package com.example.folha.dto;

import java.time.LocalDate;

public class MomentoDTO {

    private LocalDate dataHora;

    public MomentoDTO(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }
}

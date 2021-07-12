package com.example.folha.dto;


import com.example.folha.entity.Momento;

public class MomentoDTO {

    private String dataHora;

    public MomentoDTO(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}

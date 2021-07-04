package com.example.folha.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MomentoDTO {

    private Long id;
    private LocalDate dataHora;

    public MomentoDTO(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }
}

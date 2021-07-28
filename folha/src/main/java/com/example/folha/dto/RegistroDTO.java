package com.example.folha.dto;

import java.time.LocalDate;
import java.util.List;

public class RegistroDTO {

    private LocalDate dia;
    private List<String> horarios;

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public List<String> getHorarios() {
        return horarios;
    }

}

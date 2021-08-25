package com.example.folha.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {
    private String dia;
    private List<String> horarios;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }
}

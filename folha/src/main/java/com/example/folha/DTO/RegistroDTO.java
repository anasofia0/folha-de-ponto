package com.example.folha.DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegistroDTO {

    private Long id;
    private LocalDate dia;
    private ArrayList horarios;

    public RegistroDTO(LocalDate dia, ArrayList horarios) {
        this.dia = dia;
        this.horarios = horarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public ArrayList getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList horarios) {
        this.horarios = horarios;
    }
}

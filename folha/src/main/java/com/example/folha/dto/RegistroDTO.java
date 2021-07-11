package com.example.folha.dto;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroDTO {

    private Long id;
    private LocalDate dia;
    private List<String> horarios;

    public RegistroDTO(LocalDate dia, List<String> horarios) {
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

    public List getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList horarios) {
        this.horarios = horarios;
    }
}

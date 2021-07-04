package com.example.folha.Entity;

import java.io.Serializable;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dia;
    private ArrayList<String> horarios;


    public Registro(LocalDate dia, ArrayList horarios) {
        this.dia = dia;
        this.horarios = horarios;
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

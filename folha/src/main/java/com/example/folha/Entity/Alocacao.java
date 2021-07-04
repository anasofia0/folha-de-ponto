package com.example.folha.Entity;

import java.io.Serializable;
//@Entity
//@EntityScan
public class Alocacao implements Serializable {

    private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dia;
    private String tempo;
    private String nomeProjeto;

    public Alocacao(String dia, String tempo, String nomeProjeto) {
        this.dia = dia;
        this.tempo = tempo;
        this.nomeProjeto = nomeProjeto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }
}

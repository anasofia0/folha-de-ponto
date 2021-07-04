package com.example.folha.DTO;

public class AlocacaoDTO {

    private Long id;
    private String dia;
    private String tempo;
    private String nomeProjeto;

    public AlocacaoDTO(String dia, String tempo, String nomeProjeto) {
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

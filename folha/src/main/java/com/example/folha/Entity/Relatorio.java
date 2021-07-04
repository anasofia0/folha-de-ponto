package com.example.folha.Entity;

import java.io.Serializable;

//@Entity
//@EntityScan
public class Relatorio implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mes;
    private String horasTrabalhadas;
    private String horasExcedentes;
    private String horasDevidas;
    private Registro registro;
    private Alocacao alocacao;

    public Relatorio(String mes, String horasTrabalhadas, String horasExcedentes, String horasDevidas, Registro registro, Alocacao alocacao) {
        this.mes = mes;
        this.horasTrabalhadas = horasTrabalhadas;
        this.horasExcedentes = horasExcedentes;
        this.horasDevidas = horasDevidas;
        this.registro = registro;
        this.alocacao = alocacao;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(String horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getHorasExcedentes() {
        return horasExcedentes;
    }

    public void setHorasExcedentes(String horasExcedentes) {
        this.horasExcedentes = horasExcedentes;
    }

    public String getHorasDevidas() {
        return horasDevidas;
    }

    public void setHorasDevidas(String horasDevidas) {
        this.horasDevidas = horasDevidas;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Alocacao getAlocacao() {
        return alocacao;
    }

    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
    }
}

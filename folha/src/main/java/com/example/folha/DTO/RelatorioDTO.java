package com.example.folha.DTO;

import com.example.folha.Entity.Alocacao;
import com.example.folha.Entity.Registro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RelatorioDTO {

    private Long id;

    private String mes;
    private String horasTrabalhadasMes;
    private String horasExcedentesMes;
    private String horasDevidasMes;
    private HashMap<Integer, ArrayList<String>> registroDiaHorariosMes;
    private HashMap<String, String> alocacaoProjetoTempoMes;


    public RelatorioDTO(String mes, String horasTrabalhadasMes, String horasExcedentesMes, String horasDevidasMes,
                        HashMap<Integer, ArrayList<String>> registroDiaHorariosMes, HashMap<String, String> alocacaoProjetoTempoMes) {
        this.mes = mes;
        this.horasTrabalhadasMes = horasTrabalhadasMes;
        this.horasExcedentesMes = horasExcedentesMes;
        this.horasDevidasMes = horasDevidasMes;
        this.registroDiaHorariosMes = registroDiaHorariosMes;
        this.alocacaoProjetoTempoMes = alocacaoProjetoTempoMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getHorasTrabalhadasMes() {
        return horasTrabalhadasMes;
    }

    public void setHorasTrabalhadasMes(String horasTrabalhadasMes) {
        this.horasTrabalhadasMes = horasTrabalhadasMes;
    }

    public String getHorasExcedentesMes() {
        return horasExcedentesMes;
    }

    public void setHorasExcedentesMes(String horasExcedentesMes) {
        this.horasExcedentesMes = horasExcedentesMes;
    }

    public String getHorasDevidasMes() {
        return horasDevidasMes;
    }

    public void setHorasDevidasMes(String horasDevidasMes) {
        this.horasDevidasMes = horasDevidasMes;
    }

    public HashMap<Integer, ArrayList<String>> getRegistroDiaHorariosMes() {
        return registroDiaHorariosMes;
    }

    public void setRegistroDiaHorariosMes(HashMap<Integer, ArrayList<String>> registroDiaHorariosMes) {
        this.registroDiaHorariosMes = registroDiaHorariosMes;
    }

    public HashMap<String, String> getAlocacaoProjetoTempoMes() {
        return alocacaoProjetoTempoMes;
    }

    public void setAlocacaoProjetoTempoMes(HashMap<String, String> alocacaoProjetoTempoMes) {
        this.alocacaoProjetoTempoMes = alocacaoProjetoTempoMes;
    }
}

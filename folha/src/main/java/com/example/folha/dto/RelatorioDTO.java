package com.example.folha.dto;

import java.util.ArrayList;
import java.util.Map;

public class RelatorioDTO {
    
    private String mes;
    private String horasTrabalhadasMes;
    private String horasExcedentesMes;
    private String horasDevidasMes;
    private Map<Integer, ArrayList<String>> registroDiaHorariosMes;
    private Map<String, String> alocacaoProjetoTempoMes;


    public RelatorioDTO(String mes, String horasTrabalhadasMes, String horasExcedentesMes, String horasDevidasMes,
                        Map<Integer, ArrayList<String>> registroDiaHorariosMes, Map<String, String> alocacaoProjetoTempoMes) {
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

    public Map<Integer, ArrayList<String>> getRegistroDiaHorariosMes() {
        return registroDiaHorariosMes;
    }

    public void setRegistroDiaHorariosMes(Map<Integer, ArrayList<String>> registroDiaHorariosMes) {
        this.registroDiaHorariosMes = registroDiaHorariosMes;
    }

    public Map<String, String> getAlocacaoProjetoTempoMes() {
        return alocacaoProjetoTempoMes;
    }

    public void setAlocacaoProjetoTempoMes(Map<String, String> alocacaoProjetoTempoMes) {
        this.alocacaoProjetoTempoMes = alocacaoProjetoTempoMes;
    }
}

package com.example.folha.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class RelatorioDTO {
    
    private String mes;
    private String horasTrabalhadasMes;
    private String horasExcedentesMes;
    private String horasDevidasMes;
    private List<RegistroDTO> registrosDTO;
    private AlocacaoDTO alocacaoDTO;


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

    public List<RegistroDTO> getRegistrosDTO() {
        return registrosDTO;
    }

    public void setRegistrosDTO(List<RegistroDTO> registrosDTO) {
        this.registrosDTO = registrosDTO;
    }

    public AlocacaoDTO getAlocacaoDTO() {
        return alocacaoDTO;
    }


    public void setAlocacaoDTO(AlocacaoDTO alocacaoDTO) {
        this.alocacaoDTO = alocacaoDTO;
    }
}

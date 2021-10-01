package com.example.folha.service;

import com.example.folha.dto.AlocacaoDTO;
import com.example.folha.entity.Alocacao;
import com.example.folha.entity.Momento;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.exception.ApiRequestForbidden;
import com.example.folha.repository.AlocacaoRepository;
import com.example.folha.repository.BatidasRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlocacaoService {
    private static final int ULTIMA_POSICAO_STRING_DATA = 10;
    private static final int MAXIMA_BATIDAS_DIA = 4;

    private final AlocacaoRepository alocacaoRepository;
    private final BatidasRepository batidasRepository;

    public AlocacaoService(AlocacaoRepository alocacaoRepository, BatidasRepository batidasRepository) {
        this.alocacaoRepository = alocacaoRepository;
        this.batidasRepository = batidasRepository;
    }

    public void criarAlocacao(AlocacaoDTO alocacaoDTO) {
        verificaQuantidadeHoras(alocacaoDTO);
        this.alocacaoRepository.save(toEntity(alocacaoDTO));
    }

    public Alocacao toEntity(AlocacaoDTO alocacaoDTO) {
        Alocacao alocacao = new Alocacao();
        alocacao.setDia(alocacaoDTO.getDia());
        alocacao.setTempo(alocacaoDTO.getTempo());
        alocacao.setNomeProjeto(alocacaoDTO.getNomeProjeto());
        return alocacao;
    }

    private void verificaQuantidadeHoras(AlocacaoDTO alocacaoDTO) {
        List<Momento> horariosRegistradosDia = horariosRegistradosDia(alocacaoDTO);

        int horasTrabalhadas = 0;

        if (horariosRegistradosDia.size() == MAXIMA_BATIDAS_DIA) {
            LocalDateTime primeiroMomento = LocalDateTime.parse(horariosRegistradosDia.get(0).getDataHora());
            LocalDateTime segundoMomento = LocalDateTime.parse(horariosRegistradosDia.get(1).getDataHora());
            LocalDateTime terceiroMomento = LocalDateTime.parse(horariosRegistradosDia.get(2).getDataHora());
            LocalDateTime quartoMomento = LocalDateTime.parse(horariosRegistradosDia.get(3).getDataHora());

            horasTrabalhadas += Duration.between(primeiroMomento, segundoMomento).getSeconds();
            horasTrabalhadas += Duration.between(terceiroMomento, quartoMomento).getSeconds();
        }

        LocalDateTime horasAlocadas = LocalDateTime.parse(alocacaoDTO.getTempo());
        int tempoAlocado = horasAlocadas.getHour() * 3600 + horasAlocadas.getMinute() * 60 + horasAlocadas.getSecond();

        if(horasTrabalhadas < tempoAlocado) {
            throw new ApiRequestExcept("Não pode alocar tempo maior que o tempo trabalhado no dia");
        }

    }

    private List<Momento> horariosRegistradosDia(AlocacaoDTO alocacaoDTO) {
        List<Momento> listaHorariosJaRegistradosTotal = this.batidasRepository.findAll();
        String parteData = alocacaoDTO.getDia();
        List<Momento> listaHorariosJaregistradosPorDia = new ArrayList<>();

        listaHorariosJaRegistradosTotal.forEach((Momento data) -> {
            if (data.getDataHora().substring(0, ULTIMA_POSICAO_STRING_DATA).equals(parteData)) {
                listaHorariosJaregistradosPorDia.add(data);
            }
        });
        return listaHorariosJaregistradosPorDia;
    }

}

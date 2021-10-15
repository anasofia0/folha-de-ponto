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

        long horasAlocadas = Duration.parse(alocacaoDTO.getTempo()).getSeconds();
        long horasTrabalhadas = getHorasTrabalhadasDia(alocacaoDTO);

        if(horasTrabalhadas < horasAlocadas){
            throw new ApiRequestExcept("Não pode alocar tempo maior que o tempo trabalhado no dia");
        }
    }

    public long getHorasTrabalhadasDia (AlocacaoDTO alocacaoDTO) {

        long horasTrabalhadas = 0;

        List<Momento> horariosRegistradosDia = horariosRegistradosDia(alocacaoDTO);

        if (horariosRegistradosDia.size() == MAXIMA_BATIDAS_DIA) {

            long primeiroMomento = converteTempoParaSegundos(LocalDateTime.parse(horariosRegistradosDia.get(0).getDataHora()));
            long segundoMomento = converteTempoParaSegundos(LocalDateTime.parse(horariosRegistradosDia.get(1).getDataHora()));
            long terceiroMomento = converteTempoParaSegundos(LocalDateTime.parse(horariosRegistradosDia.get(2).getDataHora()));
            long quartoMomento = converteTempoParaSegundos(LocalDateTime.parse(horariosRegistradosDia.get(3).getDataHora()));

            horasTrabalhadas += segundoMomento-primeiroMomento;
            horasTrabalhadas += quartoMomento-terceiroMomento;
        }

        return horasTrabalhadas;
    }

    public long converteTempoParaSegundos (LocalDateTime tempo) {
        return 3600 * tempo.getHour() + 60 * tempo.getMinute() + tempo.getSecond();
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

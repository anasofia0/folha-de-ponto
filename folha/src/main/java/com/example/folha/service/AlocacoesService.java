package com.example.folha.service;

import com.example.folha.dto.AlocacaoDTO;
import com.example.folha.dto.MomentoDTO;
import com.example.folha.entity.Alocacao;
import com.example.folha.entity.Momento;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.repository.AlocacoesRepository;
import com.example.folha.repository.BatidasRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.folha.utils.Constantes.ULTIMA_POSICAO_STRING_DATA;

@Service
public class AlocacoesService {

    private final BatidasRepository batidasRepository;
    private final AlocacoesRepository alocacoesRepository;

    public AlocacoesService(BatidasRepository batidasRepository, AlocacoesRepository alocacoesRepository) {
        this.batidasRepository = batidasRepository;
        this.alocacoesRepository = alocacoesRepository;
    }

    public void criarAlocacao(AlocacaoDTO alocacaoDTO) {
        validaQuantidadeHoraAlocada(alocacaoDTO);
        alocacoesRepository.save(toEntity(alocacaoDTO));
    }

    public Alocacao toEntity(AlocacaoDTO alocacaoDTO) {
        Alocacao alocacao = new Alocacao();
        alocacao.setDia(alocacaoDTO.getDia());
        alocacao.setNomeProjeto(alocacaoDTO.getNomeProjeto());
        alocacao.setTempo(alocacaoDTO.getTempo());
        return alocacao;
    }

    public void validaQuantidadeHoraAlocada(AlocacaoDTO alocacaoDTO) {
        Duration tempo = Duration.parse(alocacaoDTO.getTempo());
        if (tempo.getSeconds() > segundosRegistradosPorDia(alocacaoDTO)) {
            throw new ApiRequestExcept("Não pode alocar tempo maior que o tempo trabalhado no dia");
        }
    }

    public Long segundosRegistradosPorDia(AlocacaoDTO alocacaoDTO) {
        String dataAlocacao = "%" + alocacaoDTO.getDia() + "%";
        List<String> listaMomentosRegistrados = this.batidasRepository.findByDate(dataAlocacao);
        int tamanhoLista = listaMomentosRegistrados.size();

        LocalDateTime primeiroLista = LocalDateTime.parse(listaMomentosRegistrados.get(0).getDataHora());
        LocalDateTime ultimoLista = LocalDateTime.parse(listaMomentosRegistrados.get(tamanhoLista-1)
                .getDataHora());

        return Duration.between(primeiroLista, ultimoLista).getSeconds();
    }


}

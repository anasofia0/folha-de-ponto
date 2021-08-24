package com.example.folha.service;

import com.example.folha.dto.AlocacaoDTO;
import com.example.folha.dto.MomentoDTO;
import com.example.folha.entity.Alocacao;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.repository.AlocacoesRepository;
import com.example.folha.repository.BatidasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

        Alocacao alocacao = new Alocacao();
        BeanUtils.copyProperties(alocacaoDTO, alocacao);
        alocacoesRepository.save(alocacao);
    }

    public void validaQuantidadeHoraAlocada(AlocacaoDTO alocacaoDTO) {
        Duration tempo = Duration.parse(alocacaoDTO.getTempo());
        if (tempo.getSeconds() > segundosRegistradosPorDia(alocacaoDTO)) {
            throw new ApiRequestExcept("Não pode alocar tempo maior que o tempo trabalhado no dia");
        }
    }

    public Long segundosRegistradosPorDia(AlocacaoDTO alocacaoDTO) {
        String dataAlocacao = "%" + alocacaoDTO.getDia() + "%";
        List<MomentoDTO> listaMomentosRegistrados = this.batidasRepository.findByDate(dataAlocacao);

        if(listaMomentosRegistrados.isEmpty()){
            throw new ApiRequestExcept("Não há registros de batidas no dia pedido!");
        }

        int tamanhoLista = listaMomentosRegistrados.size();
        LocalDateTime primeiroLista = LocalDateTime.parse(listaMomentosRegistrados.get(0).getDataHora());
        LocalDateTime ultimoLista = LocalDateTime.parse(listaMomentosRegistrados.get(tamanhoLista-1)
                .getDataHora());

        return Duration.between(primeiroLista, ultimoLista).getSeconds();
    }


}

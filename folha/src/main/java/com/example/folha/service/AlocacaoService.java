package com.example.folha.service;

import com.example.folha.dto.AlocacaoDTO;
import com.example.folha.entity.Alocacao;
import com.example.folha.repository.AlocacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlocacaoService{
//verificar se o que ele colocou corresponde com o que ta no banco
    private final AlocacaoRepository alocacaoRepository;

    public AlocacaoService(AlocacaoRepository alocacaoRepository) {
        this.alocacaoRepository = alocacaoRepository;
    }

    public void criarAlocacao(AlocacaoDTO alocacaoDTO) {

        this.alocacaoRepository.save(toEntity(alocacaoDTO));
    }

    public Alocacao toEntity(AlocacaoDTO alocacaoDTO) {
        Alocacao alocacao = new Alocacao();
        alocacao.setDia(alocacaoDTO.getDia());
        alocacao.setTempo(alocacaoDTO.getTempo());
        alocacao.setNomeProjeto(alocacaoDTO.getNomeProjeto());
        return alocacao;
    }
}

package com.example.folha.service;

import com.example.folha.entity.Alocacao;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.repository.AlocacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioService {

    private final int INDEX_FINAL_ANO = 4;
    private final int INDEX_COMECO_MES = 5;

    private AlocacaoRepository alocacaoRepository;

    public RelatorioService(AlocacaoRepository alocacaoRepository) {
        this.alocacaoRepository = alocacaoRepository;
    }

    public void encontraRelatorio(String mes) {
        List<Alocacao> alocacoesRegistradasMes = getAlocacoesRegistradasMes(mes);

    }

    public List<Alocacao> getAlocacoesRegistradasMes(String mes) {
        List<Alocacao> listaAlocacoesRegistradasTotal = this.alocacaoRepository.findAll();
        List<Alocacao> listaAlocacoesRegistradasMes = new ArrayList<>();

        listaAlocacoesRegistradasTotal.forEach((Alocacao alocacao) -> {
            LocalDate data = LocalDate.parse(alocacao.getDia());
            int anoProcurado = Integer.parseInt(mes.substring(0, INDEX_FINAL_ANO));
            int mesProcurado = Integer.parseInt(mes.substring(INDEX_COMECO_MES));
            if (data.getYear() == anoProcurado && data.getMonth().getValue()==mesProcurado) {
                listaAlocacoesRegistradasMes.add(alocacao);
            }
        });
        return listaAlocacoesRegistradasMes;
    }

}

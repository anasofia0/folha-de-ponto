package com.example.folha.resource;

import com.example.folha.dto.AlocacaoDTO;
import com.example.folha.entity.Alocacao;
import com.example.folha.service.AlocacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/alocacoes")
public class AlocacaoResource {

    private final AlocacaoService alocacaoService;

    public AlocacaoResource(AlocacaoService alocacaoService) {
        this.alocacaoService = alocacaoService;
    }

    @PostMapping
    public ResponseEntity<Alocacao> criarAlocacao(@Valid @RequestBody AlocacaoDTO alocacaoDTO) {
        this.alocacaoService.criarAlocacao(alocacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
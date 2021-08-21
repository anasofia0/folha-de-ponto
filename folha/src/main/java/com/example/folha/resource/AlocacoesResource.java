package com.example.folha.resource;

import com.example.folha.dto.AlocacaoDTO;
import com.example.folha.entity.Alocacao;
import com.example.folha.service.AlocacoesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alocacoes")
public class AlocacoesResource {

    private final AlocacoesService alocacoesService;

    public AlocacoesResource(AlocacoesService alocacoesService) {
        this.alocacoesService = alocacoesService;
    }

    @PostMapping
    public ResponseEntity<Alocacao> criarAlocacao(@RequestBody AlocacaoDTO alocacaoDTO) {
        this.alocacoesService.criarAlocacao(alocacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

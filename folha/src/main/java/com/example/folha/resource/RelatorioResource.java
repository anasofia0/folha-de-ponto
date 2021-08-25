package com.example.folha.resource;

import com.example.folha.dto.RelatorioDTO;
import com.example.folha.service.RelatorioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folhas-de-ponto")
public class RelatorioResource {

    private final RelatorioService relatorioService;

    public RelatorioResource(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/{mes}")
    public ResponseEntity<RelatorioDTO> criarRelatorio(@PathVariable(value = "mes", required = true) String mes) {
        RelatorioDTO relatorioDTO = relatorioService.listarRelatorio(mes);
        return ResponseEntity.ok(relatorioDTO);
    }
}

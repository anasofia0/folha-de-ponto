package com.example.folha.resource;

import com.example.folha.entity.Relatorio;
import com.example.folha.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folhas-de-ponto/{mes}")
public class RelatorioResource {

    private final RelatorioService relatorioService;

    public RelatorioResource(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public ResponseEntity<Relatorio> apresentaRelatorio(@PathVariable String mes) {
        relatorioService.encontraRelatorio(mes);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

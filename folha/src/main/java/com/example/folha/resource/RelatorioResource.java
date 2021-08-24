package com.example.folha.resource;

import com.example.folha.entity.Alocacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folhas-de-ponto")
public class RelatorioResource {

    @PostMapping("/{mes}")
    public ResponseEntity<Alocacao> criarAlocacao(@PathVariable String mes) {
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }
}

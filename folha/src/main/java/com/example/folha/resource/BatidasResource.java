package com.example.folha.resource;


import com.example.folha.dto.MomentoDTO;
import com.example.folha.entity.Momento;
import com.example.folha.service.BatidasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/batidas")
public class BatidasResource {

    private final BatidasService batidasService;

    public BatidasResource(BatidasService batidasService) {
        this.batidasService = batidasService;
    }

    @PostMapping
    public ResponseEntity<Momento> criarBatida(@Valid @RequestBody MomentoDTO momentoDTO) {
        this.batidasService.criarMomento(momentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

package com.example.folha.resource;


import com.example.folha.entity.Momento;
import com.example.folha.service.BatidasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/batidas")
public class BatidasResource {

    private final BatidasService batidasService;

    public BatidasResource(BatidasService batidasService) {
        this.batidasService = batidasService;
    }

    @PostMapping
    public ResponseEntity<Momento> criarBatida(@RequestBody Momento momento) {
        Momento momentoCriado = this.batidasService.criarMomento(momento);
//       return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.ok(momentoCriado);
    }
}

package com.example.folha.service;

import com.example.folha.entity.Momento;
import com.example.folha.repository.BatidasRepository;
import org.springframework.stereotype.Service;

@Service
public class BatidasService {

    private BatidasRepository batidasRepository;

    public BatidasService(BatidasRepository batidasRepository) {
        this.batidasRepository = batidasRepository;
    }

    public Momento criarMomento(Momento momento) {
        return this.batidasRepository.save(momento);
    }

}

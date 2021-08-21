package com.example.folha.repository;

import com.example.folha.entity.Alocacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlocacoesRepository extends JpaRepository<Alocacao, Long> {
}

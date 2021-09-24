package com.example.folha.repository;


import com.example.folha.entity.Alocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

}

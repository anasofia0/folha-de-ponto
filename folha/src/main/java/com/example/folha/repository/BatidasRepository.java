package com.example.folha.repository;

import com.example.folha.dto.MomentoDTO;
import com.example.folha.entity.Momento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatidasRepository extends JpaRepository<Momento, Long> {

    boolean existsByDataHora(String dataHora);

    @Query("SELECT m.dataHora from Momento m " +
            "where m.dataHora like :dataAlocacao")
    List<Momento> findByDate(@Param("dataAlocacao") String dataAlocacao);
}

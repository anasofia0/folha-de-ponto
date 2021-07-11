package com.example.folha.repository;

import com.example.folha.entity.Momento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatidasRepository extends JpaRepository<Momento, Long> {


}

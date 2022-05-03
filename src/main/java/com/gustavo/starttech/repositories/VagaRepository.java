package com.gustavo.starttech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.starttech.entities.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
		
}

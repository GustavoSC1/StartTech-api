package com.gustavo.starttech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.starttech.entities.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}

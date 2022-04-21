package com.gustavo.starttech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.starttech.entities.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	@Transactional(readOnly=true)
	boolean existsByEmailOrCnpj(String email, String cnpj);
	
}

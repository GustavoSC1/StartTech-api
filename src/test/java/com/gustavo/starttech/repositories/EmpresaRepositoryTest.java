package com.gustavo.starttech.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavo.starttech.entities.Empresa;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class EmpresaRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Test
	@DisplayName("Must save a Empresa")
	public void saveEmpresaTest() {		
		// Cenario
		Empresa newEmpresa = createNewEmpresa();
				
		// Execução
		Empresa savedEmpresa = empresaRepository.save(newEmpresa);
		
		// Execução
		Assertions.assertThat(savedEmpresa.getId()).isNotNull();		
	}
	
	@Test
	@DisplayName("Must get one empresa per id")
	public void findByIdTest() {
		// Cenario
		Empresa empresa = createNewEmpresa();
		entityManager.persist(empresa);
		
		// Execução
		Optional<Empresa> foundEmpresa = empresaRepository.findById(empresa.getId());
		empresaRepository.existsById(empresa.getId());
		
		// Execução
		Assertions.assertThat(foundEmpresa.isPresent()).isTrue();
	}
	
	@Test
	@DisplayName("Must check if there is a company with the email or cnpj provided")
	public void existsByEmailOrCnpjTest() {
		// Cenario
		Empresa empresa = createNewEmpresa();
		entityManager.persist(empresa);
		
		// Execução
		boolean foundEmpresa = empresaRepository.existsByEmailOrCnpj(empresa.getEmail(), empresa.getCnpj());
		
		// Execução
		Assertions.assertThat(foundEmpresa).isTrue();
	}
	
	@Test
	@DisplayName("Should return false when verifying the existence of a company by email or cnpj")
	public void empresaNotExistsByEmailOrCnpjTest() {
		// Cenario
		Empresa empresa = createNewEmpresa();
		entityManager.persist(empresa);
		
		// Execução
		boolean foundEmpresa = empresaRepository.existsByEmailOrCnpj("facebook@gmail.com", "51799337000142");
		
		// Execução
		Assertions.assertThat(foundEmpresa).isFalse();
	}
	
	private Empresa createNewEmpresa() {
		return new Empresa(null, "Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
	}
	
}

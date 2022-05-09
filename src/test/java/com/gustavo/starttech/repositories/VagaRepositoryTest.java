package com.gustavo.starttech.repositories;

import java.time.LocalDateTime;
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

import com.gustavo.starttech.entities.Vaga;
import com.gustavo.starttech.entities.enums.ModalidadeVaga;
import com.gustavo.starttech.entities.enums.StatusVaga;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class VagaRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	VagaRepository vagaRepository;
	
	@Test
	@DisplayName("Must save a job opportunity")
	public void saveVagaTest() {
		// Scenario
		Vaga newVaga = createNewVaga();
		
		// Execution
		Vaga savedVaga = vagaRepository.save(newVaga);
		
		// Verification
		Assertions.assertThat(savedVaga.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Must get one job opportunity per id")
	public void findByIdTest() {
		// Scenario
		Vaga vaga = createNewVaga();
		entityManager.persist(vaga);
		
		// Execution
		Optional<Vaga> foundVaga = vagaRepository.findById(vaga.getId());
	
		// Verification
		Assertions.assertThat(foundVaga.isPresent()).isTrue();
	}
	
	private Vaga createNewVaga() {
		return new Vaga(null, "Estágio Java", "Vaga para Estágio Java", 1200.0,LocalDateTime.of(2022, 11, 12, 10, 15), StatusVaga.ABERTA, ModalidadeVaga.REMOTO, null);
	}
	
}

package com.gustavo.starttech.repositories;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
	VagaRepository vagaRepository;
	
	@Test
	@DisplayName("Must save a job opportunity")
	public void saveVagaTest() {
		// Scenario
		Vaga newVaga = new Vaga(null, "Estágio Java", "Vaga para Estágio Java", 1200.0,LocalDate.of(2002, 11, 12), StatusVaga.ABERTA, ModalidadeVaga.REMOTO, null, null);
		
		// Execution
		Vaga savedVaga = vagaRepository.save(newVaga);
		
		// Verification
		Assertions.assertThat(savedVaga.getId()).isNotNull();
	}
	
}

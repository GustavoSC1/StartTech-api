package com.gustavo.starttech.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavo.starttech.entities.Estado;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class EstadoRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Test
	@DisplayName("Must save a state")
	public void saveEstadoTest() {
		// Scenario
		Estado newEstado = new Estado(null, "Bahia");
		
		// Execution
		Estado savedEstado = estadoRepository.save(newEstado);
		
		// Verification
		Assertions.assertThat(savedEstado.getId()).isNotNull();
	}

}

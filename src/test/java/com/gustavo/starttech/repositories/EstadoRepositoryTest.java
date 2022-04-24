package com.gustavo.starttech.repositories;

import java.util.List;

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
	
	@Test
	@DisplayName("Must get all states order by name")
	public void findAllByOrderByNomeTest() {
		// Scenario
		Estado estado = new Estado(null, "Bahia");
		Estado estado2 = new Estado(null, "São Paulo");
		
		entityManager.persist(estado);
		entityManager.persist(estado2);
		
		// Execution
		List<Estado> estados = estadoRepository.findAllByOrderByNome();
		
		// Verification
		Assertions.assertThat(estados.size()).isEqualTo(2);
		Assertions.assertThat(estados.get(0).getNome()).isEqualTo(estado.getNome());
		Assertions.assertThat(estados.get(1).getNome()).isEqualTo(estado2.getNome());
	}

}

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

import com.gustavo.starttech.entities.Cidade;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class CidadeRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Test
	@DisplayName("Must save a city")
	public void saveCidadeTest() {
		// Scenario
		Cidade newCidade = new Cidade(null, "Paramirim", null);
		
		// Execution
		Cidade savedCidade = cidadeRepository.save(newCidade);
		
		// Verification
		Assertions.assertThat(savedCidade.getId()).isNotNull();
	}

}

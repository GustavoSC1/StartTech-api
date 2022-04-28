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

import com.gustavo.starttech.entities.Cidade;
import com.gustavo.starttech.entities.Estado;

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
	
	@Test
	@DisplayName("Must get all cities in a state sorted by name")
	public void findCidadesTest() {
		// Scenario
		Estado estado = new Estado(null, "Bahia");
		Cidade cidade = new Cidade(null, "Paramirim", estado);
		Cidade cidade1 = new Cidade(null, "Vitória da Conquista", estado);
				
		entityManager.persist(estado);
		entityManager.persist(cidade);
		entityManager.persist(cidade1);
		
		// Execution
		List<Cidade> cidades = cidadeRepository.findCidades(estado.getId());
		
		// Verification
		Assertions.assertThat(cidades.size()).isEqualTo(2);
		Assertions.assertThat(cidades.get(0).getNome()).isEqualTo(cidade.getNome());
		Assertions.assertThat(cidades.get(1).getNome()).isEqualTo(cidade1.getNome());
	}

}

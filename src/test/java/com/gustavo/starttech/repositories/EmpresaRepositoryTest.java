package com.gustavo.starttech.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavo.starttech.entities.Empresa;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class EmpresaRepositoryTest {
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Test
	@DisplayName("Must save a Empresa")
	public void saveEmpresaTest() {		
		Empresa newEmpresa = new Empresa(null, "Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
				
		Empresa savedEmpresa = empresaRepository.save(newEmpresa);
		
		Assertions.assertThat(savedEmpresa.getId()).isNotNull();		
	}

}

package com.gustavo.starttech.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavo.starttech.dtos.EmpresaDTO;
import com.gustavo.starttech.dtos.EmpresaNewDTO;
import com.gustavo.starttech.entities.Empresa;
import com.gustavo.starttech.repositories.EmpresaRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class EmpresaServiceTest {
	
	EmpresaService empresaService;
	
	@MockBean
	EmpresaRepository empresaRepository;
	
	@BeforeEach
	public void setUp() {
		this.empresaService = new EmpresaService(empresaRepository);
	}
	
	@Test
	@DisplayName("Must save a Empresa")
	public void saveEmpresaTest() {
		// Cenário
		Long id = 2l;
		EmpresaNewDTO newEmpresa = new EmpresaNewDTO("Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
		Empresa savedEmpresa = new Empresa(id, "Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
		
		Mockito.when(empresaRepository.save(Mockito.any(Empresa.class))).thenReturn(savedEmpresa);
		
		// Execução
		EmpresaDTO savedEmpresaDto = empresaService.save(newEmpresa);
		
		// Verificação
		Assertions.assertThat(savedEmpresaDto.getId()).isEqualTo(id);
		Assertions.assertThat(savedEmpresaDto.getNome()).isEqualTo("Google LLC");
		Assertions.assertThat(savedEmpresaDto.getEmail()).isEqualTo("google@gmail.com");
		Assertions.assertThat(savedEmpresaDto.getCnpj()).isEqualTo("51799337000141");	
	}
	
}

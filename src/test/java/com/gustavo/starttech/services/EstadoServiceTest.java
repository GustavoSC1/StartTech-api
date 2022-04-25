package com.gustavo.starttech.services;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavo.starttech.dtos.EstadoDTO;
import com.gustavo.starttech.entities.Estado;
import com.gustavo.starttech.repositories.EstadoRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class EstadoServiceTest {
	
	EstadoService estadoService;
	
	@MockBean
	EstadoRepository estadoRepository;
	
	@BeforeEach
	public void setUp() {
		this.estadoService = new EstadoService(estadoRepository);
	}
	
	@Test
	@DisplayName("Must get all states order by name")
	public void findAllTest() {
		// Scenario
		List<Estado> estados = new ArrayList<>();
		estados.add(new Estado(1l, "Bahia"));
		estados.add(new Estado(2l, "São Paulo"));
		
		Mockito.when(estadoRepository.findAllByOrderByNome()).thenReturn(estados);
		
		// Execution
		List<EstadoDTO> estadosDto = estadoService.findAll();
	
		// Verification
		Assertions.assertThat(estadosDto.size()).isEqualTo(estados.size());
		Assertions.assertThat(estadosDto.get(0).getNome()).isEqualTo(estados.get(0).getNome());
		Assertions.assertThat(estadosDto.get(1).getNome()).isEqualTo(estados.get(1).getNome());
	}
	
}

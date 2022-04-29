package com.gustavo.starttech.services;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavo.starttech.dtos.CidadeDTO;
import com.gustavo.starttech.entities.Cidade;
import com.gustavo.starttech.entities.Estado;
import com.gustavo.starttech.repositories.CidadeRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CidadeServiceTest {
	
	CidadeService cidadeService;
	
	@MockBean
	CidadeRepository cidadeRepository;
	
	public void setUp() {
		this.cidadeService = new CidadeService(cidadeRepository);
	}
	
	@Test
	@DisplayName("Must get all cities in a state sorted by name")
	public void findByEstadoTest() {
		// Scenario
		Estado estado = new Estado(1l, "Bahia");
		
		List<Cidade> cidades = new ArrayList<>();
		cidades.add(new Cidade(1l, "Paramirim", estado));
		cidades.add(new Cidade(2l, "Vitória da Conquista", estado));
	
		Mockito.when(cidadeRepository.findCidades(estado.getId())).thenReturn(cidades);
		
		// Execution
		List<CidadeDTO>  cidadesDto = cidadeService.findByEstado(estado.getId());
		
		// Verification
		Assertions.assertThat(cidadesDto.size()).isEqualTo(cidades.size());
		Assertions.assertThat(cidadesDto.get(0).getNome()).isEqualTo(cidades.get(0).getNome());
		Assertions.assertThat(cidadesDto.get(1).getNome()).isEqualTo(cidades.get(1).getNome());
	}
	
}

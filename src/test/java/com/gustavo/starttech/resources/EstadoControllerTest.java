package com.gustavo.starttech.resources;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.gustavo.starttech.dtos.CidadeDTO;
import com.gustavo.starttech.dtos.EstadoDTO;
import com.gustavo.starttech.services.CidadeService;
import com.gustavo.starttech.services.EstadoService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = EstadoController.class)
@AutoConfigureMockMvc
public class EstadoControllerTest {
	
	static String ESTADO_API = "/estados";
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	EstadoService estadoService;
	
	@MockBean
	CidadeService cidadeService;
	
	@Test
	@DisplayName("Must get all states order by name")
	public void findAllTest() throws Exception {
		// Scenario
		List<EstadoDTO> estadosDto = new ArrayList<>();
		estadosDto.add(new EstadoDTO(1l, "Bahia"));
		estadosDto.add(new EstadoDTO(2l, "São Paulo"));
		
		BDDMockito.given(estadoService.findAll()).willReturn(estadosDto);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.get(ESTADO_API)
													.accept(MediaType.APPLICATION_JSON);
	
		// Verification
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));		
	}
	
	@Test
	@DisplayName("Must get all cities in a state sorted by name")
	public void findCidadesTest() throws Exception {
		// Scenario
		Long id = 1l;
		
		List<CidadeDTO> cidadesDto = new ArrayList<>();
		cidadesDto.add(new CidadeDTO(1l, "Paramirim"));
		cidadesDto.add(new CidadeDTO(2l, "Vitória da Conquista"));
		
		BDDMockito.given(cidadeService.findByEstado(id)).willReturn(cidadesDto);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.get(ESTADO_API.concat("/"+id+"/cidades"))
													.accept(MediaType.APPLICATION_JSON);
		// Verification
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));		
	}

}

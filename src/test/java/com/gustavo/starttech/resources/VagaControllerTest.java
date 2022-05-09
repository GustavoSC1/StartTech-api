package com.gustavo.starttech.resources;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.starttech.dtos.VagaDTO;
import com.gustavo.starttech.dtos.VagaNewDTO;
import com.gustavo.starttech.entities.enums.ModalidadeVaga;
import com.gustavo.starttech.services.VagaService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = VagaController.class)
@AutoConfigureMockMvc
public class VagaControllerTest {
	
	static String VAGA_API = "/vagas";
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	VagaService vagaService;
	
	@Test
	@DisplayName("Must save a job opportunity")
	public void saveVagaTest() throws Exception {
		// Scenario
		Long id = 1l;
		VagaNewDTO newVaga = new VagaNewDTO("Estágio Java", "Vaga para Estágio Java", 1200.0, ModalidadeVaga.REMOTO, "Rua Mamoré", "779", "Nenhum", "Chácaras Alto do Marimbondo", "15706216", id, id);
		
		VagaDTO savedVaga = new VagaDTO();
		savedVaga.setId(id);
		
		BDDMockito.given(vagaService.save(Mockito.any(VagaNewDTO.class))).willReturn(savedVaga);
		
		String json = new ObjectMapper().writeValueAsString(newVaga);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post(VAGA_API)
													.contentType(MediaType.APPLICATION_JSON)
													.accept(MediaType.APPLICATION_JSON)
													.content(json);
		
		// Verification
		mvc
		.perform(request)
		.andExpect( MockMvcResultMatchers.status().isCreated() )
		.andExpect( MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, Matchers.containsString("/vagas/"+id)) );
	}
	
	@Test
	@DisplayName("Should throw validation error when there is not enough data for job opportunity creation")
	public void createInvalidVagaTest() throws Exception {
		// Scenario
		VagaNewDTO newVaga = new VagaNewDTO();
		newVaga.setEmpresaId(0l);
		newVaga.setSalario(-1.0);
		String json = new ObjectMapper().writeValueAsString(newVaga);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(VAGA_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		// Verification
		mvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
			.andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(5)));		
	}

}

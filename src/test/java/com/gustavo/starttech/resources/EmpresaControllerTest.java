package com.gustavo.starttech.resources;

import org.hamcrest.Matchers;
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
import com.gustavo.starttech.dtos.EmpresaDTO;
import com.gustavo.starttech.dtos.EmpresaNewDTO;
import com.gustavo.starttech.services.EmpresaService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = EmpresaController.class)
@AutoConfigureMockMvc
public class EmpresaControllerTest {
	
	static String EMPRESA_API = "/empresas";
	
	@Autowired
	MockMvc mvc;
	
	@MockBean 
	EmpresaService empresaService;
	
	@Test	
	public void saveEmpresaTest() throws Exception {
		
		long id = 2l;
		
		EmpresaNewDTO newEmpresa = new EmpresaNewDTO("Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
		EmpresaDTO savedEmpresa = new EmpresaDTO(id, "Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
		
		BDDMockito.given(empresaService.save(Mockito.any(EmpresaNewDTO.class))).willReturn(savedEmpresa);
	
		String json = new ObjectMapper().writeValueAsString(newEmpresa);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post(EMPRESA_API)
													.contentType(MediaType.APPLICATION_JSON)
													.accept(MediaType.APPLICATION_JSON)
													.content(json);
		
		mvc
		.perform(request)
		.andExpect( MockMvcResultMatchers.status().isCreated() )
		.andExpect( MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, Matchers.containsString("/empresas/"+id)) );
	}

}

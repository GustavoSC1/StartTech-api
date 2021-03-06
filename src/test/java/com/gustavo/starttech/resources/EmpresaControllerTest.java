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
import com.gustavo.starttech.dtos.EmpresaDTO;
import com.gustavo.starttech.dtos.EmpresaNewDTO;
import com.gustavo.starttech.entities.Empresa;
import com.gustavo.starttech.services.EmpresaService;
import com.gustavo.starttech.services.exceptions.BusinessException;
import com.gustavo.starttech.services.exceptions.ObjectNotFoundException;

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
	@DisplayName("Must save a company")
	public void saveEmpresaTest() throws Exception {
		// Scenario
		long id = 2l;
		
		EmpresaNewDTO newEmpresa = new EmpresaNewDTO("Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
		EmpresaDTO savedEmpresa = createNewEmpresaDto();
		savedEmpresa.setId(id);
		
		BDDMockito.given(empresaService.save(Mockito.any(EmpresaNewDTO.class))).willReturn(savedEmpresa);

		String json = new ObjectMapper().writeValueAsString(newEmpresa);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post(EMPRESA_API)
													.contentType(MediaType.APPLICATION_JSON)
													.accept(MediaType.APPLICATION_JSON)
													.content(json);
		
		// Verification
		mvc
		.perform(request)
		.andExpect( MockMvcResultMatchers.status().isCreated() )
		.andExpect( MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, Matchers.containsString("/empresas/"+id)) );
	}
	
	@Test
	@DisplayName("Should throw business error when trying to save a company with duplicate email or cnpj")
	public void shouldNotSaveACompanyWithDuplicatedEmailOrCnpj() throws Exception {
		// Scenario
		EmpresaNewDTO newEmpresa = new EmpresaNewDTO("Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");

		BDDMockito.given(empresaService.save(Mockito.any(EmpresaNewDTO.class))).willThrow(new BusinessException("Email ou CNPJ j?? cadastrado!"));
		
		String json = new ObjectMapper().writeValueAsString(newEmpresa);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(EMPRESA_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		// Verification
		mvc
		.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("error").value("Exce????o de neg??cio"))
		.andExpect(MockMvcResultMatchers.jsonPath("message").value("Email ou CNPJ j?? cadastrado!"));
	}
		
	@Test
	@DisplayName("Should throw validation error when there is not enough data for company creation")
	public void createInvalidEmpresaTest() throws Exception {
		// Scenario
		String json = new ObjectMapper().writeValueAsString(new EmpresaNewDTO());
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(EMPRESA_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		// Verification
		mvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
			.andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(6)));		
	}
	
	@Test
	@DisplayName("Must get one company per id")
	public void findEmpresaTest() throws Exception {
		// Scenario
		Long id = 2l;
		
		EmpresaDTO empresa = createNewEmpresaDto();
		empresa.setId(id);
		
		BDDMockito.given(empresaService.find(id)).willReturn(empresa);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(EMPRESA_API.concat("/"+id)).accept(MediaType.APPLICATION_JSON);
		
		// Verification
		mvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("id").value(id))
			.andExpect(MockMvcResultMatchers.jsonPath("nome").value("Google LLC"))
			.andExpect(MockMvcResultMatchers.jsonPath("email").value("google@gmail.com"))
			.andExpect(MockMvcResultMatchers.jsonPath("cnpj").value("51799337000141"))
			.andExpect(MockMvcResultMatchers.jsonPath("nomeFantasia").value("Google"));
		
	}
	
	@Test
	@DisplayName("Should return error when trying to get a non-existent company")
	public void empresaNotFoundByIdTest() throws Exception {
		// Scenario
		Long id = 2l;
		
		BDDMockito.given(empresaService.find(id)).willThrow(new ObjectNotFoundException("Objeto n??o encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(EMPRESA_API.concat("/"+id)).accept(MediaType.APPLICATION_JSON);
		
		// Verification
		mvc
		.perform(request)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("error").value("N??o encontrado"))
		.andExpect(MockMvcResultMatchers.jsonPath("message").value("Objeto n??o encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
	}
	
	@Test
	@DisplayName("Must update a company")
	public void updateEmpresaTest() throws Exception {
		// Scenario
		Long id = 2l;
		
		EmpresaDTO empresaDto = new EmpresaDTO(id, "Meta Platforms, Inc.", "meta@gmail.com", "1829129908", "23488218876", "15546120000166", "Meta");
		
		BDDMockito.given(empresaService.update(Mockito.anyLong(), Mockito.any(EmpresaDTO.class))).willReturn(empresaDto);
		
		String json = new ObjectMapper().writeValueAsString(empresaDto);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.put(EMPRESA_API.concat("/"+id))
													.contentType(MediaType.APPLICATION_JSON)
													.accept(MediaType.APPLICATION_JSON)
													.content(json);
		
		// Verification
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("id").value(id))
		.andExpect(MockMvcResultMatchers.jsonPath("nome").value("Meta Platforms, Inc."))
		.andExpect(MockMvcResultMatchers.jsonPath("email").value("meta@gmail.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("cnpj").value("15546120000166"))
		.andExpect(MockMvcResultMatchers.jsonPath("nomeFantasia").value("Meta"));
	}
	
	@Test
	@DisplayName("Should throw validation error when there is not enough data to update the company")
	public void updateInvalidEmpresaTest() throws Exception {
		// Scenario
		Long id = 2l;
		
		String json = new ObjectMapper().writeValueAsString(new EmpresaDTO());
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(EMPRESA_API.concat("/"+id))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		// Verification
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
		.andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(6)));
	}
	
	@Test
	@DisplayName("Should return error when trying to update a non-existent company")
	public void empresaNotUpdatedByIdTest() throws Exception {
		// Scenario
		Long id = 2l;
		
		EmpresaDTO empresaDto = new EmpresaDTO(id, "Meta Platforms, Inc.", "meta@gmail.com", "1829129908", "23488218876", "15546120000166", "Meta");
		
		BDDMockito.given(empresaService.update(Mockito.anyLong(), Mockito.any(EmpresaDTO.class))).willThrow(new ObjectNotFoundException("Objeto n??o encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
		
		String json = new ObjectMapper().writeValueAsString(empresaDto);
		
		// Execution
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(EMPRESA_API.concat("/"+id))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		// Verification
		mvc
		.perform(request)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("error").value("N??o encontrado"))
		.andExpect(MockMvcResultMatchers.jsonPath("message").value("Objeto n??o encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
	}
	
	private EmpresaDTO createNewEmpresaDto() {
		return new EmpresaDTO(null, "Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
	}

}

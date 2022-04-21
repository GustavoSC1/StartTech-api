package com.gustavo.starttech.services;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.gustavo.starttech.services.exceptions.ObjectNotFoundException;

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
		Empresa savedEmpresa = createNewEmpresa();
		savedEmpresa.setId(id);
		
		Mockito.when(empresaRepository.save(Mockito.any(Empresa.class))).thenReturn(savedEmpresa);
		
		// Execução
		EmpresaDTO savedEmpresaDto = empresaService.save(newEmpresa);
		
		// Verificação
		Assertions.assertThat(savedEmpresaDto.getId()).isEqualTo(id);
		Assertions.assertThat(savedEmpresaDto.getNome()).isEqualTo("Google LLC");
		Assertions.assertThat(savedEmpresaDto.getEmail()).isEqualTo("google@gmail.com");
		Assertions.assertThat(savedEmpresaDto.getCnpj()).isEqualTo("51799337000141");	
	}
	
	@Test
	@DisplayName("Must get one empresa per id")
	public void findEmpresaTest() {
		// Cenário
		Long id = 2l;
		
		Empresa empresa = createNewEmpresa();
		empresa.setId(id);
		
		Mockito.when(empresaRepository.findById(id)).thenReturn(Optional.of(empresa));
		
		// Execução
		EmpresaDTO foundEmpresa  = empresaService.find(id);
		
		// Verificação
		Assertions.assertThat(foundEmpresa.getId()).isEqualTo(empresa.getId());
		Assertions.assertThat(foundEmpresa.getNome()).isEqualTo(empresa.getNome());
		Assertions.assertThat(foundEmpresa.getEmail()).isEqualTo(empresa.getEmail());
		Assertions.assertThat(foundEmpresa.getCnpj()).isEqualTo(empresa.getCnpj());	
	}
	
	@Test
	@DisplayName("Should return error when trying to get a non-existent company")
	public void empresaNotFoundByIdTest() {
		// Cenário
		Long id = 1l;
		Mockito.when(empresaRepository.findById(id)).thenReturn(Optional.empty());
		
		// Execução		
		Exception exception = assertThrows(ObjectNotFoundException.class, () -> {empresaService.find(id);});
		
		String expectedMessage = "Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName();
		String actualMessage = exception.getMessage();
		
		Assertions.assertThat(actualMessage).isEqualTo(expectedMessage);
	}
	
	@Test
	@DisplayName("Must update a empresa")
	public void updateEmpresaTest() {
		// Cenário
		Long id = 2l;
		
		EmpresaDTO empresaDto = new EmpresaDTO(id, "Meta Platforms, Inc.", "meta@gmail.com", "1829129908", "23488218876", "51799337000567", "Meta");
		
		Empresa foundEmpresa = createNewEmpresa();
		foundEmpresa.setId(id);
		
		Empresa updatedEmpresa = new Empresa(id, "Meta Platforms, Inc.", "meta@gmail.com", "1829129908", "23488218876", "51799337000567", "Meta");
		
		Mockito.when(empresaRepository.findById(id)).thenReturn(Optional.of(foundEmpresa));
		Mockito.when(empresaRepository.save(Mockito.any(Empresa.class))).thenReturn(updatedEmpresa);
		
		// Execução		
		EmpresaDTO updatedEmpresaDto =  empresaService.update(id, empresaDto);
				
		// Verificação
		Assertions.assertThat(updatedEmpresaDto.getId()).isEqualTo(empresaDto.getId());
		Assertions.assertThat(updatedEmpresaDto.getNome()).isEqualTo(empresaDto.getNome());
		Assertions.assertThat(updatedEmpresaDto.getEmail()).isEqualTo(empresaDto.getEmail());
		Assertions.assertThat(updatedEmpresaDto.getCnpj()).isEqualTo(empresaDto.getCnpj());
		Assertions.assertThat(updatedEmpresaDto.getNomeFantasia()).isEqualTo(empresaDto.getNomeFantasia());
	}
	
	@Test
	@DisplayName("Should return error when trying to update a non-existent company")
	public void empresaNotUpdatedByIdTest() {
		// Cenário
		Long id = 1l;
		EmpresaDTO empresaDto = new EmpresaDTO(createNewEmpresa());
		Mockito.when(empresaRepository.findById(id)).thenReturn(Optional.empty());
		
		// Execução		
		Exception exception = assertThrows(ObjectNotFoundException.class, () -> {empresaService.update(id, empresaDto);});
		
		String expectedMessage = "Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName();
		String actualMessage = exception.getMessage();
		
		Assertions.assertThat(actualMessage).isEqualTo(expectedMessage);
	}
	
	private Empresa createNewEmpresa() {
		return new Empresa(null, "Google LLC", "google@gmail.com", "1629129421", "16988218142", "51799337000141", "Google");
	}
	
}

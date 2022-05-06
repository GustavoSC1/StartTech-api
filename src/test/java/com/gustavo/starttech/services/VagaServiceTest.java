package com.gustavo.starttech.services;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavo.starttech.dtos.VagaDTO;
import com.gustavo.starttech.dtos.VagaNewDTO;
import com.gustavo.starttech.entities.Cidade;
import com.gustavo.starttech.entities.Empresa;
import com.gustavo.starttech.entities.Endereco;
import com.gustavo.starttech.entities.Estado;
import com.gustavo.starttech.entities.Vaga;
import com.gustavo.starttech.entities.enums.ModalidadeVaga;
import com.gustavo.starttech.entities.enums.StatusVaga;
import com.gustavo.starttech.repositories.VagaRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class VagaServiceTest {
	
	VagaService vagaService;
	
	@MockBean
	EmpresaService empresaService;
	
	@MockBean
	VagaRepository vagaRepository;
	
	@BeforeEach
	public void setUp() {
		this.vagaService = new VagaService(vagaRepository, empresaService);
	}
	
	@Test
	@DisplayName("Must save a job opportunity")
	public void saveVagaTest() {
		// Scenario
		Long id = 1l;
		VagaNewDTO newVaga = new VagaNewDTO("Estágio Java", "Vaga para Estágio Java", 1200.0, ModalidadeVaga.REMOTO, "Rua Mamoré", "779", "Nenhum", "Chácaras Alto do Marimbondo", "15706216", id, id);
		Empresa empresa = new Empresa();
		empresa.setId(id);
		Vaga savedVaga = createNewVaga();
		savedVaga.setId(id);
		
		Mockito.when(empresaService.findById(newVaga.getEmpresaId())).thenReturn(empresa);
		Mockito.when(vagaRepository.save(Mockito.any(Vaga.class))).thenReturn(savedVaga);
		
		// Execution
		VagaDTO savedVagaDTO = vagaService.save(newVaga);
		
		// Verification
		Assertions.assertThat(savedVagaDTO.getId()).isEqualTo(id);
		Assertions.assertThat(savedVagaDTO.getTitulo()).isEqualTo("Estágio Java");
		Assertions.assertThat(savedVagaDTO.getDescricao()).isEqualTo("Vaga para Estágio Java");
		Assertions.assertThat(savedVagaDTO.getEndereco().getLogradouro()).isEqualTo("Rua Mamoré");
		Assertions.assertThat(savedVagaDTO.getEndereco().getCidade()).isEqualTo("São Paulo");
		Assertions.assertThat(savedVagaDTO.getEndereco().getEstado()).isEqualTo("São Paulo");
	}
	
	private Vaga createNewVaga() {
		Vaga vaga = new Vaga(null, "Estágio Java", "Vaga para Estágio Java", 1200.0, LocalDateTime.now(), StatusVaga.ABERTA, ModalidadeVaga.REMOTO, null);
		Estado estado = new Estado(1l, "São Paulo");
		Cidade cidade = new Cidade(1l, "São Paulo", estado);
		Endereco endereco = new Endereco(1l,  "Rua Mamoré", "779", "Nenhum", "Chácaras Alto do Marimbondo", "15706216", cidade, vaga);
		vaga.setEndereco(endereco);
		
		return vaga;
	}
}

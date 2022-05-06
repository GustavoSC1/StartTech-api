package com.gustavo.starttech.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.gustavo.starttech.dtos.VagaDTO;
import com.gustavo.starttech.dtos.VagaNewDTO;
import com.gustavo.starttech.entities.Cidade;
import com.gustavo.starttech.entities.Empresa;
import com.gustavo.starttech.entities.Endereco;
import com.gustavo.starttech.entities.Vaga;
import com.gustavo.starttech.entities.enums.StatusVaga;
import com.gustavo.starttech.repositories.VagaRepository;

@Service
public class VagaService {
	
	private VagaRepository vagaRepository;
	
	private EmpresaService empresaService;
	
	public VagaService(VagaRepository vagaRepository, EmpresaService empresaService) {
		this.vagaRepository = vagaRepository;
		this.empresaService = empresaService;
	}
	
	public VagaDTO save(VagaNewDTO vagaDto) {
		Empresa empresa = empresaService.findById(vagaDto.getEmpresaId());
		Vaga vaga = new Vaga(null, vagaDto.getTitulo(), vagaDto.getDescricao(), vagaDto.getSalario(), LocalDateTime.now(), StatusVaga.ABERTA, vagaDto.getModalidade(), empresa);
		Cidade cidade = new Cidade(vagaDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, vagaDto.getLogradouro(), vagaDto.getNumero(), vagaDto.getComplemento(), vagaDto.getBairro(), vagaDto.getCep(), cidade, vaga);
		vaga.setEndereco(endereco);
		
		vaga = vagaRepository.save(vaga);
		
		return new VagaDTO(vaga);
	}

}

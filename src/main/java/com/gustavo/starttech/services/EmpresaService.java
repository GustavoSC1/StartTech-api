package com.gustavo.starttech.services;

import org.springframework.stereotype.Service;

import com.gustavo.starttech.dtos.EmpresaDTO;
import com.gustavo.starttech.dtos.EmpresaNewDTO;
import com.gustavo.starttech.entities.Empresa;
import com.gustavo.starttech.repositories.EmpresaRepository;

@Service
public class EmpresaService {
	
	private EmpresaRepository empresaRepository;

	public EmpresaService(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	public EmpresaDTO save(EmpresaNewDTO empresaDto) {
		Empresa empresa  = new Empresa(null, empresaDto.getNome(), empresaDto.getEmail(), empresaDto.getTelefone(), empresaDto.getCelular(), empresaDto.getCnpj(), empresaDto.getNomeFantasia());
		
		empresa = empresaRepository.save(empresa);
		
		return new EmpresaDTO(empresa);
	}

}

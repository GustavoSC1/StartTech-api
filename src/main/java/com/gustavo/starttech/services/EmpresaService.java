package com.gustavo.starttech.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gustavo.starttech.dtos.EmpresaDTO;
import com.gustavo.starttech.dtos.EmpresaNewDTO;
import com.gustavo.starttech.entities.Empresa;
import com.gustavo.starttech.repositories.EmpresaRepository;
import com.gustavo.starttech.services.exceptions.ObjectNotFoundException;

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
	
	public EmpresaDTO find(Long id) {
		Empresa empresa = findById(id);
		return new EmpresaDTO(empresa);
	}
	
	public EmpresaDTO update(Long id, EmpresaDTO empresaDto) {
		Empresa empresa = findById(id);
		
		empresa.setNome(empresa.getNome());
		empresa.setCelular(empresa.getCelular());
		empresa.setTelefone(empresa.getTelefone());
		empresa.setCnpj(empresa.getCnpj());
		empresa.setNomeFantasia(empresa.getNomeFantasia());
		
		empresa = empresaRepository.save(empresa);
		
		return new EmpresaDTO(empresa);
	}
	
	public Empresa findById(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		Empresa empresa = empresaOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
				
		return empresa;
	}

}

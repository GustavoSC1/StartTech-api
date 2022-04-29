package com.gustavo.starttech.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gustavo.starttech.dtos.CidadeDTO;
import com.gustavo.starttech.repositories.CidadeRepository;

@Service
public class CidadeService {

	private CidadeRepository cidadeRepository;
	
	public CidadeService(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}
	
	public List<CidadeDTO> findByEstado(Long estadoId){
		return cidadeRepository.findCidades(estadoId).stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
	}
	
}

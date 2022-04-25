package com.gustavo.starttech.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gustavo.starttech.dtos.EstadoDTO;
import com.gustavo.starttech.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	private EstadoRepository estadoRepository;
	
	public EstadoService(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}
	
	public List<EstadoDTO> findAll() {
		return estadoRepository.findAllByOrderByNome().stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
	}

}

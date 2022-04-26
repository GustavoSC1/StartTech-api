package com.gustavo.starttech.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.starttech.dtos.EstadoDTO;
import com.gustavo.starttech.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	EstadoService estadoService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> listDto = estadoService.findAll();
		return ResponseEntity.ok().body(listDto);
	}

}

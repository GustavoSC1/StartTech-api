package com.gustavo.starttech.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.starttech.dtos.VagaDTO;
import com.gustavo.starttech.dtos.VagaNewDTO;
import com.gustavo.starttech.services.VagaService;

@RestController
@RequestMapping("/vagas")
public class VagaController {
	
	@Autowired
	private VagaService vagaService;
	
	@PostMapping
	public ResponseEntity<VagaDTO> save(@Valid @RequestBody VagaNewDTO vagaNewDTO) {
		VagaDTO vaga = vagaService.save(vagaNewDTO);
				
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(vaga.getId()).toUri();
		
		return ResponseEntity.created(uri).body(vaga);
	}

}

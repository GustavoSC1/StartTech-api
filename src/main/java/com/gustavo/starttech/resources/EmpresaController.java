package com.gustavo.starttech.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.starttech.dtos.EmpresaNewDTO;
import com.gustavo.starttech.dtos.EmpresaDTO;
import com.gustavo.starttech.services.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@PostMapping
	public ResponseEntity<EmpresaDTO> save(@Valid @RequestBody EmpresaNewDTO empresaNewDTO) {
		EmpresaDTO empresa = empresaService.save(empresaNewDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(empresa.getId()).toUri();
		
		return ResponseEntity.created(uri).body(empresa);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDTO> find(@PathVariable Long id) {
		EmpresaDTO empresa = empresaService.find(id);
		
		return ResponseEntity.ok().body(empresa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmpresaDTO> update(@PathVariable Long id, @RequestBody EmpresaDTO empresaDto) {
		EmpresaDTO empresa = empresaService.update(id, empresaDto);
		
		return ResponseEntity.ok().body(empresa);
	}

}

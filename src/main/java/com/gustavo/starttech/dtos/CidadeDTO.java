package com.gustavo.starttech.dtos;

import java.io.Serializable;

import com.gustavo.starttech.entities.Cidade;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	
	public CidadeDTO() {

	}

	public CidadeDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public CidadeDTO(Cidade cidade) {
		super();
		this.id = cidade.getId();
		this.nome = cidade.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}

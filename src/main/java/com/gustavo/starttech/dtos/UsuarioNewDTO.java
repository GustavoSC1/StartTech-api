package com.gustavo.starttech.dtos;

import java.io.Serializable;

public class UsuarioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String email;
	
	private String telefone;
	
	private String celular;
	
	public UsuarioNewDTO() {
		
	}

	public UsuarioNewDTO(String nome, String email, String telefone, String celular) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
}

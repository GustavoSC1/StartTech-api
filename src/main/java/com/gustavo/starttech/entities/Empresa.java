package com.gustavo.starttech.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("empresa")
public class Empresa extends Usuario {
	private static final long serialVersionUID = 1L;

	private String cnpj;
	
	private String nomeFantasia;
	
	public Empresa() {
		
	}

	public Empresa(Long id, String nome, String email, String telefone, String celular, String cnpj, String nomeFantasia) {
		super(id, nome, email, telefone, celular);
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

}

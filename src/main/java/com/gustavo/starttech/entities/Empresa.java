package com.gustavo.starttech.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("empresa")
public class Empresa extends Usuario {
	private static final long serialVersionUID = 1L;

	private String cnpj;
	
	private String nomeFantasia;
	
	@OneToMany(mappedBy = "empresa")
	private Set<Vaga> vagas = new HashSet<>();
	
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
	
	public Set<Vaga> getVagas() {
		return vagas;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setVagas(Set<Vaga> vagas) {
		this.vagas = vagas;
	}

}

package com.gustavo.starttech.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.gustavo.starttech.entities.enums.ModalidadeVaga;
import com.gustavo.starttech.entities.enums.StatusVaga;

@Entity
public class Vaga implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private Double salario;
	private LocalDate abertura;
	private StatusVaga status;
	private ModalidadeVaga modalidade;
		
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vaga")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
		
	public Vaga() {
		
	}

	public Vaga(Long id, String titulo, String descricao, Double salario, LocalDate abertura, StatusVaga status,
			ModalidadeVaga modalidade, Empresa empresa, Endereco endereco) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.salario = salario;
		this.abertura = abertura;
		this.status = status;
		this.modalidade = modalidade;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getSalario() {
		return salario;
	}

	public LocalDate getAbertura() {
		return abertura;
	}

	public StatusVaga getStatus() {
		return status;
	}

	public ModalidadeVaga getModalidade() {
		return modalidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public void setAbertura(LocalDate abertura) {
		this.abertura = abertura;
	}

	public void setStatus(StatusVaga status) {
		this.status = status;
	}

	public void setModalidade(ModalidadeVaga modalidade) {
		this.modalidade = modalidade;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

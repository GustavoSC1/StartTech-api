package com.gustavo.starttech.dtos;

import java.io.Serializable;

import com.gustavo.starttech.entities.enums.ModalidadeVaga;

public class VagaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descricao;
	private Double salario;
	private ModalidadeVaga modalidade;
	private String logradouro;
	private String numero;
	private String complemento;	
	private String bairro;
	private String cep;	
	private Long cidadeId;
	private Long empresaId;
		
	public VagaNewDTO() {
		
	}
	
	public VagaNewDTO(String titulo, String descricao, Double salario, ModalidadeVaga modalidade, String logradouro,
			String numero, String complemento, String bairro, String cep, Long cidadeId, Long empresaId) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.salario = salario;
		this.modalidade = modalidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
		this.empresaId = empresaId;
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

	public ModalidadeVaga getModalidade() {
		return modalidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public Long getEmpresaId() {
		return empresaId;
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

	public void setModalidade(ModalidadeVaga modalidade) {
		this.modalidade = modalidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}
	
}

package com.gustavo.starttech.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.gustavo.starttech.entities.Vaga;
import com.gustavo.starttech.entities.enums.ModalidadeVaga;
import com.gustavo.starttech.entities.enums.StatusVaga;

public class VagaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private Double salario;
	private LocalDateTime abertura;
	private StatusVaga status;
	private ModalidadeVaga modalidade;
	
	private EnderecoDTO endereco;
	
	public VagaDTO() {
		
	}

	public VagaDTO(Long id, String titulo, String descricao, Double salario, LocalDateTime abertura, StatusVaga status,
			ModalidadeVaga modalidade, EnderecoDTO endereco) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.salario = salario;
		this.abertura = abertura;
		this.status = status;
		this.modalidade = modalidade;
		this.endereco = endereco;
	}
	
	public VagaDTO(Vaga vaga) {
		super();
		this.id = vaga.getId();
		this.titulo = vaga.getTitulo();
		this.descricao = vaga.getDescricao();
		this.salario = vaga.getSalario();
		this.abertura = vaga.getAbertura();
		this.status = vaga.getStatus();
		this.modalidade = vaga.getModalidade();
		this.endereco = new EnderecoDTO(vaga.getEndereco());
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

	public LocalDateTime getAbertura() {
		return abertura;
	}

	public StatusVaga getStatus() {
		return status;
	}

	public ModalidadeVaga getModalidade() {
		return modalidade;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
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

	public void setAbertura(LocalDateTime abertura) {
		this.abertura = abertura;
	}

	public void setStatus(StatusVaga status) {
		this.status = status;
	}

	public void setModalidade(ModalidadeVaga modalidade) {
		this.modalidade = modalidade;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	
}

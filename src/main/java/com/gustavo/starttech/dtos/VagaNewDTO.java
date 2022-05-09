package com.gustavo.starttech.dtos;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.gustavo.starttech.entities.enums.ModalidadeVaga;

public class VagaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=10, max=120, message="O tamanho deve ser entre 10 e 120 caracteres")
	private String titulo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=20, message="O tamanho deve ser entre 3 e 120 caracteres")
	private String descricao;
	
	@Min(value = 0, message = "O salário não pode ser negativo")
	private Double salario;
	
	@NotNull(message="Preenchimento obrigatório")
	private ModalidadeVaga modalidade;
	
	private String logradouro;
	private String numero;
	private String complemento;	
	private String bairro;
	private String cep;		
	private Long cidadeId;
	
	@Min(value = 1, message = "empresaId não pode ser menor que 1")	
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

package com.gustavo.starttech.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

public class EmpresaNewDTO extends UsuarioNewDTO {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CNPJ(message="CNPJ inválido")
	private String cnpj;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=120, message="O tamanho deve ser entre 3 e 120 caracteres")
	private String nomeFantasia;
	
	public EmpresaNewDTO() {
		
	}

	public EmpresaNewDTO(String nome, String email, String telefone, String celular, String cnpj, String nomeFantasia) {
		super(nome, email, telefone, celular);
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

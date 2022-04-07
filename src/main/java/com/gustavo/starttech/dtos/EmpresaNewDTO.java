package com.gustavo.starttech.dtos;

public class EmpresaNewDTO extends UsuarioNewDTO {
	private static final long serialVersionUID = 1L;
	
	private String cnpj;
	
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

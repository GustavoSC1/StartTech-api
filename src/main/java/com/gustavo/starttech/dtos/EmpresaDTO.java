package com.gustavo.starttech.dtos;

import com.gustavo.starttech.entities.Empresa;

public class EmpresaDTO extends UsuarioDTO {
	private static final long serialVersionUID = 1L;

	private String cnpj;
	
	private String nomeFantasia;

	public EmpresaDTO() {
		
	}
	
	public EmpresaDTO(Empresa empresa) {
		super(empresa.getId(), empresa.getNome(), empresa.getEmail(), empresa.getTelefone(), empresa.getCelular());
		this.cnpj = empresa.getCnpj();
		this.nomeFantasia = empresa.getNomeFantasia();
	}

	public EmpresaDTO(Long id, String nome, String email, String telefone, String celular, String cnpj, String nomeFantasia) {
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

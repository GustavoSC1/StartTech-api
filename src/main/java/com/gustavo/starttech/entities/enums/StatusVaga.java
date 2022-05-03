package com.gustavo.starttech.entities.enums;

public enum StatusVaga {
	
	ABERTA("Aberta"),
	CANCELADA("Cancelada"),
	INCOMPLETO("Cancelada");
	
	private String descricao;
	
	private StatusVaga(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	

}

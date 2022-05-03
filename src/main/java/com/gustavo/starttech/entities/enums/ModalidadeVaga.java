package com.gustavo.starttech.entities.enums;

public enum ModalidadeVaga {
	
	PRESENCIAL("Aberta"),
	HIBRIDO("Híbrido"),
	REMOTO("Remoto");
	
	private String descricao;
	
	private ModalidadeVaga(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	

}

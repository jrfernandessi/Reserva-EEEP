package com.analistajunior.reserva.model;

public enum TipoUsuario {
	PROFESSOR("Professor"),
	DIRETOR("Diretor"),
	ADMINISTRADOR("Administrador");
	
	private String descricao;
	
	TipoUsuario(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}

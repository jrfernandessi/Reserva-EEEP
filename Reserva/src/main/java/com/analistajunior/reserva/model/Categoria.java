package com.analistajunior.reserva.model;

public enum Categoria {
	DATASHOW("Datashow"), 
	CAIXA("Caixa de som"), 
	MICROFONE("Microfone");
	
	private String descricao;
	
	Categoria(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}

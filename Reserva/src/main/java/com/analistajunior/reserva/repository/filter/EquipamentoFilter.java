package com.analistajunior.reserva.repository.filter;

import java.io.Serializable;

import com.analistajunior.reserva.model.Categoria;

public class EquipamentoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tombo;
	private Categoria[] categorias;

	public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}

	public Categoria[] getCategorias() {
		return categorias;
	}

	public void setCategorias(Categoria[] categorias) {
		this.categorias = categorias;
	}

}

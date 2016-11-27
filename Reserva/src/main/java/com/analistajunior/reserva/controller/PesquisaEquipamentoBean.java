package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.model.Categoria;
import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.repository.Equipamentos;
import com.analistajunior.reserva.repository.filter.EquipamentoFilter;

@ViewScoped
@Named
public class PesquisaEquipamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Equipamentos equipamentos;
	
	private EquipamentoFilter filter;
	
	private List<Equipamento> filtrados;

	public PesquisaEquipamentoBean() {
		filter = new EquipamentoFilter();
	}
	
	public void pesquisar(){
		filtrados = equipamentos.filtrados(filter);
	}

	public EquipamentoFilter getFilter() {
		return filter;
	}
	
	public void setFilter(EquipamentoFilter filter) {
		this.filter = filter;
	}
	
	public List<Equipamento> getFiltrados() {
		return filtrados;
	}
	
	public void setFiltrados(List<Equipamento> filtrados) {
		this.filtrados = filtrados;
	}
	
	public Categoria[] getCategorias(){
		return Categoria.values();
	}
}

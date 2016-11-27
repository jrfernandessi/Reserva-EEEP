package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.repository.filter.ProfessorFilter;

@ViewScoped
@Named
public class PesquisaProfessorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Professores professores;
	
	private ProfessorFilter filter;
	

	private List<Professor> filtrados;
	
	public PesquisaProfessorBean(){
		filter = new ProfessorFilter();
	}
	

	public void pesquisar(){
		filtrados = professores.filtrados(filter);
	}

	public List<Professor> getFiltrados() {
		return filtrados;
	}
	
	public void setFiltrados(List<Professor> filtrados) {
		this.filtrados = filtrados;
	}
	
	public ProfessorFilter getFilter() {
		return filter;
	}
	
	public void setFilter(ProfessorFilter filter) {
		this.filter = filter;
	}
}

package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.repository.filter.ProfessorFilter;
import com.analistajunior.reserva.util.jsf.FacesUtil;

@ViewScoped
@Named
public class PesquisaProfessorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Professores professores;

	private ProfessorFilter filter;

	private Professor professorSelecionado;

	private List<Professor> filtrados;

	public PesquisaProfessorBean() {
		filter = new ProfessorFilter();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback())
			filtrados = professores.listarProfessores();
	}

	public void pesquisar() {
		filtrados = professores.filtrados(filter);
	}

	public void excluir() {
		try {
			professores.remover(professorSelecionado);
			filtrados.remove(professorSelecionado);
			FacesUtil.addInfoMessage("Professor " + professorSelecionado.getNome() + " foi excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
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

	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

}

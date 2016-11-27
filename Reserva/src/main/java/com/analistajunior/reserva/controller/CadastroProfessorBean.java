package com.analistajunior.reserva.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.service.CadastroProfessorService;
import com.analistajunior.reserva.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProfessorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	CadastroProfessorService cadastroProfessorService;
	
	Professor professor;
	
	public CadastroProfessorBean(){
		limpar();
	}
	
	public void salvar(){
		try{
			professor = cadastroProfessorService.salvar(professor);
			limpar();
			FacesUtil.addInfoMessage("Professor salvo com sucesso!");
		}catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void inicializar() {
		if(professor==null){
			limpar();
		}
	}
	
	public void limpar(){
		professor = new Professor();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public boolean isEditando(){
		return this.professor.getId() != null;
	}
	
	
	
}

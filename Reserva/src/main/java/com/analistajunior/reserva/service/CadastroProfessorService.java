package com.analistajunior.reserva.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.util.jpa.Transactional;

public class CadastroProfessorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Professores professores;

	@Transactional
	public Professor salvar(Professor professor) throws NegocioException {
		
		Professor professorExistente = professores.porEmail(professor.getEmail());
		
		if (professorExistente != null && professorExistente.equals(professor)) {
			throw new NegocioException("Já existe um professor com o e-mail informado.");
		}
		return professores.guardar(professor);
	}
}

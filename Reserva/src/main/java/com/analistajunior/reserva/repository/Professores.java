package com.analistajunior.reserva.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.analistajunior.reserva.model.Professor;

public class Professores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Professor guardar(Professor professor) {
		return manager.merge(professor);
	}

	public Professor porEmail(String email) {
		try {
			return manager.createQuery("from Professor where lower(email)=:email", Professor.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	public Professor porId(Long id){
		return manager.find(Professor.class, id);
		
	}

}

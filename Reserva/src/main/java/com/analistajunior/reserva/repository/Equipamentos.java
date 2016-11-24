package com.analistajunior.reserva.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.analistajunior.reserva.model.Equipamento;

public class Equipamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Equipamento guardar(Equipamento equipamento) {
		return manager.merge(equipamento);
	}

	public Equipamento porTombo(String tombo) {
		try{
			return manager.createQuery("from Equipamento where tombo=:tombo", Equipamento.class)
				.setParameter("tombo", tombo).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Equipamento porId(Long id){
		return manager.find(Equipamento.class, id);
	}
}

package com.analistajunior.reserva.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.analistajunior.reserva.model.Reserva;

public class Reservas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager manager;
	
	public Reserva guardar(Reserva reserva){
		return manager.merge(reserva);
	}
	
	public Reserva porId(Long id) {
		return manager.find(Reserva.class, id);
	}

}

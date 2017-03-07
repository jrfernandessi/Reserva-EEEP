package com.analistajunior.reserva.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.Reservas;
import com.analistajunior.reserva.util.jpa.Transactional;

public class CadastroReservaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Reservas reservas;
	
	@Transactional
	public Reserva salvar(Reserva reserva){
		return reservas.guardar(reserva);
		
	}

}

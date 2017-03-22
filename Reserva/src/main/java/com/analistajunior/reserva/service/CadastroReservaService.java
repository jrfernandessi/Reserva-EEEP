package com.analistajunior.reserva.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.Reservas;
import com.analistajunior.reserva.util.jpa.Transactional;

public class CadastroReservaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Reservas reservas;

	@Transactional
	public Reserva salvar(Reserva reserva) throws NegocioException {
		Reserva reservaExistente = reservas.verificarReservaExistente(reserva);
		if (reservaExistente != null && !reservaExistente.equals(reserva)) {
			throw new NegocioException("Professor com reserva para esse dia e equipamento já realizada");
		} else {
			if (reservas.verificarReserva(reserva).size() > 0)

				for (Reserva aux : reservas.verificarReserva(reserva)) {
					if (aux.isAula1())
						reserva.setAula1(false);
					if (aux.isAula2())
						reserva.setAula2(false);
					if (aux.isAula3())
						reserva.setAula3(false);
					if (aux.isAula4())
						reserva.setAula4(false);
					if (aux.isAula5())
						reserva.setAula5(false);
					if (aux.isAula6())
						reserva.setAula6(false);
					if (aux.isAula7())
						reserva.setAula7(false);
					if (aux.isAula8())
						reserva.setAula8(false);
					if (aux.isAula9())
						reserva.setAula9(false);
				}
		}

		return reservas.guardar(reserva);
	}

	@Transactional
	public Reserva editar(Reserva reserva) throws NegocioException {
		Reserva reservaExistente = reservas.verificarReservaExistente(reserva);
		if (reservaExistente != null && !reservaExistente.equals(reserva)) {
			throw new NegocioException("Professor com reserva para esse dia e esse equipamento já existente");

		}
		return reservas.guardar(reserva);

	}

}

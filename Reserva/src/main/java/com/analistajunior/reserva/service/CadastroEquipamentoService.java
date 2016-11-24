package com.analistajunior.reserva.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.repository.Equipamentos;
import com.analistajunior.reserva.util.jpa.Transactional;

public class CadastroEquipamentoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Equipamentos equipamentos;
	
	@Transactional
	public Equipamento salvar(Equipamento equipamento) throws NegocioException{
		
		Equipamento equipamentoExistente = equipamentos.porTombo(equipamento.getTombo());
		
		if(equipamentoExistente !=null && !equipamentoExistente.equals(equipamento)){
			throw new NegocioException("Já existe um equipamento com o tombo informado.");
		}
		return equipamentos.guardar(equipamento);
	}
	

}

package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.Equipamentos;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.service.CadastroReservaService;
import com.analistajunior.reserva.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroReservaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroReservaService reservaService;

	@Inject
	private Professores professores;

	@Inject
	private Equipamentos equipamentoService;

	private List<Equipamento> equipamentos;

	@Produces
	private Reserva reserva;

	public CadastroReservaBean() {
		limpar();
	}

	public void limpar() {
		reserva = new Reserva();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			equipamentos = equipamentoService.listarEquipamentos();
			System.out.println(equipamentos.size());
		}
	}

	public List<Professor> completarProfessor(String nome) {
		return professores.porNome(nome);
	}

	public void salvar() {
		reservaService.salvar(reserva);
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}
}

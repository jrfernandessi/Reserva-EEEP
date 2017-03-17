package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.Equipamentos;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.repository.Reservas;
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

	@Inject
	private Reservas reservas;

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
			limpar();
		}
	}

	public List<Professor> completarProfessor(String nome) {
		return professores.porNome(nome);
	}

	public void salvar() {
		reservaService.salvar(reserva);
		limpar();
		FacesUtil.addInfoMessage("Reserva Realizada com sucesso.");
	}

	public void verificarReserva() {
//		if (reservas.verificarReserva(reserva).get(0) != null) {
//			reserva = reservas.verificarReserva(reserva).get(0);
//			System.out.println("estou aqui");
//		}
		FacesUtil.addInfoMessage((reservas.verificarReserva(reserva).size()+" registros"));
		// System.out.println("estou aqui");

		// System.out.println(reservas.verificarReserva(reserva.getEquipamento().getId(),
		// reserva.getDataReserva()).getProfessor().getNome());

		// System.out.println(reserva.getEquipamento());
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

package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	
	private static Date dataDeHoje = new Date();
	
	public Date getDataDeHoje(){
		return dataDeHoje;
	}

	private List<Equipamento> equipamentos;

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
		if (reservas.verificarReserva(reserva).size() > 0) {
			for (Reserva aux : reservas.verificarReserva(reserva)) {
				// Reserva aux = reservas.verificarReserva(reserva).get(0);
				if (!reserva.isAula1())
					reserva.setAula1(aux.isAula1());
				if (!reserva.isAula2())
					reserva.setAula2(aux.isAula2());
				if (!reserva.isAula3())
					reserva.setAula3(aux.isAula3());
				if (!reserva.isAula4())
					reserva.setAula4(aux.isAula4());
				if (!reserva.isAula5())
					reserva.setAula5(aux.isAula5());
				if (!reserva.isAula6())
					reserva.setAula6(aux.isAula6());
				if (!reserva.isAula7())
					reserva.setAula7(aux.isAula7());
				if (!reserva.isAula8())
					reserva.setAula8(aux.isAula8());
				if (!reserva.isAula9())
					reserva.setAula9(aux.isAula9());
				reserva.setEquipamento(aux.getEquipamento());
			}
			// System.out.println("estou aqui");
		}else{
			reserva.setAula1(false);
			reserva.setAula2(false);
			reserva.setAula3(false);
			reserva.setAula4(false);
			reserva.setAula5(false);
			reserva.setAula6(false);
			reserva.setAula7(false);
			reserva.setAula8(false);
			reserva.setAula9(false);
		}
		// System.out.println("====> "+reserva.getDataReserva());
		// FacesUtil.addInfoMessage((reservas.verificarReserva(reserva).size()+"
		// registros"));
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

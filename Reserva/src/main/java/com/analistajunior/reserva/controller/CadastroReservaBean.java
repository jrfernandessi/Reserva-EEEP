package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.Equipamentos;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.repository.Reservas;
import com.analistajunior.reserva.security.UsuarioLogado;
import com.analistajunior.reserva.security.UsuarioSistema;
import com.analistajunior.reserva.service.CadastroReservaService;
import com.analistajunior.reserva.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroReservaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroReservaService reservaService;

	@Inject
	private Equipamentos equipamentoService;

	@Inject
	private Reservas reservas;
	
	@Inject
	private Professores professores;

	@Inject
	@UsuarioLogado
	UsuarioSistema user;

	private boolean d1;
	private boolean d2;
	private boolean d3;
	private boolean d4;
	private boolean d5;
	private boolean d6;
	private boolean d7;
	private boolean d8;
	private boolean d9;

	private boolean editando;

	private Date dataDeHoje = new Date();

	private Date ultimoDia = dataDeHoje;

	@SuppressWarnings("deprecation")
	public Date getUltimoDia() {
		ultimoDia.setDate(dataDeHoje.getDate()+6);
		return ultimoDia;
	}

	public Date getDataDeHoje() {
		return dataDeHoje;
	}

	private List<Equipamento> equipamentos;

	private Reserva reserva;


	public void limpar() {
		reserva = new Reserva();
		editando = false;
	}

	public void inicializar() {
		equipamentos = equipamentoService.listarEquipamentos();
		if (reserva == null && FacesUtil.isNotPostback()) {
			limpar();
		} else {
			editando=true;
			if (reservas.verificarReserva(reserva).size() > 0) {
				for (Reserva aux : reservas.verificarReserva(reserva)) {

					if (aux.isAula1() && !reserva.isAula1())
						d1 = true;
					else if (reserva.isAula1())
						d1 = false;
					if (aux.isAula2() && !reserva.isAula2())
						d2 = true;
					else if (reserva.isAula2())
						d2 = false;
					if (aux.isAula3() && !reserva.isAula3())
						d3 = true;
					else if (reserva.isAula3())
						d3 = false;
					if (aux.isAula4() && !reserva.isAula4())
						d4 = true;
					else if (reserva.isAula4())
						d4 = false;
					if (aux.isAula5() && !reserva.isAula5())
						d5 = true;
					else if (reserva.isAula5())
						d5 = false;
					if (aux.isAula6() && !reserva.isAula6())
						d6 = true;
					else if (reserva.isAula6())
						d6 = false;
					if (aux.isAula7() && !reserva.isAula7())
						d7 = true;
					else if (reserva.isAula7())
						d7 = false;
					if (aux.isAula8() && !reserva.isAula8())
						d8 = true;
					else if (reserva.isAula8())
						d8 = false;
					if (aux.isAula9() && !reserva.isAula9())
						d9 = true;
					else if (reserva.isAula9())
						d9 = false;
				}
			}
		}
	}

	public void salvar() {
		reserva.setProfessor(user.getUsuario());
		try {
			if (reservas.verificarReservaExistente(reserva) != null) {
				reservaService.editar(reserva);
			} else {
				reservaService.salvar(reserva);
			}
			limpar();
			FacesUtil.addInfoMessage("Reserva Realizada com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void verificarReserva() {
		
		if (reservas.verificarReserva(reserva).size() > 0) {
			for (Reserva aux : reservas.verificarReserva(reserva)) {
				reserva.setAula1(false);
				reserva.setAula2(false);
				reserva.setAula3(false);
				reserva.setAula4(false);
				reserva.setAula5(false);
				reserva.setAula6(false);
				reserva.setAula7(false);
				reserva.setAula8(false);
				reserva.setAula9(false);
				if (!reserva.isAula1()) {
					reserva.setAula1(aux.isAula1());
					d1 = reserva.isAula1();
				}
				if (!reserva.isAula2()) {
					reserva.setAula2(aux.isAula2());
					d2 = reserva.isAula2();
				}
				if (!reserva.isAula3()) {
					reserva.setAula3(aux.isAula3());
					d3 = reserva.isAula3();
				}
				if (!reserva.isAula4()) {
					reserva.setAula4(aux.isAula4());
					d4 = reserva.isAula4();
				}
				if (!reserva.isAula5()) {
					reserva.setAula5(aux.isAula5());
					d5 = reserva.isAula5();
				}
				if (!reserva.isAula6()) {
					reserva.setAula6(aux.isAula6());
					d6 = reserva.isAula6();
				}
				if (!reserva.isAula7()) {
					reserva.setAula7(aux.isAula7());
					d7 = reserva.isAula7();
				}
				if (!reserva.isAula8()) {
					reserva.setAula8(aux.isAula8());
					d8 = reserva.isAula8();
				}
				if (!reserva.isAula9()) {
					reserva.setAula9(aux.isAula9());
					d9 = reserva.isAula9();
				}
				reserva.setEquipamento(aux.getEquipamento());
			}
		} else {
			reserva.setAula1(false);
			reserva.setAula2(false);
			reserva.setAula3(false);
			reserva.setAula4(false);
			reserva.setAula5(false);
			reserva.setAula6(false);
			reserva.setAula7(false);
			reserva.setAula8(false);
			reserva.setAula9(false);
			d1 = false;
			d2 = false;
			d3 = false;
			d4 = false;
			d5 = false;
			d6 = false;
			d7 = false;
			d8 = false;
			d9 = false;
		}
		
	}

	public void verificarData() {
		reserva.setEquipamento(null);
		if (reservas.verificarReserva(reserva).size() > 0) {
			for (Reserva aux : reservas.verificarReserva(reserva)) {
				// Reserva aux = reservas.verificarReserva(reserva).get(0);
				if (!reserva.isAula1()) {
					reserva.setAula1(aux.isAula1());
					d1 = reserva.isAula1();
				}
				if (!reserva.isAula2()) {
					reserva.setAula2(aux.isAula2());
					d2 = reserva.isAula2();
				}
				if (!reserva.isAula3()) {
					reserva.setAula3(aux.isAula3());
					d3 = reserva.isAula3();
				}
				if (!reserva.isAula4()) {
					reserva.setAula4(aux.isAula4());
					d4 = reserva.isAula4();
				}
				if (!reserva.isAula5()) {
					reserva.setAula5(aux.isAula5());
					d5 = reserva.isAula5();
				}
				if (!reserva.isAula6()) {
					reserva.setAula6(aux.isAula6());
					d6 = reserva.isAula6();
				}
				if (!reserva.isAula7()) {
					reserva.setAula7(aux.isAula7());
					d7 = reserva.isAula7();
				}
				if (!reserva.isAula8()) {
					reserva.setAula8(aux.isAula8());
					d8 = reserva.isAula8();
				}
				if (!reserva.isAula9()) {
					reserva.setAula9(aux.isAula9());
					d9 = reserva.isAula9();
				}
				reserva.setEquipamento(aux.getEquipamento());
			}
		} else {
			reserva.setAula1(false);
			reserva.setAula2(false);
			reserva.setAula3(false);
			reserva.setAula4(false);
			reserva.setAula5(false);
			reserva.setAula6(false);
			reserva.setAula7(false);
			reserva.setAula8(false);
			reserva.setAula9(false);
			d1 = false;
			d2 = false;
			d3 = false;
			d4 = false;
			d5 = false;
			d6 = false;
			d7 = false;
			d8 = false;
			d9 = false;
		}
	}
	
	public List<Professor> completarProfessor(String nome) {
		return professores.porNome(nome);
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

	public boolean isD1() {
		return d1;
	}

	public void setD1(boolean d1) {
		this.d1 = d1;
	}

	public boolean isD2() {
		return d2;
	}

	public void setD2(boolean d2) {
		this.d2 = d2;
	}

	public boolean isD3() {
		return d3;
	}

	public void setD3(boolean d3) {
		this.d3 = d3;
	}

	public boolean isD4() {
		return d4;
	}

	public void setD4(boolean d4) {
		this.d4 = d4;
	}

	public boolean isD5() {
		return d5;
	}

	public void setD5(boolean d5) {
		this.d5 = d5;
	}

	public boolean isD6() {
		return d6;
	}

	public void setD6(boolean d6) {
		this.d6 = d6;
	}

	public boolean isD7() {
		return d7;
	}

	public void setD7(boolean d7) {
		this.d7 = d7;
	}

	public boolean isD8() {
		return d8;
	}

	public void setD8(boolean d8) {
		this.d8 = d8;
	}

	public boolean isD9() {
		return d9;
	}

	public void setD9(boolean d9) {
		this.d9 = d9;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editado) {
		this.editando = editado;
	}
	


}

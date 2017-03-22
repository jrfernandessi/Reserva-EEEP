package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.Reservas;
import com.analistajunior.reserva.repository.filter.ReservaFilter;
import com.analistajunior.reserva.security.UsuarioLogado;
import com.analistajunior.reserva.security.UsuarioSistema;
import com.analistajunior.reserva.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaReservaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Reservas reservas;
	private ReservaFilter filter;
	private Reserva reservaSelecionada;
	

	@Inject
	@UsuarioLogado
	UsuarioSistema user;

	private List<Reserva> filtrados;

	public PesquisaReservaBean() {
		filter = new ReservaFilter();
	}

	public void inicializar() {
//		Professor professor = professores.porEmail(user.getUsuario().getEmail());
//		System.out.println("estou aqui");
		if (FacesUtil.isNotPostback()){
			filtrados = reservas.porProfessor(user.getUsuario());
		}
//		FacesUtil.addInfoMessage(filtrados.size() + "");
		
	}
	
	public void excluir(){
		try {
			reservas.remover(reservaSelecionada);
			filtrados.remove(reservaSelecionada);
			FacesUtil.addInfoMessage("Reserva do " + reservaSelecionada.getEquipamento().getCategoria().getDescricao() + " "+ reservaSelecionada.getEquipamento().getNumero()+" foi excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void pesquisar(){
		filtrados = reservas.filtrados(filter);
	}

	public ReservaFilter getFilter() {
		return filter;
	}

	public void setFilter(ReservaFilter filter) {
		this.filter = filter;
	}

	public Reserva getReservaSelecionada() {
		return reservaSelecionada;
	}

	public void setReservaSelecionada(Reserva reservaSelecionada) {
		this.reservaSelecionada = reservaSelecionada;
	}

	public List<Reserva> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Reserva> filtrados) {
		this.filtrados = filtrados;
	}

}

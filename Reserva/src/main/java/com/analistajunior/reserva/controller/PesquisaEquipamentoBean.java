package com.analistajunior.reserva.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Categoria;
import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.repository.Equipamentos;
import com.analistajunior.reserva.repository.filter.EquipamentoFilter;
import com.analistajunior.reserva.util.jsf.FacesUtil;

@ViewScoped
@Named
public class PesquisaEquipamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Equipamentos equipamentos;

	private EquipamentoFilter filter;

	private Equipamento equipamentoSelecionado;

	private List<Equipamento> filtrados;

	public PesquisaEquipamentoBean() {
		filter = new EquipamentoFilter();
	}

	public void incializar() {
		if (FacesUtil.isNotPostback())
			filtrados = equipamentos.listarEquipamentos();
	}

	public void pesquisar() {
		filtrados = equipamentos.filtrados(filter);
	}

	public void excluir() {
		try {
			equipamentos.remover(equipamentoSelecionado);
			filtrados.remove(equipamentoSelecionado);
			FacesUtil.addInfoMessage("Equipamento " + equipamentoSelecionado.getTombo() + " foi excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public EquipamentoFilter getFilter() {
		return filter;
	}

	public void setFilter(EquipamentoFilter filter) {
		this.filter = filter;
	}

	public List<Equipamento> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Equipamento> filtrados) {
		this.filtrados = filtrados;
	}

	public Equipamento getEquipamentoSelecionado() {
		return equipamentoSelecionado;
	}

	public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}

	public Categoria[] getCategorias() {
		return Categoria.values();
	}
}

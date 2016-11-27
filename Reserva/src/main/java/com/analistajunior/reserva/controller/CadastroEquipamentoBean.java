package com.analistajunior.reserva.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Categoria;
import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.service.CadastroEquipamentoService;
import com.analistajunior.reserva.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEquipamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEquipamentoService cadastroEquipamentoService;

	private Equipamento equipamento;

	public CadastroEquipamentoBean() {
		limpar();
	}

	public void salvar() {
		try {
			equipamento = cadastroEquipamentoService.salvar(equipamento);
			limpar();
			FacesUtil.addInfoMessage("Equipamento salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Categoria[] getCategorias() {
		return Categoria.values();
	}

	public void inicializar() {
		if (equipamento == null) {
			limpar();
		}
	}

	public void limpar() {
		equipamento = new Equipamento();
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public boolean isEditando(){
		return this.equipamento.getId() != null;
	}

}

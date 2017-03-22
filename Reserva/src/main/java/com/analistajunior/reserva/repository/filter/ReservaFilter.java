package com.analistajunior.reserva.repository.filter;

import java.util.Date;

import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.model.Professor;

public class ReservaFilter {
	private Date data;
	private Equipamento equipamento;
	private Professor professor;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}

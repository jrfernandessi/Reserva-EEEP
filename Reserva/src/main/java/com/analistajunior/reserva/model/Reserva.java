package com.analistajunior.reserva.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Professor professor;
	private Equipamento equipamento;
	private Date dataReserva;
	private boolean aula1;
	private boolean aula2;
	private boolean aula3;
	private boolean aula4;
	private boolean aula5;
	private boolean aula6;
	private boolean aula7;
	private boolean aula8;
	private boolean aula9;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "equipamento_id", nullable = false)
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@NotNull(message="deve ser informada")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_reserva", nullable = false)
	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public boolean isAula1() {
		return aula1;
	}

	public void setAula1(boolean aula1) {
		this.aula1 = aula1;
	}

	public boolean isAula2() {
		return aula2;
	}

	public void setAula2(boolean aula2) {
		this.aula2 = aula2;
	}

	public boolean isAula3() {
		return aula3;
	}

	public void setAula3(boolean aula3) {
		this.aula3 = aula3;
	}

	public boolean isAula4() {
		return aula4;
	}

	public void setAula4(boolean aula4) {
		this.aula4 = aula4;
	}

	public boolean isAula5() {
		return aula5;
	}

	public void setAula5(boolean aula5) {
		this.aula5 = aula5;
	}

	public boolean isAula6() {
		return aula6;
	}

	public void setAula6(boolean aula6) {
		this.aula6 = aula6;
	}

	public boolean isAula7() {
		return aula7;
	}

	public void setAula7(boolean aula7) {
		this.aula7 = aula7;
	}

	public boolean isAula8() {
		return aula8;
	}

	public void setAula8(boolean aula8) {
		this.aula8 = aula8;
	}

	public boolean isAula9() {
		return aula9;
	}

	public void setAula9(boolean aula9) {
		this.aula9 = aula9;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}

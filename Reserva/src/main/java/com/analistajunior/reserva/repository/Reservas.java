package com.analistajunior.reserva.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.filter.ReservaFilter;
import com.analistajunior.reserva.util.jpa.Transactional;

public class Reservas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;

	public Reserva guardar(Reserva reserva) {
		return manager.merge(reserva);
	}

	public Reserva porId(Long id) {
		return manager.find(Reserva.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> verificarReserva(Reserva reserva) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("equipamento", reserva.getEquipamento()));
		criteria.add(Restrictions.eq("dataReserva", reserva.getDataReserva()));

		return criteria.list();

	}

	public Reserva verificarReservaExistente(Reserva reserva) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("equipamento", reserva.getEquipamento()));
		criteria.add(Restrictions.eq("dataReserva", reserva.getDataReserva()));
		criteria.add(Restrictions.eq("professor", reserva.getProfessor()));

		return (Reserva) criteria.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Reserva> filtrados(ReservaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Reserva.class);
		if (filtro.getData() != null) {
			criteria.add(Restrictions.eq("dataReserva", filtro.getData()));
		}

		return criteria.addOrder(Order.desc("dataReserva")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> porProfessor(Professor professor) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("professor", professor));
		return criteria.list();
	}

	@Transactional
	public void remover(Reserva reserva) throws NegocioException {
		try {
			reserva = porId(reserva.getId());
			manager.remove(reserva);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Reserva não pode ser exluída.");
		}
	}

}

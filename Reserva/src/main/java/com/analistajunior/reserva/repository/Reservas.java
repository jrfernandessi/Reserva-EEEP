package com.analistajunior.reserva.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.analistajunior.reserva.model.Reserva;

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
		
//		try {
//			return manager
//					.createQuery("from Reserva where data_reserva=:data and equipamento_id=:equipamento", Reserva.class)
//					.setParameter("data", data).setParameter("equipamento", id).getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}

	}

}

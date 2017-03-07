package com.analistajunior.reserva.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.repository.filter.EquipamentoFilter;
import com.analistajunior.reserva.util.jpa.Transactional;

public class Equipamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Equipamento guardar(Equipamento equipamento) {
		return manager.merge(equipamento);
	}

	@SuppressWarnings("unchecked")
	public List<Equipamento> filtrados(EquipamentoFilter filtro) {
		Session session = manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Equipamento.class);
		if (StringUtils.isNotBlank(filtro.getTombo())) {
			criteria.add(Restrictions.eq("tombo", filtro.getTombo()));
		}
		if (filtro.getCategorias() != null && filtro.getCategorias().length > 0) {
			criteria.add(Restrictions.in("categoria", filtro.getCategorias()));
		}

		return criteria.addOrder(Order.asc("categoria")).list();
	}

	public Equipamento porTombo(String tombo) {
		try {
			return manager.createQuery("from Equipamento where tombo=:tombo", Equipamento.class)
					.setParameter("tombo", tombo).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public void remover(Equipamento equipamento) throws NegocioException {
		equipamento = porId(equipamento.getId());
		try {
			manager.remove(equipamento);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Equipamento não pode ser excluído.");
		}
	}

	public Equipamento porId(Long id) {
		return manager.find(Equipamento.class, id);
	}

	public List<Equipamento> listarEquipamentos() {
		try {
			return manager.createQuery("from Equipamento", Equipamento.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}

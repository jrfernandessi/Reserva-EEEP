package com.analistajunior.reserva.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.repository.filter.ProfessorFilter;

public class Professores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Professor guardar(Professor professor) {
		return manager.merge(professor);
	}

	public Professor porEmail(String email) {
		try {
			return manager.createQuery("from Professor where lower(email)=:email", Professor.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public Professor porId(Long id) {
		return manager.find(Professor.class, id);

	}

	@SuppressWarnings("unchecked")
	public List<Professor> filtrados(ProfessorFilter filter) {
		Session session = (Session) manager;

		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Professor.class);

		if (StringUtils.isNotBlank(filter.getEmail())) {
			criteria.add(Restrictions.eq("email", filter.getEmail()));
		}
		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}

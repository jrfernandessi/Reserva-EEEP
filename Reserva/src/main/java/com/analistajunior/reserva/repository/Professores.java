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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.analistajunior.reserva.exceptions.NegocioException;
import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.repository.filter.ProfessorFilter;
import com.analistajunior.reserva.util.jpa.Transactional;

public class Professores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Professor guardar(Professor professor) {
		return manager.merge(professor);
	}
	
	public List<Professor> porNome(String nome){
		return manager.createQuery("from Professor where upper(nome) like :nome", Professor.class)
				.setParameter("nome", nome.toUpperCase()+"%").getResultList();
	}

	public Professor porEmail(String email) {
		try {
			return manager.createQuery("from Professor where lower(email)=:email", Professor.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	
	@Transactional
	public void remover(Professor professor)throws NegocioException{
		try{
			professor = porId(professor.getId());
			manager.remove(professor);
			manager.flush();
		}catch (PersistenceException e) {
			throw new NegocioException("Professor não pode ser exluído.");
		}
	}

	public Professor porId(Long id) {
		return manager.find(Professor.class, id);

	}

	@SuppressWarnings("unchecked")
	public List<Professor> filtrados(ProfessorFilter filter) {
		Session session = manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Professor.class);

		if (StringUtils.isNotBlank(filter.getEmail())) {
			criteria.add(Restrictions.eq("email", filter.getEmail()));
		}
		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public List<Professor> listarProfessores(){
		try {
			return manager.createQuery("from Professor", Professor.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}

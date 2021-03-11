package com.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ecommerce.utils.JPAUtil;

public abstract class GenericDao <T> {
	
    private final EntityManager manager = JPAUtil.getEntityManager();
	
	private final Class<T> clazz;
	
	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T getById(Long id) {
		return manager.find(clazz, id);
	}

	public List<T> findAll() { 
		String JPQL = "SELECT a FROM " + clazz.getSimpleName();
		TypedQuery<T> q = manager.createQuery(JPQL , clazz);
		return q.getResultList();
	}

	public void salvar(T clazz) {
		manager.getTransaction().begin();
		manager.persist(clazz);
		manager.getTransaction().commit();
	}

	public void update(T clazz) {
		manager.getTransaction().begin();
		manager.merge(clazz);
		manager.getTransaction().commit();
	}

	public void excluir(T clazz) {
		manager.getTransaction().begin();
		manager.remove(clazz);
		manager.getTransaction().commit();
	}

	public void excluir(Long id) {
		excluir(getById(id));
	}
}

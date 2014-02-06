package com.vamsi.spring.springmvc.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaDao<K, T> implements Dao<K, T> {

	protected Class<T> entityClass;

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	public T create(T t) {
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);
		return t;
	}

	public T find(K id) {
		return (T) this.em.find(entityClass, id);
	}

	public T find(Class<T> type, Object id) {
		return (T) this.em.find(type, id);
	}

	public void delete(K id) {
		Object ref = this.em.getReference(entityClass, id);
		this.em.remove(ref);
	}

	public T update(T t) {
		return (T) this.em.merge(t);
	}

	@Override
	public Collection<T> findByNamedQuery(String queryName) {

		TypedQuery<T> query = this.em.createNamedQuery(queryName, entityClass);

		return query.getResultList();
	}

	public Collection<T> findByNamedQuery(String namedQueryName,
			Map<String, Object> parameters) {
		return findByNamedQuery(namedQueryName, parameters, 0);
	}

	public Collection<T> findByNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName, entityClass)
				.setMaxResults(resultLimit).getResultList();
	}

	public Collection<T> findByNativeQuery(String sql, Class<T> type) {
		return this.em.createNativeQuery(sql, type).getResultList();
	}

	public Collection<T> findByNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int resultLimit) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		if (resultLimit > 0)
			query.setMaxResults(resultLimit);
		for (Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

}

package com.vamsi.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vamsi.spring.jpa.entities.Employee;

@Stateless
public class EmployeeSessionBean {

	@PersistenceContext
	EntityManager em;

	public Employee createEmployee(Employee e) {
		em.persist(e);
		return e;
	}

}

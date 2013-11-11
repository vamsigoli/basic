package com.vamsi.bean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

import com.vamsi.spring.jpa.entities.Employee;

public class EmployeeBean {
	
	@PersistenceContext(unitName="entities")
	private EntityManager em;
	
	 @Resource
	    TransactionSynchronizationRegistry tsr;
	
	@Transactional
	public void saveEmployee(Employee e) {
		
		if (tsr.getTransactionStatus() == Status.STATUS_ACTIVE) {
			System.out.println("transaction active. new transaction started");
		}
		else {
			System.out.println("transaction not active");
		}
		
		//empBean.createEmployee(e);
		em.persist(e);
	}

}

package com.vamsi.spring.jpa.entities;

import java.util.Calendar;
import java.util.List;

import org.slf4j.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTester {

	public static final Logger logger = LoggerFactory
			.getLogger(EmployeeTester.class);

	public EntityManagerFactory getEntityManagerFactory() {
		return emFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory emFactory) {
		this.emFactory = emFactory;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManagerFactory emFactory;
	private EntityManager em;
	private static EntityTransaction mTrx;

	public static EntityTransaction getTrx() {
		return mTrx;
	}

	public static void setTrx(EntityTransaction mTrx) {
		EmployeeTester.mTrx = mTrx;
	}

	@Before
	public void setUp() throws Exception {
		try {
			logger.info("Building JPA EntityManager for unit tests");
			setEntityManagerFactory(Persistence.createEntityManagerFactory("entities"));
			setEntityManager(getEntityManagerFactory().createEntityManager());
			
			logger.info("Start a new transaction for the test");
	        setTrx(getEntityManager().getTransaction());
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Exception during JPA EntityManager instanciation.");
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("Shuting down Hibernate JPA layer.");
		if (em != null) {
			em.close();
		}
		if (emFactory != null) {
			emFactory.close();
		}
	}

	@Test
	public void createEmployee() {

		Employee employee = new Employee();
		employee.setLastName("Chintal");
		employee.setJobTitle("Manager");
		employee.setSalary(99000);
		employee.setDateOfHire(Calendar.getInstance().getTime());
		employee.setEmailAddress("a@b.com");

		CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Employee> c = qb.createQuery(Employee.class);
		Root<Employee> employeeRoot = c.from(Employee.class);
		c.select(employeeRoot);

		TypedQuery<Employee> query = getEntityManager().createQuery(c);
		

		int beforeCount = query.getResultList().size();

		logger.info("Number of students in the DB:" + beforeCount);

		getTrx().begin();
		getEntityManager().persist(employee);
		getTrx().commit();

		query = getEntityManager().createQuery(c);
		
		List<Employee> employeeList = query.getResultList(); 
		
		for (Employee e : employeeList) {
			logger.info("employee id " + e.getId() );
		}

		int afterCount = employeeList.size();

		logger.info("Number of students in the DB:" + afterCount);
		Assert.assertEquals(afterCount, beforeCount + 1);

	}

}

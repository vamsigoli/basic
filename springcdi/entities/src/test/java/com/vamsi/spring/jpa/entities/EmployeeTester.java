package com.vamsi.spring.jpa.entities;

import java.util.Calendar;
import java.util.List;

import org.slf4j.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
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

	private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("entities");
	private EntityManager em;
	private  EntityTransaction mTrx;

	public  EntityTransaction getTrx() {
		return mTrx;
	}

	public void setTrx(EntityTransaction mTrx) {
		this.mTrx = mTrx;
	}

	@Before
	public void setUp() throws Exception {
		try {
			
			logger.info("Building JPA EntityManager for unit tests. this is application managed em");
			logger.info("what matters is the creation of the em.  not the emf");
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
		logger.info("Shuting down Hibernate JPA layer. closing the em is the responsibility of the app");
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
		
		ParkingSpace park1 = new ParkingSpace();
		park1.setLocation("Near FirstGate");
		park1.setLot(1);
		
		employee.setParking(park1);
		park1.setEmp(employee);
		//this assignment need to be explicitly managed

		CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Employee> c = qb.createQuery(Employee.class);
		Root<Employee> employeeRoot = c.from(Employee.class);
		c.select(employeeRoot);

		TypedQuery<Employee> query = getEntityManager().createQuery(c);
		

		int beforeCount = query.getResultList().size();

		logger.info("Number of employees in the DB:" + beforeCount);

		getTrx().begin();
		 getEntityManager().persist(employee);
		 //getId on employee will return correct value as it is set by the em.
		 //but if we use merge instead of persist, getId will return null
		getTrx().commit();
		
		logger.info("employee id set on parking object : " + park1.getEmp().getId());

		query = getEntityManager().createQuery(c);
		
		List<Employee> employeeList = query.getResultList(); 
		
		for (Employee e : employeeList) {
			logger.info("employee id " + e.getId() );
			logger.info("employee 's parking lot " + e.getParking() );
		}

		int afterCount = employeeList.size();

		logger.info("Number of students in the DB:" + afterCount);
		Assert.assertEquals(afterCount, beforeCount + 1);

	}
	
	//@Test(expected=PersistenceException.class)
	public void createEmployeeWithParking() {

		Employee employee = new Employee();
		employee.setLastName("Chintal");
		employee.setJobTitle("Manager");
		employee.setSalary(99000);
		employee.setDateOfHire(Calendar.getInstance().getTime());
		employee.setEmailAddress("a@b.com");
		
		Employee employee1 = new Employee();
		employee1.setLastName("Chintal1");
		employee1.setJobTitle("Manager1");
		employee1.setSalary(99000);
		employee1.setDateOfHire(Calendar.getInstance().getTime());
		employee1.setEmailAddress("a@b1.com");
		
		ParkingSpace park1 = new ParkingSpace();
		park1.setLocation("Near FirstGate");
		park1.setLot(1);

		CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Employee> c = qb.createQuery(Employee.class);
		Root<Employee> employeeRoot = c.from(Employee.class);
		c.select(employeeRoot);

		TypedQuery<Employee> query = getEntityManager().createQuery(c);
		

		int beforeCount = query.getResultList().size();

		logger.info("Number of students in the DB:" + beforeCount);

		getTrx().begin();
		
	    getEntityManager().persist(park1);	
	    
	    employee.setParking(park1);
	    employee1.setParking(park1);
		
		
		 getEntityManager().persist(employee);
		 getEntityManager().persist(employee1);
		 //getId on employee will return correct value as it is set by the em.
		 //but if we use merge instead of persist, getId will return null
		getTrx().commit();

		query = getEntityManager().createQuery(c);
		
		List<Employee> employeeList = query.getResultList(); 
		
		for (Employee e : employeeList) {
			logger.info("employee id " + e.getId() );
		}

		int afterCount = employeeList.size();

		logger.info("Number of employees in the DB:" + afterCount);
		Assert.assertEquals(afterCount, beforeCount + 2);

	}


}

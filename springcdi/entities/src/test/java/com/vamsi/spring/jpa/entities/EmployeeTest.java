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

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vamsi.spring.jpa.types.ParkingLocation;

public class EmployeeTest {

	public static final Logger logger = LoggerFactory
			.getLogger(EmployeeTest.class);

	public static EntityManagerFactory getEntityManagerFactory() {
		return emFactory;
	}

	public static void setEntityManagerFactory(EntityManagerFactory emFactoryParam) {
		emFactory = emFactoryParam;
	}

	public static EntityManager getEntityManager() {
		return em;
	}

	public static void setEntityManager(EntityManager emparam) {
		em = emparam;
	}

	private static EntityManagerFactory emFactory ;
	private static EntityManager em;

	
	
@BeforeClass	
public static void setupTestClass() {	
			logger.info("Building JPA EntityManager for unit tests. this is application managed em");
			logger.info("what matters is the creation of the em.  not the emf");
	setEntityManagerFactory( Persistence.createEntityManagerFactory("entities-test"));
			setEntityManager(getEntityManagerFactory().createEntityManager());
			
		
	}

	
	
	

	@AfterClass
	public static void tearDownTestClass() throws Exception {
		logger.info("Shuting down Hibernate JPA layer. closing the em is the responsibility of the app any active txns will be rolled back");
		
		
		if (em != null) {
			em.close();
		}
		if (emFactory != null) {
			emFactory.close();
		}
	}
	
	
	@Test
	public void createDepartment() {
		EntityTransaction txn = getEntityManager().getTransaction();
		EntityManager em = getEntityManager();
		
		logger.info("is txn active? " + txn.isActive());
		
		/*CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Department> c = qb.createQuery(Department.class);
		Root<Department> deptRoot= c.from(Department.class);
		c.select(deptRoot);

		TypedQuery<Department> query = getEntityManager().createQuery(c);
		

		int beforeCount = query.getResultList().size();

		logger.info("in createDepartment test Number of departments in the DB:" + beforeCount);*/
		
		Department dept = new Department();
		dept.setName("Economics");
		
		Project anewProj = new Project();
		anewProj.setName("Commercial");
		
		
		Employee employee = new Employee();
		
		Address address = new Address();
		
		address.setStreet("Ashford Dunwoody Rd");
		address.setCity("Atlanta");
		address.setState("GA");
		address.setZip("30338");
		
		employee.setLastName("Goli");
		employee.setJobTitle("Manager");
		employee.setSalary(99000);
		employee.setDateOfHire(Calendar.getInstance().getTime());
		employee.setEmailAddress("a@b.com");
		
		ParkingSpace park1 = new ParkingSpace();
		park1.setLocation(ParkingLocation.EAST_END);
		park1.setLot(1);
		
		employee.setParking(park1);
		employee.setAddress(address);
		park1.setEmp(employee);
		dept.addEmployee(employee);
		employee.addProject(anewProj);
		
		
		txn.begin();
		em.persist(dept);
		em.flush();
		logger.info("is txn active? " + txn.isActive());
		txn.commit();
		
		//logger.info("department created as " + dept.getId());
		//int after = query.getResultList().size();

		//logger.info("in createDepartment test Number of departments in the DB:" + after);
		
		
		
		
	}
	
	@Test
	public void createDeptAndEmployee() {
		
		EntityTransaction txn = getEntityManager().getTransaction();
		
		logger.info("is txn active? " + txn.isActive());
		
		CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Employee> c = qb.createQuery(Employee.class);
		Root<Employee> employeeRoot = c.from(Employee.class);
		c.select(employeeRoot);

		TypedQuery<Employee> query = getEntityManager().createQuery(c);
		

		logger.info("is txn active? " + txn.isActive());
		int beforeCount = query.getResultList().size();

		logger.info("in createEmployee test Number of employees in the DB:" + beforeCount);

		Department dept = new Department();
		dept.setName("Computer Science");
		
		
		Employee employee = new Employee();
		employee.setLastName("Chintal");
		employee.setJobTitle("Manager");
		employee.setSalary(99000);
		employee.setDateOfHire(Calendar.getInstance().getTime());
		employee.setEmailAddress("a@b.com");
		
		ParkingSpace park1 = new ParkingSpace();
		park1.setLocation(ParkingLocation.EAST_END);
		park1.setLot(1);
		
		employee.setParking(park1);
		park1.setEmp(employee);
		//this assignment need to be explicitly managed
		
		dept.addEmployee(employee);
		
		getEntityManager().getTransaction().begin();
		
		getEntityManager().persist(dept);
		
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
		logger.info("is txn active? " + txn.isActive());
		
		query = getEntityManager().createQuery(c);
		
		List<Employee> employeeList = query.getResultList(); 
		
		for (Employee e : employeeList) {
			logger.info("employee id " + e.getId() );
			logger.info("employee 's parking lot " + e.getParking() );
			logger.info("employee 's department " + e.getDepartment() );
			//this returns true as em is still active.
			logger.info("is it managed: " + getEntityManager().contains(e));
			logger.info("is employee instance managed? " + getEntityManager().contains(employee));
			logger.info("is parking lot instance managed? " + getEntityManager().contains(employee.getParking()));
		}
		
		
		logger.info("is txn active? " + txn.isActive());
		
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
		park1.setLocation(ParkingLocation.EAST_END);
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

		logger.info("in createEmployee test Number of employees in the DB:" + beforeCount);

		getEntityManager().getTransaction().begin();
		 getEntityManager().persist(employee);
		 //getId on employee will return correct value as it is set by the em.
		 //but if we use merge instead of persist, getId will return null
		 //because merge returns a entity of same type that is managed by em and it contains the id.
		 //persist acts on the same instance but merge copies the contents to a managed object and returns it
		 
		getEntityManager().getTransaction().commit();
		
		logger.info("employee id set on parking object : " + park1.getEmp().getId());

		query = getEntityManager().createQuery(c);
		
		List<Employee> employeeList = query.getResultList(); 
		
		for (Employee e : employeeList) {
			logger.info("employee id " + e.getId() );
			logger.info("employee 's parking lot " + e.getParking() );
			//this returns true as em is still active.
			logger.info("is it managed: " + getEntityManager().contains(e));
			logger.info("is employee instance managed? " + getEntityManager().contains(employee));
			logger.info("is parking lot instance managed? " + getEntityManager().contains(employee.getParking()));
			getEntityManager().detach(e);
			logger.info("is it managed now: " + getEntityManager().contains(e));
			
			logger.info("is employee instance managed? " + getEntityManager().contains(employee));
			logger.info("is parking lot instance managed now ? " + getEntityManager().contains(employee.getParking()));
			
		}

		int afterCount = employeeList.size();

		logger.info("Number of employees in the DB:" + afterCount);
		Assert.assertEquals(afterCount, beforeCount + 1);

	}
	
	@Test
	public void createEmployeeWithParking() {
		
		EntityTransaction txn = getEntityManager().getTransaction();

		try {
		txn.begin();
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
		park1.setLocation(ParkingLocation.EAST_END);
		park1.setLot(1);

		CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Employee> c = qb.createQuery(Employee.class);
		Root<Employee> employeeRoot = c.from(Employee.class);
		c.select(employeeRoot);

		TypedQuery<Employee> query = getEntityManager().createQuery(c);
		

		int beforeCount = query.getResultList().size();

		logger.info("Number of students in the DB:" + beforeCount);

		
	    getEntityManager().persist(park1);	
	    
	    employee.setParking(park1);
	    employee1.setParking(park1);
		
		
		 getEntityManager().persist(employee);
		 
		 logger.info("the id returned for employee is " + employee.getId()); 
		 
		 
		 getEntityManager().persist(employee1);
		 //getId on employee will return correct value as it is set by the em.
		 //but if we use merge instead of persist, getId will return null
		 logger.info("the id returned for employee is " + employee1.getId()); 
		txn.commit();

		query = getEntityManager().createQuery(c);
		
		List<Employee> employeeList = query.getResultList(); 
		
		for (Employee e : employeeList) {
			logger.info("employee id " + e.getId() );
		}

		int afterCount = employeeList.size();

		logger.info("Number of employees in the DB:" + afterCount);
		Assert.assertEquals(afterCount, beforeCount + 2);
		
		}
		catch (Exception e) {
			txn.rollback();
		}

	}

   @Test
   public void testNamedQueryOnParking() {
	   
	   EntityTransaction tx = em.getTransaction();
	   
	   tx.begin();
	   
	   ParkingSpace p = new ParkingSpace();
	   p.setLocation(ParkingLocation.EAST_END);
	   p.setLot(1);
	   em.persist(p);
	   tx.commit();
	   
	 ParkingLocation pl = ParkingLocation.EAST_END;
	   
	TypedQuery<ParkingSpace> query = em.createNamedQuery(
										"ParkingSpace.findAllavailableInLocation", ParkingSpace.class)
										.setParameter("location", pl);
	
	List<ParkingSpace> psList = query.getResultList();	
	
	logger.info("number of parking spaces in " + pl + "is " + psList.size());
   }
}

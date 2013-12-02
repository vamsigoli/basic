package com.vamsi.spring.cdi;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.vamsi.spring.jpa.entities.Employee;

public class EmployeeService {
	protected EntityManager em;

	public EmployeeService(EntityManager em) {
		this.em = em;
	}

	public Employee createEmployee(int id, String name, long salary) {
		Employee emp = new Employee();
		//emp.setId(id);
		emp.setLastName(name);
		emp.setSalary(salary);
		em.persist(emp);
		return emp;
	}

	public void removeEmployee(int id) {
		Employee emp = findEmployee(id);
		if (emp != null) {
			em.remove(emp);
		}
	}

	public Employee raiseEmployeeSalary(int id, long raise) {
		Employee emp = em.find(Employee.class, id);
		if (emp != null) {
			emp.setSalary(emp.getSalary() + raise);
		}
		return emp;
	}

	public Employee findEmployee(int id) {
		return em.find(Employee.class, id);
	}

	public List<Employee> findAllEmployees() {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e",
				Employee.class);
		return query.getResultList();
	}
}
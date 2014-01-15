package com.vamsi.spring.jpa.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vamsi.spring.jpa.types.EmployeeType;

@Entity
@Access(AccessType.FIELD)
//the other accesstype is AccessType.PROPERTY to be annotated against a method

public class Employee {
	@SequenceGenerator(name = "EMPLOYEE_ID_GENERATOR", 
			sequenceName = "EMP_SEQ" ,initialValue=10 ,allocationSize=1)
	@Id
	@GeneratedValue(generator = "EMPLOYEE_ID_GENERATOR")
	private Integer id;
	
	
	@OneToOne(optional=true, cascade=CascadeType.ALL)
	//by keeping optional as false, the Employee table that gets created will have PSPACE_ID as not null
	//by keeping true, we can create employee without parking space
	//by cascade option as ALL, even detach will evict
	
	@JoinColumn(name="PSPACE_ID"  ,unique=true)
	private ParkingSpace parking;
	//by keeping unique as true we are adding a unique constraint on PSPACE_ID in EMPLOYEE table.
	
	@ManyToOne
	@JoinColumn(name="DEPT_ID")
	//its not unique. there will be many employees for a department. its a bidirectional relationship
	//department is non owning side and will have @mappedBy("department") the name of attribute 
	//single object has ManyToOne annotation on it and in department, collection has OneToMany annotation.
	private Department department;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	private Collection<Project> projects = new ArrayList<Project>();
	//here we are defaulting to the JPA provided join table, EMPLOYEE_PROJECT. if we want to
	//specify our own, we need to give @JoinTable. The column in the EMPLOYEE_PROJECT for 
	//projects attribute will be projects_id and as in Project entity employees is used
	//to indicate the list of employees, that column would be employees_id
	//to have our own behaviour, we need to have @JoinTable with @JoinColumn attributes
	 
	private String lastName;
	private String emailAddress;
	private float salary;
	// all fields are part of the entity. a field without annotation has default annotation as Basic
	//if we donot want any field not to be persisted, we should mark it as @Transient
	//difference between transient keyword and @Transient is that annotation is related to persistance only
	//if the field is not marked as transient keyword, it is transmitted across serialization.
	
	
	@Enumerated(EnumType.ORDINAL)
	private EmployeeType employeeType;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfHire;
	
	private String jobTitle;
	
	
	@Embedded
	private Address address;
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public ParkingSpace getParking() {
		return parking;
	}

	public void setParking(ParkingSpace parking) {
		this.parking = parking;
	}

	

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public Integer getId() {
		return id;
	}
	
	//see the access restriction. setId is marked private
@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public Date getDateOfHire() {
		return dateOfHire;
	}

	public void setDateOfHire(Date dateOfHire) {
		this.dateOfHire = dateOfHire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getDateOfHire() == null) ? 0 : getDateOfHire().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getLastName() == null) ? 0 : getLastName().hashCode());
		return result;
	}

	@Override
	//refer to article that says not to override equals and hashcode methods
	//also it is suggested to use auto generated keys instead of a natural key for a primary key. 
	//use the natural keys as surrogate keys
	//the below code assumes all elements to be part of the equals while it should only be id
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (getDateOfHire() == null) {
			if (other.getDateOfHire() != null)
				return false;
		} else if (!getDateOfHire().equals(other.getDateOfHire()))
			return false;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (getLastName() == null) {
			if (other.getLastName() != null)
				return false;
		} else if (!getLastName().equals(other.getLastName()))
			return false;
		return true;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
	 this.department = department;	
	}

	public Collection<Project> getProjects() {
		return projects;
	}

	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}
	
	   public void addProject(Project project) {
	        if (!getProjects().contains(project)) {
	            getProjects().add(project);
	        }
	        if (!project.getEmployees().contains(this)) {
	            project.getEmployees().add(this);
	        }
	    }

}

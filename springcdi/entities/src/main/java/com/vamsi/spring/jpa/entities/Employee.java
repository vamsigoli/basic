package com.vamsi.spring.jpa.entities;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	
	@OneToOne(optional=true, cascade=CascadeType.PERSIST )
	//by keeping optional as false, the Employee table that gets created will have PSPACE_ID as not null
	//by keeping true, we can create employee without parking space
	
	@JoinColumn(name="PSPACE_ID"  ,unique=true)
	private ParkingSpace parking;
	//by keeping unique as true we are adding a unique constraint on PSPACE_ID in EMPLOYEE table.
	
	
	private String lastName;
	private String emailAddress;
	private float salary;
	// all fields are part of the entity. a field without annotation has default annotation as Basic
	//if we donot want any field not to be persisted, we should mark it as @Transient
	//difference between transient keyword and @Transient is that annotation is related to persistance only
	//if the field is not marked as transient keyword, it is transmitted across serialization.
	
	
	@Enumerated(EnumType.ORDINAL)
	private EmployeeType employeeType;
	
	public ParkingSpace getParking() {
		return parking;
	}

	public void setParking(ParkingSpace parking) {
		this.parking = parking;
	}

	
	@Temporal(TemporalType.DATE)
	private Date dateOfHire;
	

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

	private String jobTitle;

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

}

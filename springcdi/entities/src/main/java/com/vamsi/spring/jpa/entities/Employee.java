package com.vamsi.spring.jpa.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {
	@SequenceGenerator(name = "EMPLOYEE_ID_GENERATOR", 
			sequenceName = "EMP_SEQ" ,initialValue=10 ,allocationSize=1)
	@Id
	@GeneratedValue(generator = "EMPLOYEE_ID_GENERATOR")
	private Integer id;
	
	
	@OneToOne(optional=false, cascade=CascadeType.PERSIST )
	//by keeping optional as false, the Employee table that gets created wil have PSPACE_ID as not null
	
	@JoinColumn(name="PSPACE_ID"  ,unique=true)
	//by keeping unique as true we are adding a unique constraint on PSPACE_ID in EMPLOYEE table.
	 
	private ParkingSpace parking;
	
	public ParkingSpace getParking() {
		return parking;
	}

	public void setParking(ParkingSpace parking) {
		this.parking = parking;
	}

	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfHire;
	
	private String emailAddress;
	private float salary;

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

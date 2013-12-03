package com.vamsi.spring.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PARKING_SPACE")
public class ParkingSpace {
	@SequenceGenerator(name = "PARKING_ID_GENERATOR", 
			sequenceName = "PARKING_SEQ" ,initialValue=10 ,allocationSize=1)
	@Id
	@GeneratedValue(generator = "PARKING_ID_GENERATOR")
    private int id;
	
    private int lot;
    
    private String location;
    
    @OneToOne(mappedBy="parking")
    private Employee emp;
    //even though we have set the relationship, the emp variable need to be explicitly set by code

    public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public int getId() {
        return id;
    }
	
	@SuppressWarnings("unused")
    
    private void setId(int id) {
        this.id = id;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String deptName) {
        this.location = deptName;
    }

    public String toString() {
        return "ParkingSpace id: " + getId() + " lot: " + getLot() +
               ", location: " + getLocation();
    }

}

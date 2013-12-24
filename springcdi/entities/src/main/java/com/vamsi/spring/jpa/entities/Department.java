package com.vamsi.spring.jpa.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @OneToMany(mappedBy="department")
    private Collection<Employee> employees;

    public Department() {
    	//see the implementation defined as ArrayList.
    	//eclipse link initialized at declaration itself
        employees = new ArrayList<Employee>();
    }
    
    public int getId() {
        return id;
    }
    
    private void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public void addEmployee(Employee employee) {
    	
    	//see the logic carefully. this may be a classic interview question
    	//which ever object has the collection manages the collection
    	//this is wht i thought.. but eclipselink examples show otherwise
    	//they have setEmployeeList as well.
    	
        if (!getEmployees().contains(employee)) {
            getEmployees().add(employee);
            if (employee.getDepartment() != null) {
                employee.getDepartment().getEmployees().remove(employee);
            }
            employee.setDepartment(this);
        }
    }
    
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public String toString() {
        return "Department id: " + getId() + 
               ", name: " + getName();
    }
}

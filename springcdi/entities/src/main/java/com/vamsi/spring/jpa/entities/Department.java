package com.vamsi.spring.jpa.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity

public class Department {
	@SequenceGenerator(name = "DEPT_ID_GENERATOR", 
	sequenceName = "DEPT_SEQ" ,initialValue=10 ,allocationSize=1)
@Id
@GeneratedValue(generator = "DEPT_ID_GENERATOR")
    private int id;
    private String name;
    
    @OneToMany(mappedBy="department" , cascade=CascadeType.ALL)
    private Collection<Employee> employees;

    public Department() {
    	//the implementation defined as ArrayList.
    	//eclipse link examples initialized the variable at declaration itself
        employees = new ArrayList<Employee>();
    }
    
    public int getId() {
        return id;
    }
    
    @SuppressWarnings("unused")
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

package com.vamsi.spring.jpa.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Project {
	@SequenceGenerator(name = "PROJ_ID_GENERATOR", 
	sequenceName = "PROJ_SEQ" ,initialValue=10 ,allocationSize=1)
@Id
@GeneratedValue(generator = "PROJ_ID_GENERATOR")
    protected int id;
    protected String name;
    @ManyToMany(mappedBy="projects", cascade=CascadeType.ALL)
    private Collection<Employee> employees;

    public Project() {
        employees = new ArrayList<Employee>();
    }

    public int getId() {
        return id;
    }
    
    public void setId(int projectNo) {
        this.id = projectNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String projectName) {
        this.name = projectName;
    }
    
    public Collection<Employee> getEmployees() {
        return employees;
    }
    
    public void addEmployee(Employee employee) {
        if (!getEmployees().contains(employee)) {
            getEmployees().add(employee);
        }
        if (!employee.getProjects().contains(this)) {
            employee.getProjects().add(this);
        }
    }
    
    public String toString() {
        return "Project id: " + getId() + ", name: " + getName() +
               " with " + getEmployees().size() + " employees";
    }
}
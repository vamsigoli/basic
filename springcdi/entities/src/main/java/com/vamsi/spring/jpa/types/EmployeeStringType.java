package com.vamsi.spring.jpa.types;

public enum EmployeeStringType {
	
	FULL_TIME("FTA") ,PART_TIME("PTA");
	
	private final String empType;
	
	private EmployeeStringType(String type) {
		this.empType = type;
	}

	
	

}

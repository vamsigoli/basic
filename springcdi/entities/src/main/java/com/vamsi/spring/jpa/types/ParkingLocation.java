package com.vamsi.spring.jpa.types;

public enum ParkingLocation {
	
	EAST_END("East End"),WEST_END("West End"), SOUTH_END("South End"), NORTH_END("North End");
	
	private String location;
	
	private ParkingLocation(String location) {
		this.location= location;
		
	}

	public String getLocation() {
		return location;
	}


}

package com.vamsi.core;

@FunctionalInterface
//need to declare annotation as functional interface. 
//it should have only one non default method
public interface Calculate {
	
	public int calculate(int a, int b);
	//now the implementation of this method is done in older versions of java
	//using a anonymous inner class. but with lambda expressions, we can pass
	//functions along with data as well. check the FunctionalImplementation class
	//for details

}

package com.vamsi.core;

class Tested {
	
	int a;
	
	Tested() {
		a = 10;
	}
	
	void setA(int a) {
		this.a = a;
	}
	
	int getA() {
		return a;
	}
	
}

public class PassByValueMethod {
	
	public static void methodA(Tested a) {
		
		Tested b = new Tested();
		a = b;
		System.out.println("a value inside is " + a.getA());
		
		
	}
	
	public static void main(String ... abc) {
		Tested a = new Tested();
		a.setA(20);
		
		System.out.println("a value is " + a.getA());
		
		methodA(a);
		
		System.out.println("a value is " + a.getA());
		
	}

}

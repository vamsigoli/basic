package com.vamsi.spring.cdi;

public class IntegerBox implements Box<Integer> {
	
	private Integer a;
	
	public IntegerBox() {
		a = -1;
	}
	
	public Integer getA() {
		return a;
	}
	
	public void setA(Integer aparam) {
		a = aparam;
	}

}

package com.vamsi.spring.cdi;


public class SimpleObjectImpl implements SimpleObject {
	
	private Box<Integer> box;
	
	public int getValue() {
		return box.getA();
	}
	
	public void setValue(Box<Integer> a) {
		this.box = a;
	}

}

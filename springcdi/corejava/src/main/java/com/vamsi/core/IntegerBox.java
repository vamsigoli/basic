package com.vamsi.core;

import java.util.ArrayList;
import java.util.List;

public class IntegerBox implements Box<Integer>{
	
	private List<Integer> intBox = new ArrayList<Integer>();
	
	@Override
	public void add(Integer a) {
		intBox.add(a);
	}

	@Override
	public Integer get(int index) {
		return intBox.get(index);
	}

}

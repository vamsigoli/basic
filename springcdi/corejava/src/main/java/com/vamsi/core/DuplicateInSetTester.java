package com.vamsi.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DuplicateInSetTester {
	
	public static void main(String ... input) throws Exception  {
		
		
		try {
		Set<String> a = new HashSet<>();
		
		String astr = "vamsi";
		
		a.add(astr);
		a.add(astr);
		a.add(null);
		
		
		
		for (String single : a) {
			System.out.println(single);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//the previous a is scoped only to the try block
		Collection<String> a = new ArrayList<String>();
		
		System.out.println("the class of a " + a.getClass());
		
		
	}

}

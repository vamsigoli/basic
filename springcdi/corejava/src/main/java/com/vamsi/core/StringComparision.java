package com.vamsi.core;

public class StringComparision {
	
	
	public static void main(String ... input) { 
		
		String a = "abc";
		
		String b = "abc";
		
		if (a == b) System.out.println("without new works");
		
		String c = new String("cde");
		
		String d = new String("cde");
		
		Object cobj = (Object)c;
		
		if (c == d) System.out.println("with new works" ); 
		
		System.out.println("ref of string is " + (Object)c);
		System.out.println("ref of string is " + (Object)d);
		
		System.out.println("ref of string is " + cobj);
		
	}

}

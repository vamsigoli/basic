package com.vamsi.core;

import java.util.ArrayList;
import java.util.List;

public class PalindomeTest {

	
	public static void main(String argv[] ){
			
		System.out.println(testPalindome("a"));
		System.out.println(testPalindome("aa"));
		System.out.println(testPalindome("ab"));
		System.out.println(testPalindome("roor"));
		System.out.println(testPalindome("bow"));
		
		final List a = new ArrayList();
		
		
		a.add("a");
	}
	
	public static boolean testPalindome(final String test) {
		
		if (test == null || test.length() == 0 ) return false;
		
		if (test.length() == 1 ) return true;
		
		for (int i =0,  j= test.length() -1; i < test.length() /2 ;i++,j--) {
			
			if (test.charAt(i) != test.charAt(j)) return false;
			
		}
		
		return true;
		
	}
	
	
	public void testList(List a) {

		a.add("b");
		
		a = new ArrayList();
		
		a.add("c");
	}
	
}

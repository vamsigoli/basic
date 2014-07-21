package com.vamsi.core;

import static org.junit.Assert.*;


import org.junit.Test;

public class LambdaExpressionTester {
	
	@Test 
	public final void testNormally() {
		FunctionalInterfaces fi = new FunctionalInterfaces();
		
		int answer = fi.doesCalc(new Calculate() {
			@Override
			public int calculate(int a, int b) {
				return a+b;
			}
		});		
		assertEquals(8,answer);		
	}
	
	@Test
	public final void testLambda() {
		FunctionalInterfaces fi = new FunctionalInterfaces();
		
		//arguments -> block 
		
		int answer1 = fi.doesCalc((int x, int y) -> {return x+y;});
		assertEquals(8, answer1);
		
		//multiple statements
		int answer2 = fi.doesCalc((int x, int y) -> {int a = x*x; a = a+1;return x+y;});
		assertEquals(8, answer2);
		
		int answer = fi.doesCalc((x,y) -> (x+y));
		//annonymous innerclasses are replaced by a single lambda expression
		//because the interface expects only one method as the interface is marked
		//as functional interface. type of the arguments are inferred correctly because of 
		//the interface
		assertEquals(8, answer);
		
		//without parameters
		
		new Thread(
				() -> System.out.println("Hi from lambda")
				).start();
		//and in a single line
		new Thread(() -> System.out.println("Hi from lambda")).start();
		//we are expecting a object that implements a runnable interface that
		//has only one method. now in java 8 runnable will be marked as a functionalinterface. 
		//no arguments so () and the block is justa sysout statement.
		
		
		
		
		
	}

}

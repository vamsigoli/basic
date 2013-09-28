package com.vamsi.core;

public class ThreadStateTester implements Runnable {

	private java.lang.Thread self;

	public java.lang.Thread getSelf() {
		return self;
	}

	public void setSelf(java.lang.Thread self) {
		this.self = self;
	}

	public static void main(String[] args) throws Exception {
		
		ThreadStateTester a = new ThreadStateTester();
		ThreadStateTester b = new ThreadStateTester();
				
		
		Thread first = new Thread(a);
		a.setSelf(first);
		Thread second = new Thread(b);
		b.setSelf(second);
		
		System.out.println("first thread state before start is " + first.getState());
		System.out.println("second thread state before start is " + second.getState());
		
		first.start();
		second.start();
		
		System.out.println("first thread state after start is " + first.getState());
		System.out.println("second thread state after start is " + second.getState());
		
		Thread.sleep(100);
		
		System.out.println("first thread state after sleep is " + first.getState());
		System.out.println("second thread state after sleep is " + second.getState());
		

	}

	public void run() {

		System.out.println("running the thread");

		System.out.println("my id is " + getSelf().getId());
		
		System.out.println("my state is " + getSelf().getState());
	}

}

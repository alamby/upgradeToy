package com.carlos.luke.exception;
public class InterruptedExceptionExample {
	public static void main(String[] args) throws InterruptedException {
		// Create a new thread.
		Thread thread = new SampleThread();
		
		//Start the thread's execution.
		thread.start();
		
		//Interrupt the thread.
		thread.interrupt();
		
		//Join the thread.
		thread.join();
	}
}
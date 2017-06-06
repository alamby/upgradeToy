package com.carlos.luke.exception;
public class InterruptedExceptionExample {
	public static void main(String[] args) throws InterruptedException {
		// Create a new thread.
		Thread thread = new SampleThread();
		
		//Start the thread's execution.
		thread.start();
		
        System.out.println("thread.isInterrupted():"+thread.isInterrupted());
		
		//Interrupt the thread.
		thread.interrupt();
		
		System.out.println("thread.isInterrupted():"+thread.isInterrupted());
		
		//Join the thread.
		thread.join();
	}
}
package com.carlos.luke.exception;
import java.util.concurrent.TimeUnit;

public class SampleThread extends Thread {
	public SampleThread() {
		super();
		System.out.println("An instance of the " + SampleThread.class + " class was created!");
	}
	
	@Override
	public void run() {
		try {
			/* Sleep for some seconds. */
			TimeUnit.SECONDS.sleep(10);
		}
		catch(InterruptedException ex) {
			System.err.println("An InterruptedException was caught: " + ex.getMessage());
		}
	}
}
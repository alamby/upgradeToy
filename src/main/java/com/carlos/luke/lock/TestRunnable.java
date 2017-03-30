package com.carlos.luke.lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestRunnable implements Runnable {
	ReentrantLock lock = new ReentrantLock();

	public void get() {
		lock.lock();
		System.out.println(Thread.currentThread().getId());
		set();
		lock.unlock();
	}

	public void set() {
		lock.lock();
		System.out.println(Thread.currentThread().getId());
		lock.unlock();
	}

	@Override
	public void run() {
		get();
	}

	public static void main(String[] args) {
		TestRunnable ss = new TestRunnable();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}
package com.carlos.luke.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Test2 implements Runnable {
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
		Test2 ss = new Test2();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}
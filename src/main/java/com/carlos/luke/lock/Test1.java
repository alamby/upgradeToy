package com.carlos.luke.lock;
public class Test1 implements Runnable{

	public synchronized void get(){
		System.out.println(Thread.currentThread().getId());
		set();
	}

	public synchronized void set(){
		System.out.println(Thread.currentThread().getId());
	}

	@Override
	public void run() {
		get();
	}
	public static void main(String[] args) {
		Test1 ss=new Test1();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}

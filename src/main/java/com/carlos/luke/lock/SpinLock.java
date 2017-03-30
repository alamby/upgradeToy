package com.carlos.luke.lock;

import java.util.concurrent.atomic.AtomicReference;

/*
 * 非可重入锁
 * 若有同一线程两调用lock() ，会导致第二次调用lock位置进行自旋，产生了死锁
 */
public class SpinLock {
	private AtomicReference<Thread> owner =new AtomicReference<>();
	public void lock(){
		Thread current = Thread.currentThread();
		while(!owner.compareAndSet(null, current)){
		}
	}
	public void unlock (){
		Thread current = Thread.currentThread();
		owner.compareAndSet(current, null);
	}
	
    public static void main(String[] args) {
        final SpinLock lock = new SpinLock();
        Runnable runnable = new Runnable() {
            public void get() {
                lock.lock();
                System.out.println("get,id:"+Thread.currentThread().getId());
                set();
                lock.unlock();
            }
            
            public void set() {
                lock.lock();
                System.out.println("set,id:"+Thread.currentThread().getId());
                lock.unlock();
            }
            
            @Override
            public void run() {
                get();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
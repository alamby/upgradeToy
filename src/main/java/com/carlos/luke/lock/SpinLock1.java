package com.carlos.luke.lock;

import java.util.concurrent.atomic.AtomicReference;

/*
 * 可重入锁
 */
public class SpinLock1 {
	private AtomicReference<Thread> owner =new AtomicReference<>();
	private int count =0;
	public void lock() {
		Thread current = Thread.currentThread();
		if(current==owner.get()) {
			count++;
			return ;
		}

		while(!owner.compareAndSet(null, current)){

		}
	}
	public void unlock () {
		Thread current = Thread.currentThread();
		if(current==owner.get()){
			if(count!=0){
				count--;
			}else{
				owner.compareAndSet(current, null);
			}

		}

	}
	
	public static void main(String[] args) {
	    final SpinLock1 lock = new SpinLock1();
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
package com.carlos.luke.communication.interthread;

import java.util.concurrent.atomic.AtomicInteger;

/**
* @desc    
* @since   2017年7月31日
*
*/
public class VolatileNotSafe {
    public static volatile int count;
    public static volatile AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for(int i=0; i<20; i++){
            threads[i] = new Thread(new Runnable() {
                @Override  
                public void run() {
                    for(int j=0; j<100; j++){
                        count++;
                        for (;;) {
                            int enter = atomicCount.get();
                            boolean suc = atomicCount.compareAndSet(enter, ++enter);
                            if (suc) {
                                break;
                            }
                        }
                    }
                }
            });
              
            threads[i].start();
        }  
      
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(count);
        System.out.println(atomicCount.get());
    }

}

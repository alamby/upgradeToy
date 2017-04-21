package com.carlos.luke.communication.interthread;

import java.util.concurrent.CountDownLatch;

/**
* @desc    
* @since   2017年4月21日
* 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
*/
public class CountdownLatch {

    public static void main(String[] args) {
        runDAfterABC();
    }
    
    private static void runDAfterABC() {
        int worker = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(worker);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D is waiting for other three threads");
                try {
                    countDownLatch.await();
                    System.out.println("All done, D starts working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        for (char threadName='A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(tN + " is working");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(tN + " finished");
                    countDownLatch.countDown();
                }
            }).start();
        }
    }
    
}

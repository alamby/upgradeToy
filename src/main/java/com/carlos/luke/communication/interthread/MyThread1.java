package com.carlos.luke.communication.interthread;
public class MyThread1 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("thread is interrupted , enter catch! "+this.isInterrupted());
            e.printStackTrace();
        }
    }
}
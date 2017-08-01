package com.carlos.luke.communication.interthread;
public class MyThread2 extends Thread{
    @Override
    public void run() {
        super.run();
        while (true){
            if (this.isInterrupted()){
                System.out.println("The thread is stop!");
                return;
            }
            System.out.println("timer="+System.currentTimeMillis());
        }
    }
}
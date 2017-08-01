package com.carlos.luke.communication.interthread;
public class ThreadMain {
    public static void main(String[] args){
        try {
            MyThread1 thread=new MyThread1();
            thread.start();
            Thread.sleep(100);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end!");
    }
}
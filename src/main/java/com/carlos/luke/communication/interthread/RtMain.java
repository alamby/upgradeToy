package com.carlos.luke.communication.interthread;

public class RtMain {
    public static void main(String args[]) throws InterruptedException{
        MyThread2 thread=new MyThread2();
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }
}
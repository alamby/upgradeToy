package com.carlos.luke.communication.interthread.waitNotify;
public class Main{
    public static void main(String args[]){
        try{
        Object lock=new Object();
        WaitThread waitThread=new WaitThread(lock);
        waitThread.start();
        Thread.sleep(1000);
        NotifyThread notifyThread = new NotifyThread(lock);
        notifyThread.start();
        }catch (InterruptedException e){
          e.printStackTrace();
        }
    }
}
package com.carlos.luke.communication.interthread.waitNotify;

public class NotifyThread  extends Thread{
    private Object lock;
    public NotifyThread(Object lock){
        super();
        this.lock=lock;
    }
    @Override
    public void run(){
        synchronized (lock){
              System.out.print("before notify,");
              lock.notify();
              System.out.print("after notify,");
          }
    }
}


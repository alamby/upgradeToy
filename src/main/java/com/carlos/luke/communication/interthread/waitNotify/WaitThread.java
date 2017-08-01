package com.carlos.luke.communication.interthread.waitNotify;
public class WaitThread  extends Thread{
    private Object lock;
    public WaitThread(Object lock){
        super();
        this.lock=lock;
    }
    @Override
    public void run(){
        try{
          synchronized (lock){
              System.out.print("before wait,");
              lock.wait();
              System.out.print("after wait,");
          }
          System.out.print("after synchronized");
          }catch (InterruptedException e){
              e.printStackTrace();
          }
    }
}


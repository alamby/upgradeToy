package com.carlos.luke.communication.interthread.waitNotify;

/**
* @desc    
* @since   2017年8月1日
*
*/
public class ThreadJoinDemo {
    
    public static void main(String args[]) throws InterruptedException{
        Thread joinThread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("join thread,");
                
            }
        });
        joinThread.start();
        joinThread.join();//可以让主线程一直等子线程执行完毕后在继续往下，否则可能主线程先执行完了
        System.out.println("main thread,");
    }
}

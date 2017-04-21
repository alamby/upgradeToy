package com.carlos.luke.communication.interthread;

/**
* @desc    
* @since   2017年4月21日
* 两个线程按照指定方式有序交叉运行
*
*/
public class OrderAndCross {

    public static void main(String[] args) {
        demo3();
    }
    
    /**
     *  A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3
     *  A 1, B 1, B 2, B 3, A 2, A 3
     *  
     *  
     *  执行过程出现了B先获得锁的现象，如下:
     *  INFO: A 等待锁
     *  INFO: B 等待锁
     *  INFO: B 得到了锁 lock
     *  B 1
     *  B 2
     *  B 3
     *  INFO: B 打印完毕，调用 notify 方法
     *  INFO: A 得到了锁 lock
     *  A 1
     *  INFO: A 准备进入等待状态，放弃锁 lock 的控制权
     *  
     *  然后A处于一直等待被notify的情况
     */
    private static void demo3() {
        final Object lock = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: A 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: A 得到了锁 lock");
                    System.out.println("A 1");
                    try {
                        System.out.println("INFO: A 准备进入等待状态，放弃锁 lock 的控制权");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("INFO: 有人唤醒了 A, A 重新获得锁 lock");
                    System.out.println("A 2");
                    System.out.println("A 3");
                }

            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: B 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: B 得到了锁 lock");
                    System.out.println("B 1");
                    System.out.println("B 2");
                    System.out.println("B 3");

                    System.out.println("INFO: B 打印完毕，调用 notify 方法");
                    lock.notify();
                }
            }
        });
        A.start();
        B.start();
    }
    
}

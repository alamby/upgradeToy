package com.carlos.luke.communication.interthread;

/**
* @desc    
* @since   2017年4月21日
* 两个线程依次执行
*/
public class OneByOne {

    public static void main(String[] args) {
        demo2();
    }
    
    /*
     * 一个是线程 A，另一个是线程 B，两个线程分别依次打印 1-3 三个数字
     */
    private static void demo1() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("B");
            }
        });

        A.start();
        B.start();
    }
    
    /*
     *  B 在 A 全部打印完后再开始打印
     */
    private static void demo2() {
        final Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 A");
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                printNumber("B");
            }
        });

        B.start();
        A.start();
    }
    
    private static void printNumber(String threadName) {
        int i=0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print: " + i);
        }
    }
    
}

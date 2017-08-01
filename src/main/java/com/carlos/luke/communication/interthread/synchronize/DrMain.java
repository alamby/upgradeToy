package com.carlos.luke.communication.interthread.synchronize;

//测试入口
public class DrMain {
    public static void main(String args[]){
        try {
            PublicVar publicVar=new PublicVar();
            ThreadA threadA=new ThreadA(publicVar);
            threadA.start();
            threadA.sleep(20);//不加的话，getValue可能会先执行，出现thread:main usernameA passwordAA
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
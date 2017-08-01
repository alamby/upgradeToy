package com.carlos.luke.communication.interthread.synchronize;

//测试线程
public class ThreadA extends Thread {
    private PublicVar publicVar;

    public ThreadA(PublicVar publicVar) {
        super();
        this.publicVar = publicVar;
    }
    @Override
    public void run() {
        super.run();
        publicVar.setValue("B","BB");
    }
}

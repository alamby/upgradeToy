package com.carlos.luke.communication.interthread.synchronize;

//测试synchronized类
public class PublicVar {
    public String username="A";
    public String password="AA";
    synchronized public void setValue(String username,String password){
        try {
            this.username=username;
            Thread.sleep(2000);
            this.password=password;
            System.out.println("thread:"+Thread.currentThread().getName()+
                    " username"+this.username+" password"+this.password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getValue(){
        System.out.println("thread:"+Thread.currentThread().getName()+
                " username"+this.username+" password"+this.password);
    }
}
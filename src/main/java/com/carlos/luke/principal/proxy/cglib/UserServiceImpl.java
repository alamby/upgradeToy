package com.carlos.luke.principal.proxy.cglib;

public class UserServiceImpl {  
    public void add() {  
        System.out.println("This is add service");  
    }  
    public void delete(int id) {  
        System.out.println("This is delete service：delete " + id );  
    }
    public final void update(int id) {  
        System.out.println("This is delete update " + id );  
    }
}
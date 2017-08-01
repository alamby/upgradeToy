package com.carlos.luke.principal.proxy.jdk;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * Created by chaofan
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target) {
        this.target=target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method :"+ method.getName()+" is invoked!");
        return method.invoke(target,args);
    }
}
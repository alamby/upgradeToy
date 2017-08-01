package com.carlos.luke.principal.proxy.jdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class JDKProxyTest {
    public static void main(String[]args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //这里有两种写法，我们采用略微复杂的一种写法，这样更有助于大家理解。
        System.out.println(JDKProxyTest.class.getClassLoader());
/*        Class<?> proxyClass= Proxy.getProxyClass(JDKProxyTest.class.getClassLoader(),Helloworld.class);
        final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
        final InvocationHandler ih = new MyInvocationHandler(new HelloworldImpl());
        Helloworld helloWorld= (Helloworld)cons.newInstance(ih);
        helloWorld.sayHello();*/
 
        //下面是更简单的一种写法，本质上和上面是一样的
        Helloworld helloWorld=(Helloworld)Proxy.newProxyInstance(
                        JDKProxyTest.class.getClassLoader(),
                        new Class<?>[]{Helloworld.class},
                        new MyInvocationHandler(new HelloworldImpl()));
        helloWorld.sayHello();
        
    }
 
}
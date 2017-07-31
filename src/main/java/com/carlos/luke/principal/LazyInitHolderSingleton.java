package com.carlos.luke.principal;

/*
 * 利用了JVM对类初始化时会获取一个锁。该锁可以同步多个线程对同一个类的初始化
 * 也被称为“最好的”单例
 */
public class LazyInitHolderSingleton {
    private LazyInitHolderSingleton() {
    }

    private static class SingletonHolder {
        private static final LazyInitHolderSingleton INSTANCE = new LazyInitHolderSingleton();
    }

    public static LazyInitHolderSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
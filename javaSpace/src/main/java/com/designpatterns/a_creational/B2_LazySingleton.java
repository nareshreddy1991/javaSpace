package com.designpatterns.a_creational;

public class B2_LazySingleton {
    public static void main(String[] args) {

    }
}

class LazyInitializedSingleton {
    //prior to java 5 java memory model has few issues, so we need to add volatile keyword to work as expected
    private static volatile LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {
    }

    //Singleton for single threaded env
    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }

    //Singleton with thread safe - bad performance
    public static synchronized LazyInitializedSingleton getInstance1() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }

    //thread safe with good performance
    public static LazyInitializedSingleton getInstanceUsingDoubleLocking() {
        if (instance == null) { // 2. This is to avoid synchronization every time
            synchronized (LazyInitializedSingleton.class) {//1. this is to thread safety
                if (instance == null) {
                    instance = new LazyInitializedSingleton();
                }
            }
        }
        return instance;
    }
}

/*
Bill Pugh Singleton Implementation: This is easiest way to write thread safe singleton
Prior to Java 5, java memory model had a lot of issues and the above approaches used to fail in certain scenarios where too many threads
try to get the instance of the Singleton class simultaneously. So Bill Pugh came up with a different approach to create the Singleton class
using an inner static helper class.
 */
class BillPughSingleton {

    private BillPughSingleton() {
    }

    private static class SingletonHelper {//This class will not be loaded to memory untill some calls it.
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {// when multiple threads calls at the same time, JVM take care of synchronization
        return SingletonHelper.INSTANCE;
    }
}
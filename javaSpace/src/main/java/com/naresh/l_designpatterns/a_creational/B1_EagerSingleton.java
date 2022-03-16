package com.naresh.l_designpatterns.a_creational;

/*
Singleton Pattern
Singleton pattern restricts the instantiation of a class and ensures that only one instance of the class exists in the java virtual machine.
The singleton class must provide a global access point to get the instance of the class.
Singleton pattern is used for logging, drivers objects, caching and thread pool.
Singleton design pattern is also used in other design patterns like Abstract Factory, Builder, Prototype, Facade etc.
Singleton design pattern is used in core java classes also, for example java.lang.Runtime, java.awt.Desktop.
 */
public class B1_EagerSingleton {
    public static void main(String[] args) {

    }
}

/*
TODO Eager initialization:
Object will be created at class loading time
Even if object is not used by anyone it will be created and kept it memory
This is useful when it is lightweight object.
Drawbacks: we can't handle exception while creating object
 */
class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();//can't handle exception

    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton() {
    }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}

/*
TODO Static block initialization
Static block initialization implementation is similar to eager initialization, except that instance of class is created
 in the static block that provides option for exception handling.
 */
class StaticBlockSingleton {

    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {
    }

    //static block initialization for exception handling
    static { // static block execute at the time of class loading/ initialization block executes at the time of object creation
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
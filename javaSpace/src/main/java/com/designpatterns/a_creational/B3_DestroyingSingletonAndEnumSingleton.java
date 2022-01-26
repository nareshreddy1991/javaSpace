package com.designpatterns.a_creational;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class B3_DestroyingSingletonAndEnumSingleton {
    public static void main(String[] args) {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {//TODO if we dont have constructor then it will take the default one
                //Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());//TODO we are able to create second obj

        //TODO try to break Enum singleton - This will not work
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = null;
        try {
            Constructor[] declaredConstructors = EnumSingleton.class.getDeclaredConstructors();
            for (Constructor declaredConstructor : declaredConstructors) {
                declaredConstructor.setAccessible(true);
                instance2 = (EnumSingleton) declaredConstructor.newInstance();//java.lang.IllegalArgumentException: Cannot reflectively create enum objects
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enum:" + instance);
        System.out.println(instance2);
    }
}
/*
Enum Singleton
To overcome this situation with Reflection, Joshua Bloch suggests the use of Enum to implement Singleton design pattern as Java ensures that any enum value
 is instantiated only once in a Java program. Since Java Enum values are globally accessible, so is the singleton. The drawback is that the enum type is somewhat inflexible;
  for example, it does not allow lazy initialization.

  As enums don’t have any constructor so it is not possible for Reflection to utilize it. Enums have their by-default constructor, we can’t invoke them by ourself.
  JVM handles the creation and invocation of enum constructors internally. As enums don’t give their constructor definition to the program,
  it is not possible for us to access them by Reflection also. Hence, reflection can’t break singleton property in case of enums.
 */

enum EnumSingleton {

    INSTANCE; //thread safe singleton object, eager way, we can't do lazy

    EnumSingleton() { //Even if we declare the constructor then it will throw exception (java.lang.IllegalArgumentException: Cannot reflectively create enum objects)

    }

    //since enum's doesn't have any constructor singleton can't be broken
    public static void doSomething() {
        //do something
    }
}

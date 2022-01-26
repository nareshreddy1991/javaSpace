package com.designpatterns.a_creational;

import java.io.*;

/*
Sometimes in distributed systems, we need to implement Serializable interface in Singleton class so that we can store its
 state in the file system and retrieve it at a later point of time.
 Here is a small singleton class that implements Serializable interface also.
 */
public class B4_SerializationSingleton {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                "filename.ser"));
        out.writeObject(instanceOne);
        out.close();

        //deserailize from file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream(
                "filename.ser"));
        SerializedSingleton instanceTwo = (SerializedSingleton) in.readObject(); //TODO readObject vs readResolve?
        in.close();

        System.out.println("instanceOne hashCode=" + instanceOne.hashCode());
        System.out.println("instanceTwo hashCode=" + instanceTwo.hashCode());

    }

}


class SerializedSingleton implements Serializable {

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton() {
    }

    private static class SingletonHelper {
        private static final SerializedSingleton instance = new SerializedSingleton();
    }

    public static SerializedSingleton getInstance() {
        return SingletonHelper.instance;
    }
    //TODO adding this method will solve the problem
    protected Object readResolve() {
        return getInstance();
    }

}
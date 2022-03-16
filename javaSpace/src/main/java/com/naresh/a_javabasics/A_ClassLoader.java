package com.naresh.a_javabasics;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/*
Classloader is a subsystem of JVM which is used to load class files. Whenever we run the java program, it is loaded first by the classloader.
There are three built-in classloaders in Java.

Bootstrap ClassLoader: This is the first classloader which is the super class of Extension classloader. It loads the rt.jar file which contains all class files of Java Standard Edition like java.lang package classes, java.net package classes, java.util package classes, java.io package classes, java.sql package classes etc.
Extension ClassLoader: This is the child classloader of Bootstrap and parent classloader of System classloader. It loades the jar files located inside $JAVA_HOME/jre/lib/ext directory.
System/Application ClassLoader: This is the child classloader of Extension classloader. It loads the classfiles from classpath. By default, classpath is set to current directory. You can change the classpath using "-cp" or "-classpath" switch. It is also known as Application classloader
 Bootstrap ClassLoader(first it will load all jvm related classes in rt.jar)
           |
  Extension ClassLoader(JAVA_HOME/jre/lib/ext)
            |
System/Application ClassLoader(classes written by us, from classpath)

 */
public class A_ClassLoader {
    public static void main(String[] args) {
        ClassLoader loader = A_ClassLoader.class.getClassLoader();
        System.out.println("Our class Loader:" + loader); //ClassLoaders$AppClassLoader
//        System.out.println("Our class Loader:"+Logging.class.getClassLoader());//Launcher$ExtClassLoader@3caeaf62
        System.out.println("Our class Loader:" + String.class.getClassLoader());//null - String is loaded by Bootstrap classloader
        System.out.println("Our class Loader:" + ArrayList.class.getClassLoader());//null - String is loaded by Bootstrap classloader
        //for Sting & ArrayList is it is loaded by Bootstrap, it is showing as null because it was written in Native code
    }
}

/*
Custom class loaders are helpful for more than just loading the class during runtime. A few use cases might include:

Helping to modify the existing bytecode, e.g. weaving agents
Creating classes dynamically suited to the user's needs, e.g. in JDBC, switching between different driver implementations is done through dynamic class loading.
Implementing a class versioning mechanism while loading different bytecodes for classes with the same names and packages. This can be done either through a URL class loader (load jars via URLs) or custom class loaders.
 */
class CustomClassLoader extends ClassLoader {

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }

}
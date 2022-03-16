package com.naresh.m_concurrencyDefogTech;

import java.util.stream.IntStream;

/*
in Synchronized block/non static Synchronized methods two threads can execute the same method/block of code on two different objects, because that is object level locking.
In static synchronization only one thread will be able to execute static methods, here lock will be acquired on class level.
If that class is having any instance methods then using its object any other thread can access them.
Need to use it carefully because only one class leve lock will be available for entire application
 */
public class _2C_SynchronizedStaticMethod {
    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        Thread t1 = new Thread(() -> PrintNumbers.print(1));
        Thread t2 = new Thread(() -> PrintNumbers.print(2));
        Thread t3 = new Thread(() -> PrintNumbers.print(3));
        Thread t4 = new Thread(() -> PrintNumbers.print(4));
        Thread t5 = new Thread(() -> printNumbers.printf(5));
        Thread t6 = new Thread(() -> PrintNumbers.printX(5));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}

class PrintNumbers {
    public synchronized static void print(int n) {
        IntStream.rangeClosed(1, 5).forEach(i -> System.out.print(i * n));
        System.out.println();
    }

    public synchronized static void printX(int n) {// Since we have only once class level lock only one static synchronized methods can run at a time
        IntStream.rangeClosed(1, 5).forEach(i -> System.out.print("X" + i * n));
        System.out.println();
    }

    synchronized public void printf(int n) {//This method can run while other static methods are running, this required object level lock, thread can get object leve lock while class level lock acquired by other threads
        IntStream.rangeClosed(1, 5).forEach(i -> System.out.print("printf:" + i * n));
        System.out.println();
    }
}


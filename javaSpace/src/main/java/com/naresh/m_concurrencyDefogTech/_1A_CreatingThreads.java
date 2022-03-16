package com.naresh.m_concurrencyDefogTech;

import java.io.Serializable;
/*
Multithreading in Java is a process of executing multiple threads simultaneously.
A thread is a lightweight sub-process, the smallest unit of processing. Multiprocessing and multithreading, both are used to achieve multitasking.

However, we use multithreading than multiprocessing because threads use a shared memory area. They don't allocate separate memory area so saves memory, and context-switching between the threads takes less time than process.
 */

public class _1A_CreatingThreads {
    public static void main(String[] args) {
        System.out.println("jai Screen Rama");
        Thread runnableThread1= new Thread(new RunnableThread());
        runnableThread1.start();
        Thread runnableThread2 = new Thread(() -> System.out.println("Java 8 lambda thread is running"));
        runnableThread2.start();
        Thread thread1= new ThreadClassThread();
        thread1.start();
        //runnableThread2.start();//we can't start the thread twice, it will throw IllegalthreadStateException
        try {
            runnableThread1.join();//main thread should wait un till this thread is finished
            runnableThread2.join(500);
            thread1.join(500);//wait till 500ms otherwise proceed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main is done");
    }
}

class RunnableThread implements Runnable, Serializable{//when we need multiple inheritance we can use Runnable

    @Override
    public void run() {
        System.out.println("RunnableThread one is running...");
    }
}
class ThreadClassThread extends Thread  {
    @Override
    public void run() {
        ThreadUtils.sleep(1000);
        System.out.println("ThreadClassThread one is running...");
    }
}

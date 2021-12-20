package com.defogTech.concurrency;
/*
Daemon thread in Java is a low-priority thread that runs in the background to perform tasks such as garbage collection. ex: GC, finalizer
Daemon thread in Java is also a service provider thread that provides services to the user thread. Its life depends on the mercy of user threads i.e.
when all the user threads die, JVM terminates this thread automatically.
In simple words, we can say that it provides services to user threads for background supporting tasks. It has no role in life other than to serve user threads.
Main thread is non deamon, Deaamon status will be inherited from parents.
 */
public class _3_DeamonThread {

    public static void main(String[] args) {
        new WorkerThread().start();// setDeamon(true) should be set before starting the thread

        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            // handle here exception
        }

        System.out.println("Main Thread ending") ;
    }

}

class WorkerThread extends Thread {

    public WorkerThread() {
        // When false, (i.e. when it's a non daemon thread),
        // the WorkerThread continues to run.
        // When true, (i.e. when it's a daemon thread),
        // the WorkerThread terminates when the main
        // thread or/and user defined thread(non daemon) terminates.
        setDaemon(true);//TODO
    }

    public void run() {
        int count = 0;

        while (true) {// when is it Deamon, it will be terminated by JVM when main thread is done
            System.out.println("Hello from Worker "+count++);

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                // handle exception here
            }
        }
    }
}

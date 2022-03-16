package com.naresh.m_concurrencyDefogTech;

/*
TODO important: NOt able to reproduce
If a waiting Thread is not notified by calling notify() or notifyAll() on the object the said thread is waiting on,
then any one of the following may happen:

the Thread keeps waiting in the object's wait pool
the Thread becomes runnable if a timeout was specified and the time elapses
the Thread gets interrupted and becomes runnable again
the Thread wakes up for no reason at all i.e. it was neither notified nor interrupted
The last case is known as a spurious wake-up and is one of the reasons why upon wake-up a Thread should always check
 whether the condition it was waiting for is true or not. If not, the Thread should call and go wait() again.

TODO Reentrant Monitor in Java
According to Sun Microsystems, Java monitors are reentrant means java thread can reuse the same monitor for different synchronized
methods if method is called from the method.
When one synchronized method is called another synch method then it use the same lock , it help in avoiding single thread dead locks
 */
public class _2E3_ThreadWaiting {
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (object) {
                object.notifyAll();
            }
        });
        Thread t3 = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t2.start();
        t3.start();
        t.join();
        t2.join();
        t3.join();
        System.out.println("Waiting thread was never notified and completed");
    }
}


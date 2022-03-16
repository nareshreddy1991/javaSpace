package com.naresh.m_concurrencyDefogTech;
/*
Inter-thread communication or Co-operation is all about allowing synchronized threads to communicate with each other.
It must happen inside sync block
 */
public class _2E_ThreadCommunication {
    static Print print = new Print();

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            ;
            synchronized (print) {
                for (int i = 0; i < 5; i++) {
                    print.print();
                    try {
                        print.notify();//it is not a blocking operation, we can notify any number of time even if no thread is waiting for it
                        System.out.println(Thread.currentThread().getName() + "Notified...");
                        if (i != 4)
                            print.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "OddThread");
        Thread evenThread = new Thread(() -> {
            ThreadUtils.sleep(2);//Need to print 1 at the start so making Odd get the lock first
            synchronized (print) {
                for (int i = 0; i < 5; i++) {
                    print.print();
                    print.notify();
                    System.out.println(Thread.currentThread().getName() + "Notified...");
                    try {
                        if (i != 4)
                            print.wait();// current thread will wait & release the lock of print obj, so other threads can use that obj
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Event Thread");
        oddThread.start();
        evenThread.start();
    }
}


class Print {
    static int number = 0;

    public void print() {
        System.out.println(Thread.currentThread().getName() + ":" + (++number));
    }
}

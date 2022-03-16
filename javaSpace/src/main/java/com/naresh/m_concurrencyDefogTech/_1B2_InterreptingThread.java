package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
Interrupting is cooperative machanisum, when a thread is interrupted it will not stop execution immediately,this will help to maintain the data integrity
used in long running threads
    object.wait()
    Thread.sleep()
    Thread.join();
    All these methods throw Interrupted exception

If any thread is in sleeping or waiting state (i.e. sleep() or wait() is invoked), calling the interrupt() method on the thread,
 breaks out the sleeping or waiting state throwing InterruptedException. If the thread is not in the sleeping or waiting state,
 calling the interrupt() method performs normal behaviour and doesn't interrupt the thread but sets the interrupt flag to true.
         */
public class _1B2_InterreptingThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread counter = new Thread(new PrintCounter());
        counter.start();
        counter.interrupt();//interrupt the thread, so isInterrupted flag will be true
        ThreadUtils.sleep(1);
        counter.interrupt();

        //Thread counter2 = new Thread(new PrintCounter2()); //we can't do this, need to use Executor services
        FutureTask<Object> futureTask= new FutureTask<>(new PrintCounter2());//FutureTask implements Runnable
//        System.out.println("fut"+futureTask.get()); //if you call this method before submitting thread it will keep waiting
        Thread callableThread= new Thread(futureTask);
        callableThread.start();
        callableThread.interrupt();
        futureTask.get();//Exception will be thrown when we call get method
    }
}

class PrintCounter implements Runnable {

    @Override
    public void run() {// Run method can't throw checked exception, because base method is not throwing
        int i = 0;
        while (true) {
            System.out.println(i++);
            if (Thread.currentThread().isInterrupted()) {
                // throw new InterruptedException();
                System.out.println("Thread is interrupted");
                Thread.interrupted();// return true when interrupted, and reset the flag to false otherwise return false
                break;
            }
        }
//        ThreadUtils.sleep(2);
        while (true) {

            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted again" + Thread.currentThread().isInterrupted());
                break;
            } else System.out.println("Thread is still running");
        }
    }
}


class PrintCounter2 implements Callable {

    @Override
    public Object call() throws Exception {//we can throw checked Exception
        while (true) {
            if (Thread.interrupted()) {//interrupted() method is recommended while throwing exceptions
                System.out.println("Going to throw exception");
                throw new InterruptedException();//When we need to inform parent thread that it is interrupted we can throw exception
            }
        }
    }
}
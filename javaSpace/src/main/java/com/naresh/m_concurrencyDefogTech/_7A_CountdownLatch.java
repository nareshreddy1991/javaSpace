package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
CountDownLatch is used to make sure that a task waits for other threads before it starts.
 To understand its application, let us consider a server where the main task can only start when all the required services have started.
 */
public class _7A_CountdownLatch {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch= new CountDownLatch(3);
        executorService.submit(new SomeTask(latch));
        executorService.submit(new SomeTask(latch));
        executorService.submit(new SomeTask(latch));
        executorService.submit(new SomeTask(latch));// This will not be considered

        try {
            latch.await();// wait till the above three threads complete initialization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("All three threads are done initialization");
    }
}

class SomeTask implements Runnable{

    CountDownLatch latch;

    public SomeTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        //some
        latch.countDown();
        System.out.println("Count:"+latch.getCount());
        //do something
    }
}

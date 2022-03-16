package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
CyclicBarrier is used to make threads wait for each other. It is used when different threads process a part of computation and when all threads have completed the execution,
 the result needs to be combined in the parent thread. In other words, a CyclicBarrier is used when multiple thread carry out different sub tasks and the output of these
  sub tasks need to be combined to form the final output. After completing its execution, threads call await() method and wait for other threads to reach the barrier.
 Once all the threads have reached, the barriers then give the way for threads to proceed.

 Once all threads reach at a certine portion then they all move further.(Ex: In a gaming app they need to send message at once to all the players)
 */
public class _7B_CyclicBarrier {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CyclicBarrier barrier= new CyclicBarrier(3);
        executorService.submit(new DummyTask(barrier));
        executorService.submit(new DummyTask(barrier));
        executorService.submit(new DummyTask(barrier));

        executorService.shutdown();
    }
}
class DummyTask implements Runnable{
    CyclicBarrier barrier;

    public DummyTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        //some
        try {
            System.out.println("going to wait for Phase 1");
            barrier.await();// once all three threads came here then all go further
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //do something
        try {
            System.out.println("going to wait for Phase 2");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Processing other part");
    }
}

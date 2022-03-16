package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/*
Phaser’s primary purpose is to enable synchronization of threads that represent one or more phases of activity.
 It lets us define a synchronization object that waits until a specific phase has been completed.
 It then advances to the next phase until that phase concludes. It can also be used to synchronize a single phase, and in that regard, it acts much like a CyclicBarrier.
 Phaser = CountDownlatch + CyclicBarrier + more flexibility

 */
public class _7C_PhaserAsCountDownLatch {
    public static void main(String[] args) {
        AtomicInteger counter= new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Phaser phaser = new Phaser(3);
        executorService.submit(new DummyX(phaser, counter));
        executorService.submit(new DummyX(phaser, counter));
        executorService.submit(new DummyX(phaser, counter));

        int i = phaser.awaitAdvance(0);//it waits untill all threds complete phase 0 & returns the next phase number
        System.out.println("All three threads arrived Phase0: nextphase is"+i +"Count:"+counter.get());// count will be 3
        int i1 = phaser.awaitAdvance(1);
        System.out.println("All three threads arrived Phase1: nextphase is"+i1+"Count:"+counter.get());// count will be 6

//        int phase1 = phaser.arriveAndAwaitAdvance();
//        System.out.println("Completed phase number:"+phase1+"Count:"+counter.get());
        executorService.shutdown();
    }
}

class DummyX implements Runnable {
    Phaser phaser;
    AtomicInteger counter;

    public DummyX(Phaser phaser,AtomicInteger counter) {
        this.phaser = phaser;
        this.counter= counter;
    }

    @Override
    public void run() {
        counter.incrementAndGet();
        int arrive = phaser.arrive();//return phase number - this is 0th phase
        System.out.println(Thread.currentThread().getName()+"Arrived Phase:"+arrive);
        counter.incrementAndGet();
        arrive = phaser.arrive();//1st phase
        System.out.println(Thread.currentThread().getName()+"Arrived Phase:"+arrive);
    }
}

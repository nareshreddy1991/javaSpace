package com.defogTech.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
/*
phaser.arrive() - return the current phase number & phase will increment by one & thread will move on
phaser.arriveAndAwaitAdvance() - return the next phase number & phase will increment by one & it will block the execution
 */
public class _7D_PhaserAsCyclicBarrier {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Phaser phaser=new Phaser(3);
        executorService.submit(new DummyY(phaser));
        executorService.submit(new DummyY(phaser));
        executorService.submit(new DummyY(phaser));
//        executorService.submit(new DummyY(phaser)); //TODO no idea whats happen when we have more threads than parties

        executorService.shutdown();

    }
}
class DummyY implements Runnable{

    Phaser phaser;

    public DummyY(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
//        phaser.register(); //dynamic registration
        //Phase number start from 0
        System.out.println(Thread.currentThread().getName()+"Started"+phaser.getPhase());// current phase number 0
        /*
        similar to await method in cyclicbarrier , it will wait for all thread to arive here
        it returns arrival(next) phase number
         */
        int phase = phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"Advancing...phase:"+phase+" Phaser phase:"+phaser.getPhase());

        phase = phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+"Advancing...phase:"+phase+" Phaser phase:"+phaser.getPhase());

        phase = phaser.arriveAndDeregister();//one party will deregistered , other parties will be in the previous phase only
        System.out.println(Thread.currentThread().getName()+"Advancing...phase:"+phase+" Phaser phase:"+phaser.getPhase());

    }
}

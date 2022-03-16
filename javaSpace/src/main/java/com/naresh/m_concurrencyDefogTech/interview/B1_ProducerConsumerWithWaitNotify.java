package com.naresh.m_concurrencyDefogTech.interview;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Scenario: multiple producer thread produces item to container & multiple consumer threads consumer the values from container
When queue is full producer has to wait & when queue is empty consumer has to wait, this process should continue infinitely
TODO: Explore mor on BlockingQueue
 */
public class B1_ProducerConsumerWithWaitNotify {
    public static void main(String[] args) {
        ItemManager manager = new ItemManager();
        Thread p1 = new Thread(() -> {
            while (true)
                synchronized (manager) {//TODO or we can use Synchronized in method level or block level
                    manager.producer("item" + Math.random());
                }
        }, "Producer1:");
        Thread p2 = new Thread(() -> {
            while (true)
                synchronized (manager) {
                    manager.producer("item" + Math.random());
                }
        }, "Producer2:");
        Thread c1 = new Thread(() -> {
            while (true)
                synchronized (manager) {
                    manager.consumer();
                }
        }, "Consumer1:");
        Thread c2 = new Thread(() -> {
            while (true)
                synchronized (manager) {
                    manager.consumer();
                }
        }, "Consumer2:");
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}

class ItemManager {
    Queue<String> itemQueue = new ArrayDeque<>(10);

    public void producer(String item) {
        while (itemQueue.size() == 10) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                this.wait();
                /*TODO very impr: Since we have multiple producer threads if both are in waiting state for some time & both came out of waiting state at the same time
                TODO :only one thread can acquires lock, assume we the queue size is 9, one thread will go and add one element and release the lock the second thread also try
                TODO :to insert 11th element in the queue because the condition was checked already to avoid this issue, we need to use while loop rather than if condition.
                 */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " " + item);
        itemQueue.add(item);
        this.notify();
    }

    public void consumer() {
        while (itemQueue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                this.wait();
                /*TODO very impr: Since we have multiple consumer threads if both are in waiting state for some time & both came out of waiting state at the same time
                TODO :only one thread can acquires lock, assume we the queue size is 1, one thread will go and get one element and release the lock the second thread also try
                TODO :to get an element in the queue but queue is already empty because the condition was checked already to avoid this issue, we need to use while loop rather than if condition.
                 */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String remove = itemQueue.remove();
        this.notify();
        System.out.println(Thread.currentThread().getName() + " " + remove);
    }
}

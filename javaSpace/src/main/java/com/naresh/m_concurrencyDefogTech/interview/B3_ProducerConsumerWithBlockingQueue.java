package com.naresh.m_concurrencyDefogTech.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/*
TODO we can solve producer and consumer problem without wait & notify & synchronization
using blockingqueue - This internally block the thread when queue is full or empty
 */
public class B3_ProducerConsumerWithBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        Runnable producer = () -> {
            while (true) {
                String s = "Item" + Math.random();
                try {
                    queue.put(s);//TODO if queue is full thread will block un till someone remove value from the queue
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + s);
            }
        };

        Runnable consumer = () -> {
            while (true) {
                String remove = null;
                try {
                    remove = queue.take();//TODO if queue is empty thread will block un till some one add some value to queue
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + remove);
            }
        };
        new Thread(consumer, "Consumer1").start();
        new Thread(consumer, "Consumer2").start();

        new Thread(producer, "Producer1").start();
        new Thread(producer, "Producer2").start();


    }
}

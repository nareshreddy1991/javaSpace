package com.naresh.m_concurrencyDefogTech.interview;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class B2_ProducerConsumerWithLocksAndCondition {
    public static void main(String[] args) {
        ItemManagerWithLocks manager = new ItemManagerWithLocks();
        Thread p1 = new Thread(() -> {
            while (true)
                manager.producer("item" + Math.random());
        }, "Producer1:");
        Thread p2 = new Thread(() -> {
            while (true)
                manager.producer("item" + Math.random());
        }, "Producer2:");
        Thread c1 = new Thread(() -> {
            while (true)
                manager.consumer();
        }, "Consumer1:");
        Thread c2 = new Thread(() -> {
            while (true)
                manager.consumer();
        }, "Consumer2:");
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}

class ItemManagerWithLocks {
    Queue<String> itemQueue = new ArrayDeque<>(10);//TODO we can use any queue that a size limit
    ReentrantLock lock = new ReentrantLock();
    Condition producerCondition;
    Condition consumerCondition;

    public ItemManagerWithLocks() {
        producerCondition = lock.newCondition();
        consumerCondition = lock.newCondition();
    }

    public void producer(String item) {
        try {
            lock.lock();
            while (itemQueue.size() == 10) {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                producerCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + " " + item);
            itemQueue.add(item);
            consumerCondition.signalAll();
        } catch (Exception e) {// TODO catch is optional
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        try {
            lock.lock();
            while (itemQueue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                consumerCondition.await();
            }
            String remove = itemQueue.remove();
            producerCondition.signalAll();
            System.out.println(Thread.currentThread().getName() + " " + remove);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

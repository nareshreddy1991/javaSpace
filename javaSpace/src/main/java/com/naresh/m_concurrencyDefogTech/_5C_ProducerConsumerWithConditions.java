package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _5C_ProducerConsumerWithConditions {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition deposit = lock.newCondition();
        Condition withDraw = lock.newCondition();//how to work with multiple locks?
        BankingApplication application = new BankingApplication(lock, deposit, withDraw);
        Thread t1 = new Thread(() -> {
            for (int i = 100; i < 1000; i = i + 100)
                application.deposit(i);
        },"Producer");
        Thread t2 = new Thread(() -> {
            for (int i = 100; i < 1000; i = i + 100)
                application.withdraw(i);
        },"Consumer");
        t1.start();
        t2.start();
    }
}

class BankingApplication {
    private int balance = 0;
    Lock lock;
    Condition deposit;
    Condition withDraw;

    public BankingApplication(Lock lock, Condition deposit, Condition withDraw) {
        this.lock = lock;
        this.deposit = deposit;
        this.withDraw = withDraw;
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            while (balance == 0) {// TODO better to user while rather than if to avoid spurious wakeups
                System.out.println(Thread.currentThread().getName() + " Await");
                withDraw.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (balance > 0) {
            balance = balance - amount;
            System.out.println(Thread.currentThread().getName() + "Withdraw:" + amount);
            deposit.signal();//TODO notify deposit condition so that it will start running
            System.out.println(Thread.currentThread().getName() + " Signaled");
//                ThreadUtils.sleep(5);
        } else {

        }
        lock.unlock();
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            if (balance != 0) {
                System.out.println(Thread.currentThread().getName() + " Awaited");
                deposit.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (balance == 0) {
            balance = balance + amount;
            System.out.println(Thread.currentThread().getName() + "Deposited:" + amount);
            withDraw.signal();// TODO notify withdraw
            System.out.println(Thread.currentThread().getName() + " Signaled");
            ThreadUtils.sleep(5);
        }
        lock.unlock();
    }
}

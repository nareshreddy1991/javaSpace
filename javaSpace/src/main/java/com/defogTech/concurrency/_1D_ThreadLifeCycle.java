package com.defogTech.concurrency;
/*
public static final Thread.State NEW  - It represents the first state of a thread that is the NEW state.
public static final Thread.State RUNNABLE  - It represents the runnable state.It means a thread is waiting in the queue to run.
public static final Thread.State BLOCKED  -It represents the blocked state. In this state, the thread is waiting to acquire a lock.
public static final Thread.State WAITING
It represents the waiting state. A thread will go to this state when it invokes the Object.wait() method, or Thread.join() method with no timeout.
 A thread in the waiting state is waiting for another thread to complete its task.

public static final Thread.State TIMED_WAITING
It represents the timed waiting state. The main difference between waiting and timed waiting is the time constraint.
Waiting has no time constraint, whereas timed waiting has the time constraint. A thread invoking the following method
( sleep, join with timeout, wait with timeout, parkUntil, parkNanos) reaches the timed waiting state.

public static final Thread.State TERMINATED
It represents the final state of a thread that is terminated or dead. A terminated thread means it has completed its execution
 */
public class _1D_ThreadLifeCycle {
    public static void main(String[] args) throws InterruptedException {
        Thread t1= new Thread(new RunnableThread2());//New state
        System.out.println("Thread is created:"+t1.getState().name());
        Thread t2= new Thread(new RunnableThread2());
        t1.start();//Active state
        t2.start();//Thread move to Runnable state when
        System.out.println("Thread is Started:"+t1.getState().name());

        while (t1.isAlive()){
            System.out.println("Thread is Sleeping/Blocked:"+t1.getState().name());
        }
        t1.join();//Terminated state
        System.out.println("Thread is Completed:"+t1.getState().name());
    }
}

class RunnableThread2 implements Runnable{

    @Override
    public void run() {
        synchronized (this){//blocked state - while waiting for the locks
            System.out.println("Synchronized block executed");
            try {
                this.wait(100);//without passing time it thread will be in waiting state, with time it will be in Timed waiting state
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ThreadUtils.sleep(50);//Timed waiting state while sleeping

        System.out.println("Thread is running");
    }
}

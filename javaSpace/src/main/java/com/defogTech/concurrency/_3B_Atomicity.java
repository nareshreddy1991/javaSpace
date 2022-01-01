package com.defogTech.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/*
What is Atomic
Here's an example: Suppose foo is a variable of type long, then the following operation is not an atomic operation (in Java):

foo = 65465498L;
Indeed, the variable is written using two separate operations: one that writes the first 32 bits, and a second one which writes the last 32 bits.
That means that another thread might read the value of foo, and see the intermediate state.

Making the operation atomic consists in using synchronization mechanisms in order to make sure that the operation is seen, from any other thread, as a single, atomic (i.e. not splittable in parts), operation.
That means that any other thread, once the operation is made atomic, will either see the value of foo before the assignment, or after the assignment.But never the intermediate value.

A simple way of doing this is to make the variable volatile:

private volatile long foo;
Or to synchronize every access to the variable:

public synchronized void setFoo(long value) {
    this.foo = value;
}

public synchronized long getFoo() {
    return this.foo;
}
// no other use of foo outside of these two methods, unless also synchronized
Or to replace it with an AtomicLong:

private AtomicLong foo;
TODO -- Automic is used in increment/decrements & compound operations

volatile int i=1;
Thread 1 -------> i++; (Internally its a read/modify/write operations)
Thread 2 -------> i++;
Even though its a volatile it i will lead data inconsistency(volatile is not sutable for inc/dec)
One way to solve it is, but we will have scalability issue
Thread 1 --------> synchronized(obj){i++}
Thread 2 --------> synchronized(obj){i++}
So we can use AutomicInteger to solve this issue, we no need to write synch logic, they will maintain it.

 */
public class _3B_Atomicity {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger=new AtomicInteger();
        Thread t1= new Thread(()-> atomicInteger.incrementAndGet());
        Thread t2= new Thread(()-> atomicInteger.incrementAndGet());
        Thread t3= new Thread(()-> atomicInteger.decrementAndGet());
        t1.start();
        t2.start();
        t1.join(); t2.join(); t3.join();
        System.out.println("Counter:"+atomicInteger.get());
        /*
        Similar class are AtomicLong/ AtomicReference/ AtomicBoolean

        AtomicReference? for updating cache atomicly it is used
         */
    }
}

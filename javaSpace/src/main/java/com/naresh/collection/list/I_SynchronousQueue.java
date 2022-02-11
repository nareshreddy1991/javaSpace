package com.naresh.collection.list;

import java.util.concurrent.SynchronousQueue;

/*
Synchronous Queue:
public class SynchronousQueue<E> extends AbstractQueue<E>
    implements BlockingQueue<E>, java.io.Serializable

    -this implementation allows us to exchange information between threads in a thread-safe manner.
 */
public class I_SynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>(); //size 0 - you can't store any element, it will hand off the value to other thread
//        synchronousQueue.add(5); // throws IllegalStateException if you add any element
        System.out.println("offering:" + synchronousQueue.offer(10)); // return false
       /* try {
            synchronousQueue.put(25); //It will wait untill other threads takes the element
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

//        synchronousQueue.remove(); // throws NoSuchElementException
        System.out.println("poll"+synchronousQueue.poll());//return null
        try {
            synchronousQueue.take();// wait untill some thread give a value
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(synchronousQueue);
    }
}

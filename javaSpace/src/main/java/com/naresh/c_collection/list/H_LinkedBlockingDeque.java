package com.naresh.c_collection.list;

import java.util.Iterator;
import java.util.concurrent.*;

/*
Blocking Queue:
BlockingQueue<E> extends Queue<E>

    -It support operations that wait for the queue to become non-empty when retrieving and removing an element,
and wait for space to become available in the queue when adding an element.
    - These are thread safe
    - Not allows null
Different implementations of Blocking queue:
  LinkedBlockingQueue ( support bound and unbound )
  LinkedBlockingDeque ( support bound and unbound )
  PriorityBlockingQueue  ( support bound and unbound )
  ArrayBlockingQueue ( only bounded, need to pass capacity while creating the object)
  SynchronousQueue, etc ( direct hand off no size)

Adding Elements:
    add() – returns true if insertion was successful, otherwise throws an IllegalStateException
    put() – inserts the specified element into a queue, waiting for a free slot if necessary
    offer() – returns true if insertion was successful, otherwise false
    offer(E e, long timeout, TimeUnit unit) – tries to insert element into a queue and waits for an available slot within a specified timeout
Retrieving Elements
    take() – waits for a head element of a queue and removes it. If the queue is empty, it blocks and waits for an element to become available
    poll(long timeout, TimeUnit unit) – retrieves and removes the head of the queue, waiting up to the specified wait time if necessary for an element to become available. Returns null after a timeout
****************************************
LinkedBlockingDeque:
public class LinkedBlockingDeque<E>
    extends AbstractQueue<E>
    implements BlockingDeque<E>, java.io.Serializable

    - It is bounded when you specify the capacity
    - It is unbounded when we don't specify the capacity

 */
public class H_LinkedBlockingDeque {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(2);// bounded
        boolean add = blockingQueue.add(120);
        blockingQueue.add(60);
//        blockingQueue.add(45); //throws IllegalStateException queue is full
        boolean offer = blockingQueue.offer(10);// immediately return false if queue is full
        try {
            blockingQueue.offer(10, 1, TimeUnit.SECONDS);// return false afer waiting for specified time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       /* try {
            blockingQueue.put(10); // wait untill slot available in the queue
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(" Insertion Done");

        BlockingQueue<Integer> blockingQueue2 = new LinkedBlockingQueue<>(2);
        System.out.println("removed"+blockingQueue2.remove(10));//remove if present otherwise return false,

        blockingQueue2.add(10);
        blockingQueue2.add(10);
        System.out.println(blockingQueue2);
        System.out.println("removed"+blockingQueue2.remove(10)); // if multiple matches remove the first occurence
        System.out.println(blockingQueue2);

        Integer poll = blockingQueue2.poll();// remove element from head, return element, if queue is empty return null
        try {
            blockingQueue2.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Integer take = blockingQueue2.take(); //remove the element and return, if queue is empty wait untill someone insert value into queue
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Iterator<Integer> iterator = blockingQueue2.iterator();

        //PriorityBlockingQueue, LinkedBlockingDeque is also similar, just they have some additional methods
        LinkedBlockingDeque deque1= new LinkedBlockingDeque<>(); //unbounded
        LinkedBlockingDeque deque2= new LinkedBlockingDeque<>(5);//bounded

        PriorityBlockingQueue pQueue= new PriorityBlockingQueue();
        PriorityBlockingQueue pQueue2= new PriorityBlockingQueue(2);

        //Internally it use Array
        ArrayBlockingQueue arrayBlockingQueue= new ArrayBlockingQueue(10); //supports only bounded

    }
}

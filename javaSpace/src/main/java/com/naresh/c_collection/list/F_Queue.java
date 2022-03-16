package com.naresh.c_collection.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
public interface Queue<E> extends Collection<E>

Queue:
    -- FIFO/LILO
    --Two TYpes
        - bounded queue(fixed size) (these are present in java.util.concurrent) Ex: BlockingQueue
        - unbounded queue (java.util)
    -- Two implementations of Queue
        - LinkedList
        - PriorityQueue
These two are not implementing Deque
***************************************************
Priority Queue:
public class PriorityQueue<E> extends AbstractQueue<E>
    implements java.io.Serializable

    - Though queue follows FIFO, some time we need to server based on the priority
    - Elements in the queue are stored in natural order if no comparator is passed to the constructor
    - It doesn't permit null
    - It is unbounded
    - It is not thread safe, we can use PriorityBlockingQueue for thread safety

 */
public class F_Queue {
    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.add(10);
        queue1.add(5);
        queue1.add(3);
        Iterator<Integer> iterator1 = queue1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        System.out.println("peek"+queue1.peek());
        System.out.println("poll"+queue1.poll());
        System.out.println("remove"+queue1.remove());
        queue1.add(25);
        queue1.add(55);
        System.out.println(queue1);


        //**************************8
        System.out.println("Priority Queue:");
        //in unbounded queue, remove & poll are the same
        PriorityQueue<Integer> queue = new PriorityQueue<>();//data will be stored in natural order while inserting
//        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.add(10);
        queue.add(6);
        queue.add(3);
        queue.add(8);
        queue.offer(15); // in unbounded queue, add & offer are same

        //iterating
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("peek:" + queue.peek()); //3 has the highest priority
        System.out.println("removed:" + queue.remove());//it will throw NoSuchElementException if queue is empty
        System.out.println(" remove poll:" + queue.poll());

        System.out.println(queue);
    }
}

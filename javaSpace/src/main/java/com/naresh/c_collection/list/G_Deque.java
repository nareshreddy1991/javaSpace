package com.naresh.c_collection.list;

import java.util.ArrayDeque;
import java.util.Deque;
/*
Deque:
public interface Deque<E> extends Queue<E>
    - It is called Double ended queue
    - addition and removal can be performed at both the ends
    - It can be used as queue(FIFO) or stack(FILO)

 public class ArrayDeque<E> extends AbstractCollection<E>
                           implements Deque<E>, Cloneable, Serializable
ArrayDeque:
    -- it internally use array, initial capacity is 16
    -- unbounded
    -- not thread safe
    -- no null element allowed
    -- Faster than stack when used as stack
    -- Faster than LinkedList when used as queue
 */
public class G_Deque {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(10);//add at tail
        deque.add(20);
        deque.addFirst(25);
        deque.addLast(65);
        System.out.println(deque);
        System.out.println("peek"+deque.peekFirst());
        System.out.println("peek"+deque.peekLast());
        System.out.println("pool"+deque.pollFirst());
        System.out.println("pool"+deque.pollLast());
        System.out.println(deque);
        deque.offer(1);//add at tail
        deque.offerFirst(5);
        System.out.println(deque);
        deque.remove();//remove the head TODO what? its a FILO
        System.out.println(deque);
        deque.clear();
        deque.poll();// this will not throw
        deque.remove();//throws NoSuchEE
    }
}

package com.naresh.c_collection.list;

import java.util.LinkedList;

/*
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable

    --It is not implementing RandomAccess, so we can't get elements based on positions
    -- it is implemented by  duble linked list( that internally use Deque)
Why LinkedList implements Deque?
 Ans)As the JavaDocs states:
These operations allow linked lists to be used as a stack, queue, or double-ended queue.
The List interface is just a List i.e. you can add or remove. So a basic implementation of the List interface has to just provide those simple methods e.g. ArrayList.
The Deque interface is the double ended Queue and iava's LinkedList IS-A Double Ended Queue.
 */
public class D_LinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList= new LinkedList<>();
        linkedList.add(10);
        linkedList.get(5);// it internally iterate the linkedlist and get the result
        linkedList.addFirst(10);
        linkedList.addLast(10);
        linkedList.getFirst();
        linkedList.getLast();

        //we are able to add/remove both ends so we its a double linked list


           }
}

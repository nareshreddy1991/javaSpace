package com.naresh.h_datastructures.d_queue;

import java.util.stream.Stream;

/*
Queue:
    - FILO
    - Linear ds
Has following operations:
    - enqueue( inserting) at rear
    - deque (deletion) at front
    - front
    - rear
Applications of Queue:
 1) When a resource is shared among multiple consumers. Examples include CPU scheduling, Disk Scheduling.
 2) When data is transferred asynchronously (data not necessarily received at same rate as sent) between two processes. Examples include IO Buffers, pipes, file IO, etc.

Implementations:
    - Using Array
    - Using LInked List
    - Using Circular Linked list(need only open pointer)


            +-----------------------------+
enque(rear) |   |   |   |   |   |   |     |  deque(front) <--(removing point)
            +-----------------------------+

 */
//
public class A_Queue {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
        Stream.iterate(0, i -> i < 5, i -> ++i)
                .forEach(e -> queue.enqueue(e));
        System.out.println("front:" + queue.front + "/rear:" + queue.rear+"/size:"+ queue.size);
        Stream.iterate(0, i -> i < 5, i -> ++i)
                .forEach(e -> queue.deque());
        System.out.println("front:" + queue.front + "/rear:" + queue.rear+"/size:"+ queue.size);
        queue.enqueue(6);
        System.out.println("front:" + queue.front + "/rear:" + queue.rear+"/size:"+ queue.size);
        System.out.println(queue.deque());
        System.out.println("front:" + queue.front + "/rear:" + queue.rear+"/size:"+ queue.size);
        queue.enqueue(7);
        System.out.println("front:" + queue.front + "/rear:" + queue.rear+"/size:"+ queue.size);
        System.out.println(queue.deque());
        System.out.println("front:" + queue.front + "/rear:" + queue.rear+"/size:"+ queue.size);
    }
}

/*
Dis Adv:
If the queue has a large number of enqueue and dequeue operations, at some point (in case of linear increment of front and rear indexes)
we may not be able to insert elements in the queue even if the queue is empty (this problem is avoided by using circular queue)
 */
class Queue {
    int rear, front, size, capacity;
    int[] array;

    public Queue(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        rear = -1;
        front = 0;
        size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        array[rear] = data;
        size++;
    }

    public int deque() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int data = array[front];
        front = (front + 1) % capacity;
        size--;
        return data;
    }
}
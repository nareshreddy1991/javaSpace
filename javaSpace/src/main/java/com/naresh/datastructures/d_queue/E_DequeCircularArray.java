package com.naresh.datastructures.d_queue;

import java.util.Arrays;
import java.util.stream.Stream;

/*
I tried below approach that didn't work:
   +-----------------------------+
   |             |                |
   +-----------------------------+
                 | - rear & front(initially)
    +-----------------------------+
   |          5 5|1 5 2           |
   +-----------------------------+
           <--r|       |f-->(when full go to start of the queue)

Current approach:
   +-----------------------------+
   |5             |             1 |
   +-----------------------------+
   |front(initial 0)            |rear(initail-capacity-1)
 */
public class E_DequeCircularArray {
    public static void main(String[] args) {
        DequeCircularArray deque = new DequeCircularArray(10);
        Stream.iterate(0, i -> i < 5, i -> ++i)
                .forEach(e -> deque.addFront(e));
       /* Stream.iterate(0, i -> i < 5, i -> ++i)
                .forEach(e -> deque.addLast(e));
        deque.printAll();//0 1 2 3 4 4 3 2 1 0*/
        Stream.iterate(0, i -> i < 3, i -> ++i)
                .forEach(e -> System.out.println("removed:" + deque.removeFirst()));
        deque.printAll();//this will print all elements in the array irrespective of front/rear positon
        Stream.iterate(0, i -> i < 4, i -> ++i)
                .forEach(e -> System.out.println("removed:" + deque.removeLast()));
        deque.removeFirst();
    }
}

class DequeCircularArray {
    int front, rear, size, capacity;
    int[] array;

    DequeCircularArray(int capacity) {
        front = 0;
        rear = capacity - 1;
        size = 0;
        this.capacity = capacity;
        array = new int[capacity];
    }

    public void addFront(int data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        array[front] = data;
        if (front == capacity - 1)
            front = 0;
        else
            front++;
        size++;
    }

    public void addLast(int data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        array[rear] = data;
        if (rear == 0)
            rear = capacity - 1;
        else
            rear--;
        size++;
    }

    public int removeFirst() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        if (front == 0) {
            front = capacity - 1;
        } else {
            front--;
        }
        int data = array[front];//first we need to decrement & get the value othewise, it will get next position default value
        size--;
        return data;
    }

    public int removeLast() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        if (rear == capacity - 1) {
            rear = 0;
        } else {
            rear++;
        }
        int data = array[rear];
        size--;
        return data;
    }

    public int getFirst() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        if (front == 0)
            front = capacity - 1;
        else
            front--;
        return array[front];
    }

    public int getLast() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        if (rear == capacity - 1)
            rear = 0;
        else
            rear++;
        return array[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void printAll() {
        System.out.println("printing");
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }
}

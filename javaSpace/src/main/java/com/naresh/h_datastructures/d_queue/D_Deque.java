package com.naresh.h_datastructures.d_queue;

/*
Deque:
    - double ended queue
    - insertion/deletion can happen on both the ends
Operation:
insertFront(): Adds an item at the front of Deque.
insertLast(): Adds an item at the rear of Deque.
deleteFront(): Deletes an item from front of Deque.
deleteLast(): Deletes an item from rear of Deque.

In addition to above operations, following operations are also supported
getFront(): Gets the front item from queue.
getRear(): Gets the last item from queue.
isEmpty(): Checks whether Deque is empty or not.
isFull(): Checks whether Deque is full or not.

Applications of Deque:
Since Deque supports both stack and queue operations, it can be used as both.
TODO The Deque data structure supports clockwise and anticlockwise rotations in O(1) time which can be useful in certain applications.

Implementation:
A Deque can be implemented either using a
    doubly linked list or
    circular array. In both implementation, we can implement all operations in O(1) time.
 */
public class D_Deque {
    public static void main(String[] args) {
        DequeLinkedList<Integer> deque = new DequeLinkedList();
        deque.deleteFront();
        deque.deleteLast();
        deque.addFront(10);
        deque.addFront(20);
        deque.addFront(30);
        deque.addFront(40);
        deque.printAll();
        deque.deleteLast();
        deque.deleteLast();
        deque.printAll();
        deque.addLast(50);
        deque.addLast(60);
        deque.addLast(70);
        deque.printAll();
        deque.deleteFront();
        deque.deleteFront();
        deque.printAll();

    }
}

class DequeLinkedList<T> {
    Node head, tail;

    class Node {//child class can use parent T
        T data;
        Node next;
        Node previous;

        public Node(T data) {
            this.data = data;
        }
    }

    public void addFront(T data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
            return;
        }
        node.next = head;
        head.previous = node;
        head = node;
    }

    public void addLast(T data) {
        Node node = new Node(data);
        if (tail == null) {
            tail = head = node;
            return;
        }
        node.previous = tail;
        tail.next = node;
        tail = node;
    }

    public T deleteFront() {
        if (head == null) {
            System.out.println("queue is empty");
            return null;
        }
        T data = head.data;
        head = head.next;
        if (head != null) head.previous = null;
        else tail = null;
        return data;
    }

    public T deleteLast() {
        if (tail == null) {
            System.out.println("queue is empty");
            return null;
        }
        T data = tail.data;
        tail = tail.previous;
        if (tail != null) tail.next = null;
        else head = null;
        return data;
    }

    public boolean isEmpty() {
        return head == null ? true : false;
    }

    public boolean isFull() {
        return false;
    }

    public void printAll() {
        System.out.println("\nprint:");
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

}
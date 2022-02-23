package com.naresh.datastructures.d_queue;

import java.util.stream.Stream;

public class B_QueueWithLinkedList {
    public static void main(String[] args) {
        QueueLinkedList queue = new QueueLinkedList();
        Stream.iterate(0, i -> i < 5, i -> ++i)
                .forEach(e -> queue.enqueue(e));
        System.out.println("front:" + queue.front.data + "/rear:" + queue.rear.data + "/size:" + queue.size);
        Stream.iterate(0, i -> i < 5, i -> ++i)
                .forEach(e -> System.out.println(queue.deque()));
    }
}

class QueueLinkedList {
    int size;
    Node rear, front;

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public QueueLinkedList() {
        this.size = 0;
    }

    public void enqueue(int data) {
        Node node = new Node(data);
        if (rear == null) {
            rear = node;
            front = node;
            size++;
            return;
        }
     /* in this approach front is pointing old node but its next is null(insert at head)
       node1 ---> node2--nxt--> node3(next is null)
          rear|                       | front( since node3 next is null, you can't remove & point it to node2)
      node.next = rear;
        rear = node;*/

        /* we need to make sure front is pointing old node & its next has value(insert at tail)
                                    node1-----nxt------>node2
                                      |front            |rear
         */
        rear.next = node;
        rear = node;
        size++;
    }

    public int deque() {
        if (front == null) {
            System.out.println("queue is empty");
        }
        size--;
        int data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }
}

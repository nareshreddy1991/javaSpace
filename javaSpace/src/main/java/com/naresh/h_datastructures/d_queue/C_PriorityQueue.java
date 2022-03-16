package com.naresh.h_datastructures.d_queue;

/*
Priority Queue:
    -- elements in the queue follow the priority, so elements will be in sorted order.

Types of Priority Queue:
Min Priority Queue: In min priority Queue minimum number of value gets the highest priority and lowest number of element gets the highest priority.
Max Priority Queue: Max priority Queue is where  maximum number value gets the highest priority and minimum number of value gets the minimum priority.

Priority queue can be implemented using the following data structures:
Arrays
Linked list
Heap data structure
Binary search tree

Priority queue implementations by array:
    - using sorted array - insertion O(n), deletion O(1)
    - using unsorted array-insertion O(1), deletion O(n)

 */
public class C_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueueLinkedList queue = new PriorityQueueLinkedList();
        queue.push(new PriorityQueueLinkedList.Node(6, 6));
        queue.push(new PriorityQueueLinkedList.Node(7, 7));
        queue.push(new PriorityQueueLinkedList.Node(4, 4));
        queue.push(new PriorityQueueLinkedList.Node(0, 0));
        queue.push(new PriorityQueueLinkedList.Node(2, 2));
        queue.push(new PriorityQueueLinkedList.Node(9, 9));
        queue.printAll();
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.printAll();
        queue.push(new PriorityQueueLinkedList.Node(9, 9));
        queue.push(new PriorityQueueLinkedList.Node(10, 10));
        queue.push(new PriorityQueueLinkedList.Node(9, 9));
        queue.push(new PriorityQueueLinkedList.Node(0, 0));
        queue.printAll();
    }
}

class PriorityQueueLinkedList {
    Node head;

    static class Node {
        int data;
        int priority;
        Node next;

        public Node(int data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    public void push(Node node) {
        if (head == null) {
            head = node;
            return;
        }
        if (node.priority >= head.priority) {//  6 5 4 2 | 5
            node.next = head;
            head = node;
        } else {
         /* TODO imp: here we are using extra previous variable
         Ex: 6 5 4 2 | 3
                   |curr at 2
          Node curr = head.next;
            Node previous = head;
            while (curr != null && node.priority < curr.priority) {
                previous = curr;
                curr = curr.next;
            }
            previous.next = node;
            node.next = curr;*/
          /*  no need extra variable
            Ex: 6 5 4 2 | 3
                    |curr at 4 */
            Node curr = head;
            while (curr.next != null && node.priority < curr.next.priority) {
                curr = curr.next;
            }
            node.next = curr.next;
            curr.next = node;
        }
    }

    public int pop() {
        if (head == null) {
            System.out.println("Queue is empty");
            return -1;
        }
        int data = head.data;
        head = head.next;
        return data;
    }

    public int peek() {
        if (head == null) {
            System.out.println("Queue is empty");
            return -1;
        }
        int data = head.data;
        return data;
    }

    public void printAll() {
        System.out.println("printing");
        Node curr = this.head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
        System.out.println("end of print");
    }
}

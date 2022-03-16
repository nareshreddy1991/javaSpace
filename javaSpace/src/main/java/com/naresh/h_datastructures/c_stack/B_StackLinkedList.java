package com.naresh.h_datastructures.c_stack;

/*
StackLinkedList:
    - Dynamic size
 */
public class B_StackLinkedList {
    public static void main(String[] args) {
        StackLinkedList stack = new StackLinkedList();
//        int pop = stack.pop();
        stack.push(10);
        stack.push(20);
        stack.push(31);
        stack.push(32);
        stack.push(33);
        stack.print();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        stack.print();
    }
}

class StackLinkedList {
    Node head;

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void push(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public int pop() {
        if (head == null) {
            System.out.println("stack is empty");
            return -1;
        }
        int data = head.data;
        Node oldHead = head;//this is to free the memory of head
        head = head.next;
        oldHead.next = null;
        return data;
    }

    public int peek() {
        if (head == null) {
            System.out.println("stack is empty");
            return -1;
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null ? true : false;
    }

    public void print() {
        System.out.println("print");
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
        System.out.println("print end");
    }

}

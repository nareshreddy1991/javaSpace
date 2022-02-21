package com.naresh.datastructures.b_linkedlist;

public class D_CircularDoubleLinkedList {
    public static void main(String[] args) {

        CircularDoubleLinkedList linkedList = new CircularDoubleLinkedList();
        linkedList.add("10");
        linkedList.add("20");
        linkedList.add("30");
        linkedList.addAtStart("3");
        linkedList.addAtStart("5");
        linkedList.printAll();
        linkedList.pop();
        linkedList.pop();
        linkedList.removeAtBegin();
        linkedList.printAll();
    }
}

class CircularDoubleLinkedList {
    Node last;

    class Node {
        String data;
        Node previous;
        Node next;

        public Node(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data='" + data + '\'' +
                    ", previous=" + previous +
                    ", next=" + next +
                    '}';
        }
    }

    public void add(String data) {
        Node node = new Node(data);
        if (last == null) {
            last = node;
            node.next = node;
            node.previous = node;
            return;
        }
        Node first = last.next;
        node.next = first;
        node.previous = last;
        first.previous = node;
        last.next = node;
        last = node;
    }

    public void addAtStart(String data) {
        Node node = new Node(data);
        if (last == null) {
            last = node;
            node.next = node;
            node.previous = node;
            return;
        }
        node.next = last.next;
        node.previous = last;
        last.next.previous = node;
        last.next = node;
    }

    public void pop() {
        if (last.next == last) {
            last = null;
            return;
        }
        last.next.previous = last.previous;
        last.previous.next = last.next;
        last = last.previous;
    }

    public void removeAtBegin() {
        if (last.next == last)
            last = null;
        last.next = last.next.next;
        last.next.previous = last;
    }


    public void printAll() {
        System.out.println("head to tail");
        if (last == null) return;
        Node curr = last.next;
        do {
            System.out.println(curr.data);
            curr = curr.next;
        } while (curr != last.next);
        System.out.println("tail to head");
        curr = last;
        int i = 0;
        do {
            System.out.println(curr.data);
            curr = curr.previous;
            i++;
        } while (curr != last); //TODO :)
    }
}

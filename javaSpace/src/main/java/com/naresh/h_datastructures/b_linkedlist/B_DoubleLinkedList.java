package com.naresh.h_datastructures.b_linkedlist;

/*
Adv:
    - can travel in both direction
    - can delete nodes easily because we have access to previous nodes
    - we can implement various data strus
 */
public class B_DoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList dll = new DoubleLinkedList();
        dll.add("10");
        dll.add("20");
        dll.add("30");
        dll.add("40", 3);
        dll.add("50");
        dll.printAll();
        dll.pop();
        dll.remove(0);
        dll.remove("20");
        dll.printAll();

    }
}

class DoubleLinkedList {
    Node head;

    class Node {
        private String data;
        Node next;
        Node previous;

        public Node(String data) {
            this.data = data;
        }
    }

    public void add(String data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            return;
        }
        Node curr = head;
        //TODO curr.next!=null - current element is last position, curr is pointing to last element
        //TODO curr!=null - current element after the last element, curr is null
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
        node.previous = curr;
    }

    public void add(String data, int position) {
        Node node = new Node(data);
        if (position == 0) {
            node.next = head;
            head.previous = node;
            head = node;
            return;
        }
        Node curr = head;
        for (int i = 0; i < position - 1; i++) {
            curr = curr.next;
        }
        if (curr == null) return;
        node.next = curr.next;
        if (node.next != null)//if last element
            node.next.previous = node;
        curr.next = node;
        node.previous = curr;
    }

    public void pop() {
        if (head.next == null) head = null;
        Node curr = head;
        Node previous = null;
        while (curr.next != null) {
            previous = curr;
            curr = curr.next;
        }
        previous.next = null;
    }

    public void remove(int position) {
        if (position == 0) {
            head = head.next;
            head.previous = null;
            return;
        }
        Node curr = head;
        for (int i = 0; i < position - 1; i++) {
            curr = curr.next;
        }
        if (curr == null || curr.next == null) return;
        curr.next = curr.next.next;
        curr.next.previous = curr;
    }

    public void remove(String data) {
        if (data.equals(head.data)) {
            head = head.next;
            head.previous = null;
            return;
        }
        Node curr = head.next;
        Node previous = head;
        while (curr != null) {
            if (data.equals(curr.data)) {
                previous.next = curr.next;
                if (curr.next != null)
                    curr.next.previous = previous;
                break;
            }
            previous = curr;
            curr = curr.next;
        }
    }

    public void printAll() {
        System.out.println("From head to last");
        Node temp = head;
        Node last = null;
        while (temp != null) {
            System.out.println(temp.data);
            last = temp;
            temp = temp.next;
        }
        System.out.println("Tail to head");
        temp = last;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.previous;
        }
    }

}
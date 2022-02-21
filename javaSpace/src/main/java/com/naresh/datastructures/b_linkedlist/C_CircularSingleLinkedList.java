package com.naresh.datastructures.b_linkedlist;

/*
Circular Linked List:
    - All nodes are connected in a circle
    - NO null at the end
    - It can be implemented by single linked list & double linked list
Advantages:
    - Any point can be starting point, we need to stop once we visit the same node again
    - Useful to implement Queue, when Queue is implemented using Arrays we need to maintain two pointers ie front, rear but with circular linkedlist
we can have single pointer at the end, we can get the previous node by last.next(in single linked list)
    - Used in round robinson kind of frameworks
    - Useful in implementing adv data structure like fibinocci heap
 */

/*
CircularSingleLinkedList:
    - take the pointer to the last node, so you can access first node by last.next
    - In this case inserting node at start or end it will take constant time O(1)
    - NO need to traverse the list
 */
public class C_CircularSingleLinkedList {
    public static void main(String[] args) {
        CircularSingleLinkedList dll = new CircularSingleLinkedList();
        dll.add("10");
        dll.add("20");
        dll.add("30");
//        dll.add("40", 3);
        dll.add("50");
        dll.add("60");
        dll.printAll();
        dll.pop();
        dll.pop();
        dll.remove("20");
        dll.remove("10");
        dll.printAll();
    }
}

class CircularSingleLinkedList {
    Node last;

    class Node {
        String data;
        Node next;

        public Node(String data) {
            this.data = data;
        }
    }

    public void add(String data) {
        Node node = new Node(data);
        if (last == null) {
            last = node;
            last.next = node;
            return;
        }
        Node first = last.next;
        last.next = node;
        node.next = first;
        last = node;
    }

    public void add(String data, int position) {
        Node node = new Node(data);
        if (position == 0) {
            node.next = last.next;
            last.next = node;
            return;
        }
        Node curr = last;
        for (int i = 0; i < position; i++) {//go to previous node of the position
            curr = curr.next;
        }
        node.next = curr.next;
        curr.next = node;
    }

    public void pop() {
        if (last == last.next) {
            last = null;
            return;
        }
        Node curr = last;
        while (last != curr.next) {//stop previous node of last
            curr = curr.next;
        }
        curr.next = last.next;
        last = curr;
    }

    public void remove(String data) {
        Node curr = last.next;
        Node previous = last;
        while (curr != null) {
            if (curr.data.equals(data)) {
                previous.next = curr.next;
                if (last == curr) {
                    last = previous;
                }
                break;
            }
            previous=curr;
            curr = curr.next;
        }
    }

    public void printAll() {
        System.out.println("data");
      /*  Node curr = last.next;
        Node lastBkp = null;
        boolean a = true;
        while (curr != lastBkp) {
            if (a) {
                lastBkp = last.next;
                a = false;
            }
            System.out.println(curr.data);
            curr = curr.next;
        }*/

        Node curr = last.next;
        do {
            System.out.println(curr.data);
            curr = curr.next;
        } while (curr != last.next);
    }
}

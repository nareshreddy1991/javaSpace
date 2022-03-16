package com.naresh.h_datastructures.b_linkedlist;

/*
LinkedList:
    - It is a linear data structure
    - Can store any kind of data
    - Every node stores data part & next node reference
Advantages:
    - Insertion & deletion are so fast
    - Dynamic sizing
Limitations:
    - No Random Access, we need iterate from head to element for all operations
    - Extra space for pointer/reference




 */
public class A_SingleLinkedList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40, 2);
        linkedList.printAll();
        System.out.println("size:" + linkedList.size());
        System.out.println("size:" + linkedList.size2());
        System.out.println("after removing");
//        linkedList.remove(20);
//        linkedList.remove(10);
//        linkedList.remove(30);
//        linkedList.remove(40);
        linkedList.remove(4);
        linkedList.printAll();
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
    }

    class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }

    //add element at end, complexity O(n), we can reduce the complexity O(1) if we maintain one more pointer at the end
    public void add(Integer data) {
        Node node = new Node(data);
        if (head == null) {//adding element at the beginning, O(1)
            head = node;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
    }

    public void remove(Integer data) {
        Node curr = head;
        Node previous = null;
        while (curr != null) {
            if (data.compareTo(curr.data) == 0) {
                if (previous == null) {
                    head = curr.next;
                    break;
                }
                previous.next = curr.next;
                break;
            }
            previous = curr;
            curr = curr.next;
        }
    }

    public void remove(int position) {
        if (position == 0) {
            head = head.next;
            return;
        }
        Node curr = head;
        for (int i = 0; i < position - 1 && curr != null; i++) {//go till previous node only
            curr = head.next;
        }
        if (curr == null || curr.next == null)//position is out of range
            return;
        curr.next = curr.next.next;
    }

    //adding element at give position, O(n) --my poor logic
    /*public void add(Integer data, int position) {
        if (position >= size())//finding is O(n)
            throw new IndexOutOfBoundsException();
        Node node = new Node(data);

        int i = 0;
        Node curr = head;
        Node previous = null;
        while (curr != null) {
            if (position == i) {
                if (i == 0) {
                    head = node;
                    node.next = curr;
                } else {
                    node.next = curr;
                    previous.next = node;
                }
                break;
            }
            previous = curr;
            curr = curr.next;
            i++;
        }
    }*/

    public void add(Integer data, int position) {
        Node node = new Node(data);
        if (position == 0) {
            node.next = head;
            head = node;
            return;
        }
//        Node curr = head;
//        for (int i = 0; i < position - 1 && curr != null; i++) {
//            curr = curr.next;
//        }
        Node curr = head.next;//improved since head is already handled
        for (int i = 0; i < position - 2 && curr != null; i++) {
            curr = curr.next;
        }
        if (curr == null) {//if position is out of range
            return;
        }
        node.next = curr.next;
        curr.next = node;
    }

    //O(1)
    public void addAfter(Node node, Integer data) {
        if (node != null) {
            Node newNod = new Node(data);
            newNod.next = node.next;
            node.next = newNod;
        }
    }

    public void printAll() {
        Node next = head;
        while (next != null) {
            System.out.println(next.data);
            next = next.next;
        }
    }

    public int size() {
        Node next = head;
        int count = 0;
        while (next != null) {
            count++;
            next = next.next;
        }
        return count;
    }

    public int size2() {
        return sizeRecursive(head);
    }

    //what is the complexity of recursion?
    public int sizeRecursive(Node node) {
        if (node == null)
            return 0;
        return 1 + sizeRecursive(node.next);

    }
}
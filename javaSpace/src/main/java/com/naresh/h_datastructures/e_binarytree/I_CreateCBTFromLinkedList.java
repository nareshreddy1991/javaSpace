package com.naresh.h_datastructures.e_binarytree;

import java.util.LinkedList;
import java.util.Queue;

/*
Create Complete BT from single linked list
Since Complete binary tree is filled from left to right, we can construct CMT from single array/linkedList/level order
Time Complexity: Time complexity of the above solution is O(n) where n is the number of nodes.


 */
public class I_CreateCBTFromLinkedList {
    public static void main(String[] args) {
        LinkedList1 linkedList1 = new LinkedList1();
        LinkedList1.Node head = new LinkedList1().new Node(1);
        head.next = new LinkedList1().new Node(2);
        head.next.next = new LinkedList1().new Node(3);
        head.next.next.next = new LinkedList1().new Node(4);
        head.next.next.next.next = new LinkedList1().new Node(5);
        head.next.next.next.next.next = new LinkedList1().new Node(6);
        head.next.next.next.next.next.next = new LinkedList1().new Node(7);
        linkedList1.head = head;

        CBT1 cbt1 = new CBT1();
        CBT1.Node cbt = cbt1.convertLLToCBT(head);
        cbt1.inOrder(cbt);
    }
}

class LinkedList1 {
    Node head;

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}

class CBT1 {
    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node convertLLToCBT(LinkedList1.Node head) {
        Node node = new Node(head.data);
        LinkedList1.Node curr = head;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        curr = curr.next;//advance to next position
        while (curr != null) {//take two elements from linked list and populate left & right
            Node parent = queue.poll();
            parent.left = new Node(curr.data);
            curr = curr.next;
            queue.add(parent.left);
            if (curr != null) {
                parent.right = new Node(curr.data);
                curr = curr.next;
                queue.add(parent.right);
            }
        }
        return node;
    }


    public void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
}

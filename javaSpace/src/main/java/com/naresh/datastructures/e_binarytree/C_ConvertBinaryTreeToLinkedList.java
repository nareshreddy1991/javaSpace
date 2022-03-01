package com.naresh.datastructures.e_binarytree;

import java.util.ArrayList;
import java.util.List;

//convert given binary tree to DoubleLinkedList
public class C_ConvertBinaryTreeToLinkedList {
    static List<LinkedListNode> list = new ArrayList<>();


    public void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        list.add(new LinkedListNode(node.data));
        inOrder(node.right);
    }

    LinkedListNode head;
    LinkedListNode tail;

    public void inOrder2(Node node) {
        if (node == null)
            return;
        inOrder2(node.left);
        LinkedListNode curr = new LinkedListNode(node.data);
      /*  if (head == null) {
            head = curr;
            tail = head;
        } else {
            tail.next = curr;
            curr.previous = tail;
            tail = curr;
        }*/
        //or below logic
        if (head == null)
            head = curr;
        curr.previous = tail;
        if (tail != null)
            tail.next = curr;
        tail = curr;
        inOrder2(node.right);
    }

    public LinkedList prepareLinkedList(List<LinkedListNode> list) {
        LinkedListNode head = list.get(0);
        LinkedListNode previous = head;
        for (int i = 1; i < list.size(); i++) {
            LinkedListNode curr = list.get(i);
            previous.next = curr;
            curr.previous = previous;
            previous = curr;
        }
        return new LinkedList(head);
    }

    public static void main(String[] args) {
        /*
                1
               / \
              2    3
            /  \
           4    5
         */
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        C_ConvertBinaryTreeToLinkedList obj = new C_ConvertBinaryTreeToLinkedList();
        System.out.println("approach1: inorder with list");
        obj.inOrder(tree.root);
        LinkedList linkedList = obj.prepareLinkedList(list);
        LinkedListNode curr = linkedList.head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
        System.out.println("approach1: inorder with out list");
        obj.inOrder2(tree.root);
        linkedList = new LinkedList(obj.head);
        curr = linkedList.head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
}

class LinkedList {
    LinkedListNode head;

    public LinkedList(LinkedListNode head) {
        this.head = head;
    }
}

class LinkedListNode {
    int data;
    LinkedListNode next;
    LinkedListNode previous;

    public LinkedListNode(int data) {
        this.data = data;
    }
}

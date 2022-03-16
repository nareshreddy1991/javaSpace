package com.naresh.h_datastructures.f_binarysearchtree;

public class G_ArrayToBalancedBST {
    public static void main(String[] args) {
        BST5 bst = new BST5();
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        BST5.Node root = bst.arrayToBalancedBST(array, 0, array.length - 1);
        System.out.println("inorder:root" + root.data);
        bst.inOrder(root);
        System.out.println("\npreorder:root:" + root.data);
        bst.preOrder(root);
    }
}

class LinkedList {
    LinkedNode root;

    class LinkedNode {
        int data;
        LinkedNode next;

        public LinkedNode(int data) {
            this.data = data;
        }
    }
}

class BST5 {
    Node root;

    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    /*
    Time complexity: O(n)
     */
    public Node arrayToBalancedBST(int[] array, int start, int end) {//end is inclusive
        if (start > end)
            return null;
        else if (start == end)
            return new Node(array[start]);
//        int rootPos = array.length / 2; //we cant use this because array size is constant
//        int rootPos = (end+1 - start) / 2; //  index5-index7=2/2=1 index is not valid for right sub tree
        int rootPos = end - (end - start) / 2;
        Node root = new Node(array[rootPos]);
        root.left = arrayToBalancedBST(array, start, rootPos - 1);
        root.right = arrayToBalancedBST(array, rootPos + 1, end);
        return root;
    }
/*
    public Node arrayToBalancedBST(LinkedList.LinkedNode head, LinkedList.LinkedNode start, LinkedList.LinkedNode end) {
        if (head == null)
            return null;
        int size = sizeOfLinkedList(head);
        start = head;
        LinkedList.LinkedNode curr = head;
        LinkedList.LinkedNode previous=null;
        for (int i = 0; i < size / 2; i++) {
            previous=curr;
            curr = curr.next;
        }
        Node root = new Node(curr.data);
        root.left = arrayToBalancedBST(head, start, previous);//curr is exclusive
        root.right = arrayToBalancedBST(head, curr.next, null);
    }*/

    private int sizeOfLinkedList(LinkedList.LinkedNode start, LinkedList.LinkedNode end) {
        int count = 0;
        LinkedList.LinkedNode curr = start;
        while (curr != null) {
            if (end != null && curr.data == end.data) {
                break;
            }
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void preOrder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
}

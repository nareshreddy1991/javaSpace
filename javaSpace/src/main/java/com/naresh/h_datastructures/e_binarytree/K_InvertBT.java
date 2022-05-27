package com.naresh.h_datastructures.e_binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class K_InvertBT {
    public static void main(String[] args) {
        /*
        1               1
       2  3   -->      3  2
      4 5 6 7           5  4

      4 2 3 1 3         3 1 5 2 4
               */
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);
        Node newTree = invertBT3(tree);
        inOrder(tree);
    }

    //This will crate a new BT in mirror
    public static Node invertBT(Node node) {
        if (node == null) return null;
        Node newNode = new Node(node.data);
        newNode.right = invertBT(node.left);
        newNode.left = invertBT(node.right);
        return newNode;
    }

    //invert the same tree
    public static Node invertBT2(Node node) {
        if (node == null) return node;
        Node left = invertBT(node.left);
        Node right = invertBT(node.right);

        node.left = right;
        node.right = left;
        return node;
    }

    //without using recursion
    //Travel in level order and reverse it
    public static Node invertBT3(Node node) {
        Queue<Node> queue = new LinkedList<>();
//        Stack stack=new Stack();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node parentNode = queue.poll();
            Node left = parentNode.left;
            Node right = parentNode.right;
            if (left != null)
                queue.add(left);
            if (right != null)
                queue.add(right);
            parentNode.left = right;
            parentNode.right = left;
        }
        return node;
    }


    public static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }
}

package com.naresh.h_datastructures.e_binarytree;

import java.util.Queue;

/*
Input :
                 1
               /   \
              2     3
             / \     \
            4   5     6
Output : 1 2 4

Input :
        1
      /   \
    2       3
      \
        4
          \
            5
             \
               6
Output :1 2 4 5 6
Recommended PracticeLeft View of Binary TreeTry It!
Method-1 (Using Recursion)
The left view co
 */
public class K_PrintLeftViewOfBT {
    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);
        tree.right.right.right = new Node(11);
        leftView(tree);
        System.out.println();
        rightView(tree);
    }

    public static void leftView(Node node) {
        Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node parent = queue.poll();
                if (i == 0) System.out.print(parent.data + " ");
                if (parent.left != null) {
                    queue.add(parent.left);
                }
                if (parent.right != null) {
                    queue.add(parent.right);
                }
            }
        }
    }

    public static void rightView(Node node) {
        Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {//travel all elements of that level and remove them and add their childs
                Node parent = queue.poll();
                if (i == size - 1) //TODO just change here
                    System.out.print(parent.data + " ");
                if (parent.left != null) {
                    queue.add(parent.left);
                }
                if (parent.right != null) {
                    queue.add(parent.right);
                }
            }
        }
    }

    //TODO top view of BT??
}

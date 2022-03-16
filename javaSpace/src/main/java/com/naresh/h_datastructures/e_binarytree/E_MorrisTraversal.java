package com.naresh.h_datastructures.e_binarytree;

/*
-no extra space is required
-no recursion
-it uses threaded binary trees
 */
public class E_MorrisTraversal {
    public static void main(String[] args) {

    }
}

class MorrisTraversal {
    Node root;

    class Node {
        int data;
        Node left, right;
    }

    public void morrisInOrder(Node root) {
        if (root == null)
            return;
        Node curr = null, previous = null;
        curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.println(curr.data);
                curr = curr.right;
            } else {
                previous = curr.left;
                if (previous.right != null
                        && previous.right != curr) {
                    previous = previous.right;
                }

                if (previous.right == null) {
                    previous.right = curr;
                    curr = curr.left;
                } else {
                    previous.right = null;
                    System.out.println(curr.right);
                    curr = curr.right;
                }
            }
        }
    }
}

package com.naresh.datastructures.e_binarytree;

/*
in Single Threaded BT: every leaf node(dont have any childs), right null chaild points to inorde successor.
in Double Threaded Bt: every leaf nodes null left child will point to inorder predessor & right null child will point to inorder successor.
 */
public class D_ThreadBinaryTree {
}

class ThreadedBinaryTree {
    Node root;

    class Node {
        int data;
        Node left, right;
        boolean leftThread, rightThread; //for single thread only rightThread is enough.
    }

    public Node inOrder(Node root) {
        if (root == null)
            return null;
        Node curr = root;
        while (curr != null) {
            curr = leftNode(root);
            if (curr != null)
                System.out.println(curr.data);
            if (curr.rightThread)
                curr = curr.right;
            else
                curr = leftNode(curr.right);
        }
        return root;
    }

    public Node leftNode(Node root) {
        if (root == null)
            return null;
        Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
}

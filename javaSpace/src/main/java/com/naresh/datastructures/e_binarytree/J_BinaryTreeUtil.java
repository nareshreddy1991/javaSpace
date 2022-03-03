package com.naresh.datastructures.e_binarytree;

public class J_BinaryTreeUtil {
    public static void main(String[] args) {

    }
}

class BT5 {
    Node root;

    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    //TODO how to find height with out recursion?
    public int height(Node node) {
        if (node == null) return 0;
        int lh = height(node.left);
        int rh = height(node.right);
        if (lh > rh) return lh + 1;
        else return rh + 1;
    }

    Node temp = null;

    public Node min(Node node) {
        if (node == null) return null;
        min(node.left);
        if (temp == null || temp.data > node.data)
            temp = node;
        min(node.right);
        return temp;
    }

    int count = 0;

    public int count(Node node) {
        if (node == null)
            return 0;
        count(node.left);
        count++;
        count(node.right);
        return count;
    }
}

package com.naresh.datastructures.f_binarysearchtree;

import java.util.Stack;

public class E_CreateBSTFromPreOrder3 {
    public static void main(String[] args) {
        BST3 bst = new BST3();
        int[] array = new int[]{10, 5, 1, 7, 40, 50};
        BST3.Node root = bst.convertPreOrderToBST(array);
        bst.preOrder(root);
    }
}

class BST3 {
    Node root;

    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node convertPreOrderToBST(int[] array) {
        Node root = new Node(array[0]);
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        for (int i = 1; i < array.length; i++) {
            Node temp = null;
            while (!stack.isEmpty() && array[i] > stack.peek().data) {
                temp = stack.pop();
            }
            if (temp != null) { // a[i] > stack top -- right
                temp.right = new Node(array[i]);
                stack.push(temp.right);
            } else {
                temp = stack.peek();
                temp.left = new Node(array[i]);
                stack.push(temp);
            }
        }
        return root;
    }

    public void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }
}

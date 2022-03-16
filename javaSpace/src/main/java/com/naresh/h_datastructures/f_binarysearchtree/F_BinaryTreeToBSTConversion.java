package com.naresh.h_datastructures.f_binarysearchtree;

import java.util.Arrays;
/*
step:1 travel inorder copy the data in array -- O(n)
Step 2: sort it - I used interl sort -- O(logn)
Step 3: travel inorder copy the data from array to tree -- O(n)

Total complexity:
Time Complexity: O(n logn). This is the complexity of the sorting algorithm which we are using after first in-order traversal, rest of the operations take place in linear time.
Auxiliary Space: O(n). Use of data structure ‘array’ to store in-order traversal.
 */
public class F_BinaryTreeToBSTConversion {
    public static void main(String[] args) {
        BST4 bst = new BST4();
        bst.root = new BST4.Node(10);
        bst.root.left = new BST4.Node(2);
        bst.root.right = new BST4.Node(7);
        bst.root.left.left = new BST4.Node(8);
        bst.root.left.right = new BST4.Node(4);

        int[] inOrder = new int[5];
        bst.inOrder(bst.root, inOrder);
        Arrays.stream(inOrder).forEach(System.out::print);
        Arrays.sort(inOrder);
        System.out.println("\n sort:");
        Arrays.stream(inOrder).forEach(System.out::print);
        bst.index = 0;
        bst.inOrderCopy(bst.root, inOrder);
        System.out.println("\nresult:");
        bst.printInOrder(bst.root);
    }
}

class BST4 {
    Node root;

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    int index = 0;

    public void inOrder(Node node, int[] array) {
        if (node == null)
            return;
        inOrder(node.left, array);
        array[index++] = node.data;
        inOrder(node.right, array);
    }

    public void inOrderCopy(Node node, int[] array) {
        if (node == null)
            return;
        inOrderCopy(node.left, array);
        node.data = array[index++];
        inOrderCopy(node.right, array);
    }

    public void printInOrder(Node node) {
        if (node == null)
            return;
        printInOrder(node.left);
        System.out.println(node.data + " ");
        printInOrder(node.right);
    }
}

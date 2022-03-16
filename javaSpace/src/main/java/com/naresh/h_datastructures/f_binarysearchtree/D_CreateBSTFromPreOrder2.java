package com.naresh.h_datastructures.f_binarysearchtree;


// Java program to construct BST from given preorder
// traversal

// A binary tree node

//Complexity O(n)

class Index {

    int index = 0;
}

public class D_CreateBSTFromPreOrder2 {

    class Node {

        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    Index index = new Index();

    // A recursive function to construct BST from pre[].
    // preIndex is used to keep track of index in pre[].
    Node constructTreeUtil(int pre[], Index preIndex, int key, int min, int max, int size) {

        // Base case
        if (preIndex.index >= size) {
            return null;
        }

        Node root = null;

        // If current element of pre[] is in range, then
        // only it is part of current subtree
        if (key > min && key < max) {

            // Allocate memory for root of this
            // subtree and increment *preIndex
            root = new Node(key);
            preIndex.index = preIndex.index + 1;

            if (preIndex.index < size) {

                // Construct the subtree under root
                // All nodes which are in range {min .. key}
                // will go in left subtree, and first such
                // node will be root of left subtree.
                root.left = constructTreeUtil(pre, preIndex, pre[preIndex.index], min, key, size);
            }
            if (preIndex.index < size) {
                // All nodes which are in range {key..max}
                // will go in right subtree, and first such
                // node will be root of right subtree.
                root.right = constructTreeUtil(pre, preIndex, pre[preIndex.index], key, max, size);
            }
        }

        return root;
    }

    // The main function to construct BST from given
    // preorder traversal. This function mainly uses
    // constructTreeUtil()
    Node constructTree(int pre[], int size) {
        int preIndex = 0;
        return constructTreeUtil(pre, index, pre[0], Integer.MIN_VALUE, Integer.MAX_VALUE, size);
    }

    // A utility function to print inorder traversal of a
    // Binary Tree
    void printPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    // Driver code
    public static void main(String[] args) {
        D_CreateBSTFromPreOrder2 tree = new D_CreateBSTFromPreOrder2();
        int pre[] = new int[]{10, 5, 1, 7, 40, 50};
        int size = pre.length;

        // Function call
        Node root = tree.constructTree(pre, size);
        System.out.println("Inorder traversal of the constructed tree is ");
        tree.printPreOrder(root);
    }
}

// This code has been contributed by Mayank Jaiswal


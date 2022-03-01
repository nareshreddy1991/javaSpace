package com.naresh.datastructures.f_binarysearchtree;


/*
BinarySearchTree:
   Binary Search Tree is a node-based binary tree data structure which has the following properties:

The left subtree of a node contains only nodes with keys lesser than the node’s key.
The right subtree of a node contains only nodes with keys greater than the node’s key.
The left and right subtree each must also be a binary search tree.
There must be no duplicate nodes.

- Searching element & finding min & max is easy, similar to binary search
            8
           / \
          5    10
         / \   / \
        3   6  9  11
 */
public class A_BinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new Node(8);
        tree.root.left = new Node(5);
        tree.root.right = new Node(10);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(6);

        Node search = search(tree.root, 11);
        System.out.println(search != null ? search.data : null);
        search = search(tree.root, 5);
        System.out.println(search != null ? search.data : null);
        System.out.println("inorder result");
        inOrder(tree.root);
        insertWithoutRecursion(tree.root, 2);
        System.out.println("inorder result");
        inOrder(tree.root);

    }

    public static Node search(Node node, int searchData) {//TODO in recursion we can return values
        if (node == null || searchData == node.data) return node;
//        if (searchData == node.data)
//            return node;
        else if (node.data < searchData) return search(node.right, searchData);
        return search(node.left, searchData);
    }

    //this method doesn't work if root is null
    //TODO since we are changing the tree structure we need to return the modified tree
    public static Node insert(Node node, int key) {
        if (node == null) return null;
        else if (node.data < key) {
            if (null == insert(node.right, key)) {
                node.right = new Node(key);
                return node;
            }
        } else if (null == insert(node.left, key)) {
            node.left = new Node(key);
        }
        return node;
    }

    public static Node insert2(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        } else if (node.data < key) {
            node.right = insert2(node.right, key);
        } else {
            node.left = insert2(node.left, key);
        }
        return node;
    }

    public static Node insertWithoutRecursion(Node node, int key) {
        Node newNode = new Node(key);
        if (node == null) {
            node = newNode;
            return node;
        }
        Node curr = node;
        Node left = null;
        Node right = null;
        while (curr != null) {
            left = right = null;
            if (curr.data < key) {//8<9 ,10<9
                right = curr;
                curr = curr.right;
            } else {
                left = curr;
                curr = curr.left;
            }
        }
        if (left != null) left.left = new Node(key);
        else right.right = new Node(key);
        return node;
    }

    //TODO improved
    public static Node insertWithoutRecursion2(Node node, int key) {
        Node newNode = new Node(key);
        if (node == null) {
            node = newNode;
            return node;
        }
        Node curr = node;
        Node previous = null;
        while (curr != null) {
            previous = curr;
            if (curr.data < key) {//8<9 ,10<9
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        if (previous.data < key) previous.right = new Node(key);
        else previous.left = new Node(key);
        return node;
    }

    public static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public int findMin(Node node) {
        int min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    public int findMax(Node node) {
        int max = node.data;
        while (node.right != null) {
            max = node.right.data;
            node = node.right;
        }
        return max;
    }

}

class BinarySearchTree {
    Node root;
}

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
    }
}




package com.naresh.h_datastructures.a_recursion;

public class A2_RecursionBinaryTree {
    public static void main(String[] args) {
        /*
                1
               / \
              2    3
            /  \
           4    5
         */
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
//        tree.root.left.right = new Node(5);

        System.out.println(
                "Preorder traversal of binary tree is ");
        tree.printPreorderSum();
        tree.printPreorderCount();

    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}

//Tree traversal using recursion
class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }


    void printPreorderSum() {
        System.out.println("Total sum:" + preOrderSum(root));
    }

    void printPreorderCount() {
        System.out.println("Total count:" + preOrderCount(root));
    }

    public void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    //Find sum of all elements
    //TODO the mess I have written
    public int preOrder(Node node, int sum) {
        if (node == null)
            return 0;
        System.out.println(node.data + " sum:" + sum);
        sum += node.data;
        sum += preOrder(node.left, sum);
        sum += preOrder(node.right, sum);
        return sum;
    }

    public int preOrderSum(Node node) {
        if (node == null)
            return 0;
        return node.data + preOrderSum(node.left) + preOrderSum(node.right);
    }

    //FInd the no of elements in BT
    // TODO the mess again
    public int preOrderCount(Node node, int count) {
        if (node == null)
            return 0;
        count++;
        preOrderCount(node.left, count);
        preOrderCount(node.right, count);
        return count;
    }

    //count no of elements in tree
    public int preOrderCount(Node node) {
        if (node == null)
            return 0;
        return 1 + preOrderCount(node.left) + preOrderCount(node.right);
    }
}

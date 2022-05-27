package com.naresh.h_datastructures.e_binarytree;

public class J_BinaryTreeUtil {
    public static void main(String[] args) {
        BT5 bt = new BT5();
        bt.root = new BT5().new Node(10);
        bt.root.left = new BT5().new Node(8);
        bt.root.right = new BT5().new Node(11);
        bt.root.left.left = new BT5().new Node(6);
        bt.root.left.right = new BT5().new Node(9);
        bt.root.left.left.left = new BT5().new Node(4);
        bt.root.left.left.left.right = new BT5().new Node(7);

        System.out.println("height:" + bt.height(bt.root));
        System.out.println("height2:" + bt.height2(bt.root));
        System.out.println("count:" + bt.count(bt.root));
        System.out.println("min:" + bt.min(bt.root).data);
        System.out.println("min in BST:" + bt.minInBST(bt.root).data);
        System.out.println("max in BST:" + bt.maxInBST(bt.root).data);
        System.out.println("min in BT:" + bt.findMinInBT(bt.root));
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

    public int height2(Node node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height2(node.left), height2(node.right));
    }

    //TODO not a good approach, since we are using ourter variables
    Node temp = null;

    public Node min(Node node) {
        if (node == null) return null;
        min(node.left);
        if (temp == null || temp.data > node.data)
            temp = node;
        min(node.right);
        return temp;
    }

    //Travel inoder/any order and find the Min(node.data, left.data, right.data)
    public int min2(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int left = min2(node.left);
        int right = min2(node.right);
        int result = node.data;
        if (left < result)
            result = left;
        if (right < result)
            result = right;
        return result;
    }

    //Travel inoder/any order and find the Min(node.data, left.data, right.data)
    public int max2(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int left = max2(node.left);
        int right = max2(node.right);
        int result = node.data;
        return Math.max(Math.max(left, right), result);
    }

    public Node minInBST(Node node) {
        if (node == null)
            return null;
        while (node.left != null)
            node = node.left;
        return node;
    }

    public Node maxInBST(Node node) {
        if (node == null)
            return null;
        while (node.right != null)
            node = node.right;
        return node;
    }

    //TODO find min/max element in BT
    //THe mess
    public int findMinInBT(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        int leftMin = findMinInBT(node.left);
        int rightMin = findMinInBT(node.right);
        return Math.min(leftMin, rightMin);

    }

    int count = 0;

    //TODO poor logic
    public int count(Node node) {
        if (node == null)
            return 0;
        count(node.left);
        count++;
        count(node.right);
        return count;
    }

    public int count2(Node node) {
        if (node == null)
            return 0;
        return 1 + count2(node.left) + count2(node.right);
    }

    public int sumOfAllElementsInBT(Node node) {
        if (node == null)
            return 0;
        return node.data + sumOfAllElementsInBT(node.left) + sumOfAllElementsInBT(node.right);
    }
}

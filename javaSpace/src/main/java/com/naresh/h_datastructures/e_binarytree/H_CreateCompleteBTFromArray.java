package com.naresh.h_datastructures.e_binarytree;

public class H_CreateCompleteBTFromArray {
    public static void main(String[] args) {
        CBT cbt = new CBT();
        int[] array = new int[]{1, 12, 9, 5, 6, 10};
//        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        CBT.Node completeBT = cbt.createCompleteBT(array, 0);
        cbt.inOrder(completeBT);
    }
}

class CBT {
    Node root;

    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    int index = 0;

    public Node createCompleteBT(int[] array, int index) {
        System.out.println("start of method");
        if (array == null || index >= array.length)
            return null;
        Node node = new Node(array[index]);
        node.left = createCompleteBT(array, 2 * index + 1);
        node.right = createCompleteBT(array, 2 * index + 2);
        index++;
        System.out.println("end of method");
        return node;
    }

    //TODO incomplete
    // 1 2 3 4 5 6
    public Node createCompleteBTWithoutRecursion(int[] array) {

        Node node = new Node(array[0]);
        Node previous = node;
        for (int i = 0; i < array.length; i++) {
            int l = array[2 * i + 1];
            if (l > array.length)
                node.left = new Node(l);
            int r = array[2 * i + 2];
            if (r > array.length)
                node.right = new Node(r);
        }
        return null;
    }

    public void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
}

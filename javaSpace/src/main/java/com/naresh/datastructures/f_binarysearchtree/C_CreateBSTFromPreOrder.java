package com.naresh.datastructures.f_binarysearchtree;

public class C_CreateBSTFromPreOrder {
    public static void main(String[] args) {
        BST bst = new BST();
        int[] array = new int[]{10, 5, 1, 7, 6, 9, 40, 30, 50};
        BST.Node node = bst.convertPreOrderToBST(array, 0, array.length - 1);
        bst.preOrder(node);
    }
}

class BST {
    Node root;

    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    //10, (5, 1, 7), 40, 50
    //own logic took lots of time
    //Time Complexity: O(n2)
    public Node convertPreOrderToBST(int[] array, int start, int end) {
        if (start > end) return null;
        if (start == end)
            return new Node(array[start]);
        int rootElement = array[start];
        Node root = new Node(rootElement);
        int dividingPosition = findDividingPosition(array, rootElement, start + 1, end);
        if (dividingPosition > 0) {
            root.left = convertPreOrderToBST(array, start + 1, dividingPosition - 1);
            root.right = convertPreOrderToBST(array, dividingPosition, end);
        }
        return root;
    }

    public int findDividingPosition(int[] array, int rootElement, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (rootElement < array[i])
                return i;
        }
        return -1;
    }

    public void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }
}

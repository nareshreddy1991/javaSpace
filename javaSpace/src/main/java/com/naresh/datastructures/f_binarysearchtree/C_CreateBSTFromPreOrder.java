package com.naresh.datastructures.f_binarysearchtree;

public class C_CreateBSTFromPreOrder {
    public static void main(String[] args) {
        BST bst = new BST();
        int[] array = new int[]{10, 5, 1, 7, 40, 50};
        //TODO method1 O(n^2)
        BST.Node node = bst.convertPreOrderToBST(array, 0, array.length - 1);
        bst.preOrder(node);

        //TODO method2 O(n^2)
        BST.Node node2 = null;
        System.out.println("method 2");
        for (int i : array)
            node2 = bst.createBST(node2, i);
        bst.preOrder(node2);

        //TODO method3 O(n)

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
        if (start > end)
            return null;
        if (start == end)
            return new Node(array[start]);
        int rootElement = array[start];
        Node root = new Node(rootElement);
        //time complexity of this code is O(n^2) due to below line
        // rather than finding dividingPosition what if we have min & max, initially min=0, max= Integer.max
        //later min=0, max=root for left tree
        //for right tree min= root, max= Integer.max
        int dividingPosition = findDividingPosition(array, rootElement, start + 1, end);
        if (dividingPosition > 0) {
            // when start is 4, dividingPos is 5 then start> end
            root.left = convertPreOrderToBST(array, start + 1, dividingPosition - 1);
            root.right = convertPreOrderToBST(array, dividingPosition, end);
        }
        return root;
    }

    public Node convertPreOrderToBST2(int[] array, int root, int rootIndex, int min, int max) {

        return null;
    }


    public Node createBST(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (root.data > data)
            root.left = createBST(root.left, data);
        else
            root.right = createBST(root.right, data);
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

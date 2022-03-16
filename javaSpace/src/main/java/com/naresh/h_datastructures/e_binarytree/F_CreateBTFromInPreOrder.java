package com.naresh.h_datastructures.e_binarytree;

public class F_CreateBTFromInPreOrder {
    public static void main(String[] args) {
        CreateBT tree = new CreateBT();
        char in[] = new char[] { 'D', 'B', 'E', 'A', 'F', 'C' };
        char pre[] = new char[] { 'A', 'B', 'D', 'E', 'C', 'F' };
        int len = in.length;
        CreateBT.Node root = tree.buildTree(in, pre);

        // building the tree by printing inorder traversal
        System.out.println("Inorder traversal of constructed tree is : ");
        tree.inOrder(root);
        System.out.println("total iterations:"+tree.iterations);
    }
}

class CreateBT {
    Node root;

    class Node {
        char data;
        Node left, right;

        public Node(char data) {
            this.data = data;
        }
    }


    //Inorder sequence: D B E A F C
    //Preorder sequence: A B D E C F
    int preIndex = 0;
    int iterations=0;
    // own code- little expensive
    public Node buildTree(char[] inOrder, char[] preOrder) {
        iterations++;
        char root = preOrder[preIndex++];
        int inIndex = findIndex(inOrder, root);
        Node node = new Node(root);
        char[] left = subArray(inOrder, 0, inIndex);//this is expensive
        if (left != null)
            node.left = buildTree(left, preOrder);
        char[] right = subArray(inOrder, inIndex + 1, inOrder.length);
        if (right != null)
            node.right = buildTree(right, preOrder);
        return node;
    }

    private int findIndex(char[] inOrder, char root) {
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == root)
                return i;
        }
        return -1;
    }

    private char[] subArray(char[] array, int start, int end) {
        int size = end - start;
        if (size == 0)
            return null;
        char[] newArray = new char[size];
        int j = 0;
        for (int i = start; i < end; i++) {
            newArray[j++] = array[i];
        }
        return newArray;
    }

    public void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
}

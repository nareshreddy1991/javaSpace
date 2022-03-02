package com.naresh.datastructures.e_binarytree;
/*
Time Complexity: O(n^2) in worst case. For a skewed tree, printGivenLevel() takes O(n) time where n is the number of nodes in the skewed tree.
 So time complexity of printLevelOrder() is O(n) + O(n-1) + O(n-2) + .. + O(1) which is O(n^2).
Space Complexity: O(n) in worst case. For a skewed tree, printGivenLevel() uses O(n) space for call stack.
 For a Balanced tree, the call stack uses O(log n) space, (i.e., the height of the balanced tree).
 */
public class E_LevelOrderTraversal1 {
    public static void main(String[] args) {
        LevelOrderTraversal tree = new LevelOrderTraversal();
        tree.root = new LevelOrderTraversal.Node(1);
        tree.root.left = new LevelOrderTraversal.Node(2);
        tree.root.right = new LevelOrderTraversal.Node(3);
        tree.root.left.left = new LevelOrderTraversal.Node(4);
        tree.root.left.right = new LevelOrderTraversal.Node(5);

        System.out.println("Level order traversal of binary tree is ");
        tree.printLevelOrder();
    }
}

class LevelOrderTraversal {
    Node root;

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        int lheight = height(node.left);
        int rheight = height(node.right);
        if (lheight > rheight)
            return lheight + 1;
        else
            return rheight + 1;
    }

    public void printLevelOrder() {
        int height = height(root);
        for (int i = 1; i <= height; i++)//level starts from 1
            printNodes(root, i);
    }

    private void printNodes(Node node, int level) {
        if (node == null)
            return;
        if (level == 1)
            System.out.println(node.data + " ");
        else if (level > 1) {
            printNodes(node.left, level - 1);
            printNodes(node.right, level - 1);
        }
    }


}

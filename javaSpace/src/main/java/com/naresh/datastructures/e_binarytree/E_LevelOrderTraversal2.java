package com.naresh.datastructures.e_binarytree;

import java.util.LinkedList;
import java.util.Queue;
/*
Time Complexity: O(n) where n is the number of nodes in the binary tree
Space Complexity: O(n) where n is the number of nodes in the binary tree
 */
public class E_LevelOrderTraversal2 {
    public static void main(String[] args) {
        LevelOrderTraversal tree = new LevelOrderTraversal();
        tree.root = new LevelOrderTraversal.Node(1);
        tree.root.left = new LevelOrderTraversal.Node(2);
        tree.root.right = new LevelOrderTraversal.Node(3);
        tree.root.left.left = new LevelOrderTraversal.Node(4);
        tree.root.left.right = new LevelOrderTraversal.Node(5);

        System.out.println("Level order traversal of binary tree is ");
        levelOrderTraversalWithQueue(tree.root);
    }

    public static void levelOrderTraversalWithQueue(LevelOrderTraversal.Node root) {

        Queue<LevelOrderTraversal.Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LevelOrderTraversal.Node poll = queue.poll();//remove the element
            System.out.println(poll.data + " ");
            if (poll.left != null)
                queue.add(poll.left);
            if (poll.right != null)
                queue.add(poll.right);
        }
    }
}



package com.naresh.h_datastructures.e_binarytree;

import java.util.Stack;

/*
BinaryTree:
    - every node at most have 2 nodes(0,1 or nodes)
    - trees are hierarchical data structures(not linear ds like Array etc)

      tree
      ----
       j    <-- root
     /   \
    f      k
  /   \      \
 a     h      z    <-- leaves

 Main applications of trees include:
1. Manipulate hierarchical data. (file system)
2. Make information easy to search (see tree traversal).
3. Manipulate sorted lists of data.
4. As a workflow for compositing digital images for visual effects.

Properties of Binary Tree:
    1. Maximum number of elements present in Binary Tree level l is 2 power l. formula= Math.pow(2,l) , l is level
        Ex: root, level=0, pow(2,0)=1
            next level of root, l=1 pow(2,1)=2
            next level, l=2, pwd(2,2)=4
   2. The Maximum number of nodes in a binary tree of height ‘h’ is 2h – 1.
          root height is 1
          if root height is 0 then formula will be 2h+1-1 (2 pow h)
          1+2+4+...
   3.
 */
public class A_BinaryTree {
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
        tree.root.left.right = new Node(5);

        System.out.println(
                "Preorder traversal of binary tree is ");
        tree.printPreorder();

        System.out.println(
                "\nInorder traversal of binary tree is ");
        tree.printInorder();

        System.out.println(
                "\nPostorder traversal of binary tree is ");
        tree.printPostorder();

        //
         /* creating a binary tree and entering
        the nodes */
        BinaryTreeInOrderTraversal inOrderTraversal = new BinaryTreeInOrderTraversal();
        inOrderTraversal.root = new Node(1);
        inOrderTraversal.root.left = new Node(2);
        inOrderTraversal.root.right = new Node(3);
        inOrderTraversal.root.left.left = new Node(4);
        inOrderTraversal.root.left.right = new Node(5);
        System.out.println("with out recursion");
        inOrderTraversal.inOrder();
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

    //wrapper methods
    void printPostorder() {
        postOrder(root);
    }

    void printInorder() {
        inOrder(root);
    }

    void printPreorder() {
        preOrder(root);
    }

    public void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }
}

//without recurssion
/*
Algorithm:
1) Create an empty stack S.
2) Initialize current node as root
3) Push the current node to S and set current = current->left until current is NULL
4) If current is NULL and stack is not empty then
     a) Pop the top item from stack.
     b) Print the popped item, set current = popped_item->right
     c) Go to step 3.
5) If current is NULL and stack is empty then we are done.
 */
class BinaryTreeInOrderTraversal {
    Node root;

    public void inOrder() {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (curr != null || stack.size() > 0) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            Node pop = stack.pop();
            System.out.println(pop.data);
            curr = pop.right;
        }
    }
}
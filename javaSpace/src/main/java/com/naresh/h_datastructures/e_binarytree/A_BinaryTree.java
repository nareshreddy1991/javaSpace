package com.naresh.h_datastructures.e_binarytree;

import java.util.Iterator;
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
        inOrderTraversal.postOrder();
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
        System.out.println("Total sum:" + preOrderSum(root));
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

    public int preOrderCount(Node node) {
        if (node == null)
            return 0;
        return 1 + preOrderCount(node.left) + preOrderCount(node.right);
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

//without recurssion (In order)
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

    public void preOrder() {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        stack.push(curr);
        while (!stack.empty()) {
            Node pop = stack.pop();
            System.out.print(pop.data + " ");
            Node right = pop.right;
            if (right != null)
                stack.push(right);
            Node left = pop.left;
            if (left != null)
                stack.push(left);
        }
    }

    //Level order with stack + move the popped elements to other stack + print it
    //O(n) O(2h)
    public void postOrder() {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.empty()) {
            Node pop = stack1.pop();
            stack2.add(pop);
            if (pop.left != null)
                stack1.add(pop.left);
            if (pop.right != null)
                stack1.add(pop.right);
        }
        //in stack iterator prints the data from bottom to top
//        Iterator<Node> iterator = stack2.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next().data + " ");
//        }
        //print top to bottom
        while (!stack2.empty()){
            System.out.print(stack2.pop().data+" ");
        }
    }

    //TODO postorder with single stack
    //todo postorder with no stack/hashSet
}
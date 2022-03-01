package com.naresh.datastructures.f_binarysearchtree;

public class B_BinarySearchTreeDeleteNode {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new Node(8);
        tree.root.left = new Node(5);
        tree.root.right = new Node(10);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(6);
        inOrder(tree.root);
        delete(tree.root, 6);
        System.out.println("after deletion");
        inOrder(tree.root);
    }

    //my logic -- working
    public static Node delete(Node root, int key) {
        if (root == null)
            return null;
        if (root.data < key)
            root.right = delete(root.right, key);
        else if (root.data > key)
            root.left = delete(root.left, key);
        else {
            if (root.left != null && root.right != null) {
                root.data = minValue(root.right);
                root.right = delete(root.right, root.data);
            } else if (root.left != null) {
//                root.data = root.left.data;
                return root.left;
            } else if (root.right != null) {
//                root.data = root.right.data;
                return root.right;
            } else {// for node not having left & right
                return null;//what ever we are returning that will be initialized to root.left/right
            }
        }
        return root;
    }

    //improved logic
    Node deleteRec(Node root, int key) {
        /* Base Case: If the tree is empty */
        if (root == null)
            return root;

        /* Otherwise, recur down the tree */
        if (key < root.data)
            root.left = deleteRec(root.left, key);
        else if (key > root.data)
            root.right = deleteRec(root.right, key);

            // if key is same as root's
            // key, then This is the
            // node to be deleted
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    //without recursion -- This is not working
    public static void deleteNode(Node node, int key) {
        if (node == null) {
            return;
        }
        Node curr = node;
        Node previous = null;
        while (curr != null) {
            if (curr.data == key) {
                if (curr.left == null && curr.right == null) {
                    if (previous.data < key)
                        previous.right = null;
                    else
                        previous.left = null;
                    break;
                } else if (curr.left != null && curr.right == null) {
                    if (previous.data < key)
                        previous.right = curr.left;
                    else
                        previous.left = curr.left;
                    break;
                } else if (curr.left == null && curr.right != null) {
                    if (previous.data < key)
                        previous.right = curr.right;
                    else
                        previous.left = curr.right;
                    break;
                } else {
                    previous = curr;
                    curr.data = minValue(curr.right);//replacing the original key
                    key = curr.data;
                    curr = curr.right;//we still need to delete minval in the right tree
                }
            } else if (curr.data < key) {
                previous = curr;
                curr = curr.right;
            } else {
                previous = curr;
                curr = curr.left;
            }
        }
    }

    public static int minValue(Node node) {
        int min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    public static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
}


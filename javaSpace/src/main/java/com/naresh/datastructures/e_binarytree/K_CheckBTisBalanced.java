package com.naresh.datastructures.e_binarytree;

/*
Check the given BT is balanced tree or not
 */
public class K_CheckBTisBalanced {
    public static void main(String[] args) {
        BT6 bt = new BT6();
        bt.root = new BT6().new Node(10);
        bt.root.left = new BT6().new Node(8);
        bt.root.right = new BT6().new Node(11);
        bt.root.left.left = new BT6().new Node(6);
        bt.root.left.right = new BT6().new Node(9);
        bt.root.right.left = new BT6().new Node(4);
        bt.root.right.right = new BT6().new Node(7);

        bt.checkBalancedTreeOrNot(bt.root);
        System.out.println("is Balanced:" + bt.isBalanced);
        BT6.Height height = bt.new Height();
        System.out.println("is Balanced:" + bt.checkBalancedTreeOrNot2(bt.root, height));
        System.out.println("height:" + height.height);
    }
}

class BT6 {
    Node root;

    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    boolean isBalanced = true;

    /* own logic : using external variable,
or we can pass IsBalanced class as param and change the status there
class IsBalanced{
    boolean isBalanced;
}
If method has to return boolean then need to follow different approach
 */
    public int checkBalancedTreeOrNot(Node node) {
        if (node == null)
            return 0;
        int lh = checkBalancedTreeOrNot(node.left);
        int rh = checkBalancedTreeOrNot(node.right);
        if (isBalanced && Math.abs(lh - rh) > 1) {
            isBalanced = false;
        }
        if (lh > rh)
            return lh + 1;
        else
            return rh + 1;
    }

    class Height {
        int height;
    }

    /*
    Time Complexity: O(n)
 Auxiliary Space: O(n)
     */
    public boolean checkBalancedTreeOrNot2(Node node, Height height) {
        if (node == null) {
//            height.height = 0; //ths is not required
            return true;//TODO why? - for leafs if we return false then false & true will be false
        }

        Height lh = new Height(), rh = new Height();
        boolean l = checkBalancedTreeOrNot2(node.left, lh);
        boolean r = checkBalancedTreeOrNot2(node.right, rh);
        //this is to find the total height of the tree, this is not playing any role in calculations
        height.height = (lh.height > rh.height ? lh.height : rh.height) + 1;

        if (Math.abs(lh.height - rh.height) > 1)
            return false;
        return l && r;
    }
}

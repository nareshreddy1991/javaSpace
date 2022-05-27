package com.naresh.h_datastructures.b_linkedlist;

public class E_SLLMiscProblems {
    public static void main(String[] args) {
        LinkedList llist = new LinkedList();
        llist.add(20);
        llist.add(13);
        llist.add(13);
        llist.add(8);
        llist.add(6);
        llist.add(2);
        llist.add(30);
        makeLastNodeAsHead(llist);
        llist.printAll();
        findThirdNodeFromLast(llist);
        llist = reverse2(llist);
        System.out.println("After reversing:");
        llist.printAll();
        findMiddleElement(llist);

    }

    private static void makeLastNodeAsHead(LinkedList linkedList) {
        LinkedList.Node secLast = linkedList.head;
        while (secLast.next != null && secLast.next.next != null) {
            secLast = secLast.next;
        }
        secLast.next.next = linkedList.head;
        linkedList.head = secLast.next;
        secLast.next = null;
    }

    private static void findThirdNodeFromLast(LinkedList linkedList) {
        LinkedList.Node thirdLast = linkedList.head;
        while (thirdLast.next != null && thirdLast.next.next != null
                && thirdLast.next.next.next != null) {
            thirdLast = thirdLast.next;
        }
        System.out.println("Third node from last:" + thirdLast.data);
    }

    private static int countLLNodes(LinkedList.Node node) {
        if (node == null)
            return 0;
        return 1 + countLLNodes(node.next);
    }

    //  1 2 3 4 5
    private static LinkedList reverse(LinkedList linkedList) {
        LinkedList.Node curr = linkedList.head.next;
        LinkedList.Node prev = linkedList.head;
        prev.next = null;
        LinkedList.Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        linkedList.head = prev;
        return linkedList;
    }

    //improved
    // 1 2 3 4 5
    private static LinkedList reverse2(LinkedList linkedList) {
        LinkedList.Node curr = linkedList.head;
        LinkedList.Node prev = null;
        LinkedList.Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        linkedList.head = prev;
        return linkedList;
    }

    //find the length, then traverse till len-n+1 and return the current
    private static void printNthNodeFromEnd(LinkedList linkedList) {

    }

    private static void findMiddleElement(LinkedList linkedList) {
        //Method1: find the count and return count/2
        //Method3: have counter, mid=head, travel the full list, increment counter every time, when countr is odd mid=mid.next
        //Method2: have two pointers one will move one step and other move two steps
        LinkedList.Node curr = linkedList.head;
        LinkedList.Node twoStep = linkedList.head;
        while (twoStep != null) {
            curr = curr.next;
            twoStep = twoStep.next;
            if (twoStep != null)
                twoStep = twoStep.next;
        }
        System.out.println("Middle element:"+curr.data);
    }
}

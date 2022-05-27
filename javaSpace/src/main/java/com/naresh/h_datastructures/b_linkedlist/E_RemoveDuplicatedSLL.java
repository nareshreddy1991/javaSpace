package com.naresh.h_datastructures.b_linkedlist;

public class E_RemoveDuplicatedSLL {
    public static void main(String[] args) {
        LinkedList llist = new LinkedList();
        llist.add(20);
        llist.add(13);
        llist.add(13);
        llist.add(11);
        llist.add(11);
        llist.add(11);
        llist.add(10);
        removeDuplicatedInSortedSLL(llist);
        System.out.println("Duplicate removal in sorted list");
        llist.printAll();

        llist = new LinkedList();
        llist.add(4);
        llist.add(10);
        llist.add(11);
        llist.add(10);
        llist.add(10);
        llist.add(11);
        llist.add(12);
        llist.add(10);
        llist.add(12);
        llist.add(13);
        removeDuplicatesInUnSortedSLL(llist);
        System.out.println("Duplicate removal in unsorted list");
        llist.printAll();
    }

    /// 11->11->12-->12
    private static void removeDuplicatedInSortedSLL(LinkedList linkedList) {
        if (linkedList == null)
            return;
        LinkedList.Node curr = linkedList.head.next;
        LinkedList.Node prev = linkedList.head;
        while (curr != null) {
            if (curr.data == prev.data) {
                prev.next = curr.next;
            } else {
                prev = curr;//When removed no need to change the previous
            }
            curr = curr.next;
        }
    }

    //10 20 10 21 10 20
    //O(n^2) O(1)
    //we can use HashSet to store the visited elements, if alredy visted remove it
    private static void removeDuplicatesInUnSortedSLL(LinkedList linkedList) {
        if (linkedList == null)
            return;
        LinkedList.Node curr = linkedList.head;
        while (curr != null) {
            LinkedList.Node next = curr.next;
            LinkedList.Node prev = curr;
            while (next != null) {
                if (curr.data == next.data) {
                    prev.next = next.next;
                } else {
                    prev = next;
                }
                next = next.next;
            }
            curr = curr.next;
        }
    }

    //TODO we can sort using merge sort then remove the duplicates
}

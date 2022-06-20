import java.util.Arrays;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
//        System.out.println(findNumbersDivisible(12, 30, 3));
        Node list1 = new Node();
        list1.data = 2;

        Node list11 = new Node();
        list11.data = 4;

        Node list111 = new Node();
        list111.data = 3;

        list1.next = list11;
        list11.next = list111;

        Node list2 = new Node();
        list2.data = 5;

        Node list22 = new Node();
        list22.data = 6;

        Node list222 = new Node();
        list222.data = 4;

        list2.next = list22;
        list22.next = list222;

        Node node = addTwoNumbers(list1, list2);
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }


    }

    public static int findNumbersDivisible(int a, int b, int div) {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (i % div == 0)
                count++;
        }
        return count;
    }

    public static Node addTwoNumbers(Node list1, Node list2) {

        String n1 = "";
        int size = 0;
        while (list1 != null) {
            n1 = n1 + list1.data;
            list1 = list1.next;
            size++;
        }
        String n2 = "";
        while (list2 != null) {
            n2 = n2 + list2.data;
            list2 = list2.next;
        }
        int sum = Integer.valueOf(n1) + Integer.valueOf(n2);
        System.out.println("Sum:" + sum);
        int[] arr = new int[size];
        int i = 0;
        while (sum > 0) {
            int tmp = sum % 10;
            arr[i] = tmp;
            sum = sum / 10;
            i++;
        }
        Node head = null;
        Node prev = null;
        for (i = size - 1; i >= 0; i--) {
            Node newNode = new Node();
            if (head == null) {
                head = newNode;
            }
            newNode.data = arr[i];
            if (prev != null) {
                prev.next = newNode;
            }
            prev = newNode;
        }
        return head;
    }
}

class Node {
    int data;
    Node next;
}

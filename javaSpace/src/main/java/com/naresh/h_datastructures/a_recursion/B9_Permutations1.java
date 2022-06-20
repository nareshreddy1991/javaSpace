package com.naresh.h_datastructures.a_recursion;

import java.util.ArrayList;
import java.util.List;

public class B9_Permutations1 {
    public static void main(String[] args) {
        int[] input = new int[]{3, 4, 1, 2};
        boolean[] isPicked = new boolean[input.length];
        permutations(input, input.length, isPicked, new ArrayList<>());
    }

    private static void permutations(int[] a, int n, boolean[] isPicked, List<Integer> list) {
        if (list.size() == n) {
            list.forEach(System.out::print);
            System.out.println();
            return;
            //to return result just add it to another list - list.add(new ArrayList(list))
        }
        for (int i = 0; i < n; i++) {
//            if (!list.contains(a[i])) { //THis will increase complexity
            if (!isPicked[i]) {
                isPicked[i] = true;
                list.add(a[i]);
                permutations(a, n, isPicked, list);
                list.remove(list.size() - 1);
                isPicked[i] = false;
//                permutations(a, n, isPicked, list); this will leads to SackOveflow
            }
        }
    }

    private static void permutations2(int[] a, int n, List<Integer> list) {
        if (list.size() == n) {
            list.forEach(System.out::print);
            System.out.println();
            return;
            //to return result just add it to another list - list.add(new ArrayList(list))
        }
        for (int i = 0; i < n; i++) {
            if (!list.contains(a[i])) { //TODO this operation is time consuming
                list.add(a[i]);
                permutations2(a, n, list);
                list.remove(list.size() - 1);
            }
        }
    }
}

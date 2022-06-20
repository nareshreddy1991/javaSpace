package com.naresh.h_datastructures.a_recursion;

import java.util.Arrays;

public class B9_Permutations2 {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3};
        permutations(input, 0);
    }

    public static void permutations(int[] a, int index) {
        if (index == a.length) {
            Arrays.stream(a).forEach(System.out::print);
            System.out.println();
        }
        for (int i = index; i < a.length; i++) {
            swap(a, i, index);
            permutations(a, index + 1);
            swap(a, i, index);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

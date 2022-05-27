package com.naresh.h_datastructures.a_recursion;

import java.util.Arrays;

public class B4_Problems {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5, 6};
        int[] input2 = new int[]{1, 2, 3, 4, 5, 6};
        reverseArray(input, 0, input.length - 1);
        Arrays.stream(input).forEach(System.out::print);
        System.out.println();
        reverseArray(input2, 0);
        Arrays.stream(input2).forEach(System.out::print);
        System.out.println("\nCheck Polindrome:" + isPolindrome("MADAM2", 0));
        System.out.println("\nCheck Polindrome2:" + isPolindrome2("MADAM", 0));
    }

    private static void reverseArray(int[] a, int l, int r) {
        if (l >= r) return;
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
        reverseArray(a, l + 1, r - 1);
    }

    // 1 2 3 4
    private static void reverseArray(int[] a, int i) {
        int len = a.length;
        if (i >= len / 2)
            return;
        int temp = a[i];
        a[i] = a[len - 1 - i];
        a[len - 1 - i] = temp;
        reverseArray(a, i + 1);
    }

    private static boolean isPolindrome(String str, int i) {
        int len = str.length();
        if (i >= len / 2)
            return true;
        char[] chars = str.toCharArray();
        boolean isPol = false;
        if (chars[i] == chars[len - 1 - i])
            isPol = true;
        return isPol && isPolindrome(str, i + 1);
    }

    private static boolean isPolindrome2(String str, int i) {
        int len = str.length();
        if (i >= len / 2)
            return true;
        char[] chars = str.toCharArray();
        if (chars[i] != chars[len - 1 - i])//as soon as one condition is failed then simply return false
            return false;
        return isPolindrome2(str, i + 1);
    }
}

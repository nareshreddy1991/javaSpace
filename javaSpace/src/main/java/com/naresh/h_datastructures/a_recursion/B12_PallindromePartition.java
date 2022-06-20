package com.naresh.h_datastructures.a_recursion;

import java.util.ArrayList;
import java.util.List;

public class B12_PallindromePartition {
    public static void main(String[] args) {
        String input = "aabb";
        palindromePartition(input, 0, new ArrayList<>());
    }

    public static void palindromePartition(String input, int index, List<String> ds) {
        if (index == input.length()) {
            ds.stream().forEach(e -> System.out.print(e + " "));
            System.out.println();
            return;
        }
        for (int i = index; i < input.length(); i++) {
            if (isPolindrome(input, index, i)) {
                ds.add(input.substring(index, i + 1));
                palindromePartition(input, i + 1, ds);//here i+1 not index+1
                ds.remove(ds.size() - 1);
            }
        }
    }

    private static boolean isPolindrome(String input, int start, int end) {
        /*for (int i = start; i < end; i++) {
            if (i<input.length() && input.charAt(i) != input.charAt(end - i))
                return false;
        }*/
        while (start <= end) {
            if (input.charAt(start++) != input.charAt(end--)) {
                return false;
            }
        }
        return true;
    }


}

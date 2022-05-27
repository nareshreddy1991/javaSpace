package com.naresh.h_datastructures.a_recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B7_CombinationSum2 {
    public static void main(String[] args) {
        int[] input = new int[]{1, 1, 1, 2, 2};
        Arrays.sort(input);//TODO must be sorted
        List<List<Integer>> result = new ArrayList<>();
        combination(input, 0, 4, new ArrayList<>(), result);
        for (List<Integer> list : result) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    private static void combination(int[] a, int index, int target, List<Integer> ds, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(ds));
            return;
        }
        for (int i = index; i < a.length; i++) {
            if (i > index && a[i] == a[i - 1])//from the second element if any repeated elements just ignore it
                continue;
            if (a[i] > target)//since elements are in sorted order,  just optimization
                break;
            ds.add(a[i]);
            combination(a, i + 1, target - a[i], ds, result);
            ds.remove(ds.size() - 1);//while going to previous call current element needs to be removed
        }
    }
}

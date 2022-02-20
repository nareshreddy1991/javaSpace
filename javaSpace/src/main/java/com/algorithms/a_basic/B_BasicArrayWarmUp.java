package com.algorithms.a_basic;

import java.util.Arrays;
import java.util.List;

public class B_BasicArrayWarmUp {
    public static void main(String[] args) {
        //reverse an array
        System.out.println("Reverse an array:");
        int[] array = new int[]{5, 6, 8, 7, 2, 4, 6, 3};//9 elements, index 0-8, 9/2=4 ,iterate 0-3(4times)
        for (int i = 0; i < array.length / 2; i++) {//8 elements, index 0-7, 8/2=4 ,iterate 0-3(4times)
            int s = array[i];
            int l = array[array.length - i - 1];//since array index is starting from 0 we need to do i-1
            array[array.length - i - 1] = s;
            array[i] = l;
        }
        Arrays.stream(array).forEach(System.out::print);

        System.out.println("\nshift elements right by one position from 3rd position");
        int[] array2 = new int[]{5, 6, 8, 7, 2, 4, 6, 3, 0, 0};//5668724630
        int dataLen = 8;
        int fromPosition = 3;
        for (int i = 0; i < array2.length - (array2.length - 8); i++) {
            if (i < fromPosition) continue;
            array2[dataLen] = array2[--dataLen];
        }
        array2[3] = 0;
        Arrays.stream(array2).forEach(System.out::print);

        System.out.println("\nremove 3rd position and shift elements left");
        int[] array3 = new int[]{5, 6, 8, 7, 2, 4, 6, 3, 0, 0};//5682463000
        int removePosition = 3;
        for (int i = 0; i < array3.length - 1; i++) {
            if (i < removePosition) continue;
            array3[i] = array3[i + 1]; //array3[++i] - this will not work, because "i" is part of loop
        }
        Arrays.stream(array3).forEach(System.out::print);

        System.out.println("\nFind the different combination of given string");
        String input = "ABCD";//4*4 COMBINATION
//        String[] split = input.split("");
        char[] chars = input.toCharArray(); //this is prefered
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                System.out.print(chars[i] + "" + chars[j] + " ");
            }
        }

        System.out.println("Check if a List of integers contains only odd numbers?");
        List<Integer> list = List.of(3, 5, 7, 9, 1);
        boolean allOdd = true;
        for (Integer i : list) {
            if (i % 2 == 0) {
                allOdd = false;
                break;
            }
        }
        //using stream
        boolean b = list.stream()
                .allMatch(e -> e % 2 == 1);
//                .allMatch(e -> e % 2 != 0); //or
        System.out.println("only odd numbers" + b);

        System.out.println("find second largest number in the given array");
        int[] a = new int[]{3, 5, 7, 9, 1, 25, 17, 20};
        //one is to sort and get the element
        int large = 0, secondLarge = 0;
        for (int i : a) {
            if (i > large) {
                secondLarge = large;
                large = i;
            } else if (i > secondLarge) {
                secondLarge = i;
            }
        }
        System.out.println("large" + large + "/second largest:" + secondLarge);

        System.out.println("");

    }
}

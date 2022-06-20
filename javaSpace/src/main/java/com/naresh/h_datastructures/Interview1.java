package com.naresh.h_datastructures;

import java.util.*;

public class Interview1 {
    public static void main(String[] args) {
        int input1 = 2610;
        int input2 = 2359;
        int input3 = 1784;

//        int extracted = extracted(input1, input2, input3);
        int extracted = password(12, 1313, 122, 678, 898);
        System.out.println(extracted);

        List<String> list = Arrays.asList("aa","b");
        Optional<String> first = list.stream().sorted(Comparator.comparing(e -> e.length())).findFirst();
        System.out.println(first.get());

    }

//    public static  <T extends [String | Integer]> void method(T param) {
//    }

    static int extracted(int input1, int input2, int input3) {
        int f = (input1 % 10000) / 1000;
        int s = (input2 % 1000) / 100;

        int min = Integer.MAX_VALUE;
        while (input3 > 0) {
            int curr = input3 % 10;
            min = Math.min(min, curr);
            input3 = input3 / 10;
        }
        int result = (f * s) + min;
        return result;
    }

    static int password(int input1, int input2, int input3, int input4, int input5) {
        List<Integer> list = Arrays.asList(input1, input2, input3, input4, input5);
        int maxStable = -1;
        int minUnStable = Integer.MAX_VALUE;
        for (Integer num : list) {
            int temp = num;
            HashMap<Integer, Integer> map = new HashMap<>();
            while (temp > 0) {
                int digit = temp % 10;
                if (map.containsKey(digit)) {
                    Integer count = map.get(digit);
                    map.put(digit, count + 1);
                } else {
                    map.put(digit, 1);
                }
                temp = temp / 10;
            }
            Set<Integer> collect = new HashSet<>(map.values());
            if (collect.size() == 1) {
                maxStable = Math.max(maxStable, num);
            } else {
                minUnStable = Math.min(minUnStable, num);
            }
        }
        return maxStable + minUnStable;
    }

    /*

     */
    private static void printMaxProduct(int[] array) {
        int product = 0;
        int tempProduct = 0;
       /* for (int i = 0; i < array.length - 2; i++) {
            tempProduct = array[i] * array[i + 1] * array[i + 2];
            product = Math.max(tempProduct, product);
        }*/

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                for (int k = 0; k < array.length; k++) {
                    if (i != j && j != k & i != k) {
                        System.out.println(array[i] + " " + array[j] + " " + array[k]);
                        tempProduct = array[i] * array[j] * array[k];
                        product = Math.max(tempProduct, product);
                    }
                }
            }
        }
        System.out.println(product);
    }
}


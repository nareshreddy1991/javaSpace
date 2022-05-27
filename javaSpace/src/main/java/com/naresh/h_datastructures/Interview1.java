package com.naresh.h_datastructures;

import java.util.*;

public class Interview1 {
    public static void main(String[] args) {
        int[] input = new int[]{1, 3, 5, 2, 4};
        printMaxProduct(input);
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
                    if (i != j && j != k & i!=k) {
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


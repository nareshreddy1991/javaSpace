package com.naresh.h_datastructures.j_matrix;

public class MatrixUtils {

    public static int[][] a = new int[][]{
            {1, 2, 3, 4},
            {7, 8, 9, 10},
            {13, 14, 15, 16},
            {17, 18, 19, 20}};

    public static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            int[] row = a[i];
            int colLength = row.length;
            for (int j = 0; j < colLength; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}

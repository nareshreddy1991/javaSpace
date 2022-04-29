package com.naresh.h_datastructures.j_matrix;

public class C1_RowWiseSorting extends MatrixUtils {

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {2, 4, 3, 1},
                {17, 8, 9, 10},
                {8, 14, 7, 16},
                {20, 4, 1, 20}};
        sortRowWise(a);//selection sort
        print(a);
    }

    private static void sortRowWise(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n-1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i][j] > a[i][k]) {
                        int temp = a[i][j];
                        a[i][j] = a[i][k];
                        a[i][k] = temp;
                    }
                }
            }
        }
    }
}

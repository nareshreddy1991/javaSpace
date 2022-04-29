package com.naresh.h_datastructures.j_matrix;

public class E1_PrintDiagonal {
    public static void main(String[] args) {
        int[][] a1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] a = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {24, 23, 22, 21, 20, 19},
        };
        printDiagonalPositiveSlop(a);
        printDiagonalNegativeSlop(a);
    }

    private static void printDiagonalPositiveSlop(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int j = 0; j < m; j++) {//TODO j < m row by row
            int row = j, col = 0;
            while (row >= 0 & col < m) {
                System.out.print(a[row][col] + " ");
                row--;
                col++;
            }
            System.out.println();
        }
        for (int i = 1; i < n; i++) {
            int row = m - 1, col = i;
            while (row >= 0 & col < m) {
                System.out.print(a[row][col] + " ");
                row--;
                col++;
            }
            System.out.println();
        }
    }

    private static void printDiagonalNegativeSlop(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < n; i++) {
            int row = 0, col = i;
            while (row < m && col >= 0) {
                System.out.print(a[row][col] + " ");
                row++;
                col--;
            }
            System.out.println();
        }

        for (int i = 1; i < m; i++) {
            int row = i, col = n-1;
            while (row < m && col >= 0) {
                System.out.print(a[row][col] + " ");
                row++;
                col--;
            }
            System.out.println();
        }
    }
}

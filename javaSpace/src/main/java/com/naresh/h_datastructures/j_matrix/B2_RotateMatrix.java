package com.naresh.h_datastructures.j_matrix;

public class B2_RotateMatrix {
    public static void main(String[] args) {
//        int[][] a = new int[][]{{1, 2}, {4, 5}, {6, 7}};
//        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] a = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {24, 23, 22, 21, 20, 19},
        };
        print(a);
        spiralOrder1(a);
        print(a);
    }

    // Time complexity O(m*n)
    //space O(1)
    public static void spiralOrder1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = 0;
        while (r < m & c < n) {
            if (r + 1 == m || c + 1 == n)
                break;
            int prev = matrix[r + 1][c];
            for (int i = c; i < n; i++) {
                System.out.print(matrix[r][i] + " ");
                int temp = matrix[r][i];
                matrix[r][i] = prev;
                prev = temp;
            }
            r++;
            for (int i = r; i < m; i++) {
                System.out.print(matrix[i][n - 1] + " ");
                int temp = matrix[i][n - 1];
                matrix[i][n - 1] = prev;
                prev = temp;
            }
            n--;
            if (r < m) {
                for (int i = n - 1; i >= c; i--) {
                    System.out.print(matrix[m - 1][i] + " ");
                    int temp = matrix[m - 1][i];
                    matrix[m - 1][i] = prev;
                    prev = temp;
                }
                m--;
            }
            if (c < n) {
                for (int i = m - 1; i >= r; i--) {
                    System.out.print(matrix[i][c] + " ");
                    int temp = matrix[i][c];
                    matrix[i][c] = prev;
                    prev = temp;
                }
                c++;
            }

        }

    }

    private static void print(int[][] a) {
        System.out.println();
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

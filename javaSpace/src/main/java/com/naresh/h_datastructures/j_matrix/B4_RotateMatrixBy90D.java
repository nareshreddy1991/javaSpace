package com.naresh.h_datastructures.j_matrix;

public class B4_RotateMatrixBy90D {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3, 4},
                {7, 8, 9, 10},
                {13, 14, 15, 16},
                {17, 18, 19, 20}};
        print(a);
        rotateBy90ClockWise(a);
        System.out.println("after rotation");
        print(a);
    }

    /*
    1 2 3
    4 5 6
    7 8 9
    TODO inplace rotation so M=N, say it as N
     */
    //refer book for explanation
    public static void rotateBy90AntiClockWise(int[][] a) {
        int N = a.length;
        for (int r = 0; r < N / 2; r++) {
            for (int c = r; c < N - 1 - r; c++) {
                int temp = a[r][c];
                a[r][c] = a[c][N - 1 - r];
                a[c][N - 1 - r] = a[N - 1 - r][N - 1 - c];
                a[N - 1 - r][N - 1 - c] = a[N - 1 - c][r];
                a[N - 1 - c][r] = temp;
            }
        }
    }

    public static void rotateBy90ClockWise(int[][] a) {
        int N = a.length;
        for (int r = 0; r < N / 2; r++) {
            for (int c = r; c < N - 1 - r; c++) {
                int temp = a[r][c];
                a[r][c] = a[N - 1 - c][r];
                a[N - 1 - c][r] = a[N - 1 - r][N - 1 - c];
                a[N - 1 - r][N - 1 - c] = a[c][N - 1 - r];
                a[c][N - 1 - r] = temp;
            }
        }
    }

    private static void print(int[][] a) {
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

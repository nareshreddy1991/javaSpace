package com.naresh.h_datastructures.j_matrix;
//rotate a matrix or image by 90/180 degrees with extra space
//rotating a matrix by 90 is called transpose
public class B3_RotateMatrixBy90D {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18}};
        print(a);
        int[][] result = rotateBy90ClockWise(a);
        print(result);
        System.out.println("anti close 90");
        result = rotateBy90AntiClockWise(a);
        print(result);
        System.out.println("clock wise 180");
        result = rotateBy180ClockWise(a);
        print(result);
    }

    /*
    3*2
    1 2         2*3
    4 5         7 4 1
    7 8   =>    8 5 2
     */
    public static int[][] rotateBy90ClockWise(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] result = new int[n][m];//2X3
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][m - i - 1] = a[i][j];
            }
        }
        return result;
    }

    /*
    3*2
    1 2         2*3
    4 5         2 5 8
    7 8   =>    1 4 7
     */
    public static int[][] rotateBy90AntiClockWise(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] result = new int[n][m];//2X3
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[n-j-1][i] = a[i][j];
            }
        }
        return result;
    }

    /*
 3*2
 1 2         8 7
 4 5         5 4
 7 8   =>    2 1
 TODO for 180 clockwise or anti clock wise we get the same result
  */
    public static int[][] rotateBy180ClockWise(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[m-i-1][n - j - 1] = a[i][j];

            }
        }
        return result;
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

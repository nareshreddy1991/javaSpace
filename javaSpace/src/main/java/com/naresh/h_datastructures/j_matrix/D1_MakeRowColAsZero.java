package com.naresh.h_datastructures.j_matrix;

/*
Given an M × N matrix consisting of only 0 or 1, change all elements of row i and column j to 0 if cell (i, j) has value 0.
Do this without using any extra space for every (i, j) having value 0.
For example,
Input:

[ 1  1  0  1  1 ]
[ 1  1  1  1  1 ]
[ 1  1  1  0  1 ]
[ 1  1  1  1  1 ]
[ 0  1  1  1  1 ]

Output:
[ 0  0  0  0  0 ]
[ 0  1  0  0  1 ]
[ 0  0  0  0  0 ]
[ 0  1  0  0  1 ]
[ 0  0  0  0  0 ]

 */
public class D1_MakeRowColAsZero extends MatrixUtils {
    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 1, 0, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 1, 1, 1, 1}};
//        int[][] a = new int[][]{
//                {1, 2, 3, 4, 5, 6},
//                {7, 8, 9, 10, 11, 12},
//                {13, 14, 15, 16, 17, 18}};
        makeRowAndColsZero2(a);
        print(a);
    }

    //TODO this approach use extra space
    //Space complexity O(N)
    private static void makeRowAndColsZero1(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean[][] replaced = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0 && !replaced[i][j]) {
                    //make all rows as zero
                    for (int k = 0; k < m; k++) {
                        a[i][k] = 0;
                        replaced[i][k] = true;
                    }
                    //make all cols as zero
                    for (int k = 0; k < n; k++) {
                        a[k][j] = 0;
                        replaced[k][j] = true;
                    }
                    break;
                }
            }
        }
    }

    //Without extra space O(1)
    //THis approach works if its a binary matrix
    private static void makeRowAndColsZero2(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    //make all rows as zero
                    for (int k = 0; k < m; k++) {
                        a[i][k] = -1;//TODO make it -1 then in the next loop replace -1 with 0
                    }
                    //make all cols as zero
                    for (int k = 0; k < n; k++) {
                        a[k][j] = -1;
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == -1) {
                    a[i][j] = 0;
                }
            }
        }
    }
}

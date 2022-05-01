package com.naresh.h_datastructures.j_matrix;
/*
Find given element in mxn row wise and column wise sorted matrix in liner time
 */
public class D2_FindElement {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {-4, -3, -1, 3, 5}, //<---start
                {-3, -2, 2, 4, 6},
                {-1, 1, 3, 5, 8},
                {3, 4, 7, 8, 9}
        };
        find(a, 3);
    }

    /*
    The time complexity of the proposed solution is O(M + N) for an M × N matrix and doesn’t require any extra space.
     */
    private static void find(int[][] a, int key) {//4x5
        int m = a.length;
        int n = a[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (a[row][col] > key) {
                col--;
            } else if (a[row][col] < key) {
                row++;
            } else {
                System.out.println(row + " " + col);
                row++;
            }
        }
    }
}

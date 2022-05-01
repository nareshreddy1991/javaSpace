package com.naresh.h_datastructures.j_matrix;

/*
Given a binary M × N row-wise sorted matrix, find a row that contains the maximum number of 1's in linear time
[ 0  0  0  1  1 ]
[ 0  0  1  1  1 ]
[ 0  0  0  0  0 ]
[ 0  1  1  1  1 ]
[ 0  0  0  0  1 ]

Output: The maximum 1’s are present in row 4
 */
public class D3_FindRowIndexInBinaryMatrix {
    public static void main(String[] args) {
        int[][] mat =
                {
                        { 0, 0, 0, 1, 1 },
                        { 0, 0, 1, 1, 1 },
                        { 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 1, 1 },
                        { 0, 0, 0, 0, 1 }
                };
        findIndex(mat);
    }

    private static void findIndex(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        int row = 0, col = n - 1;
        int maxRowIndex = -1;
        while (row < m & col >= 0) {
            if (a[row][col] == 1) {
                col--;
                maxRowIndex = row;
            } else {
                row++;
            }
        }
        System.out.println("Max row index:"+maxRowIndex);
    }
}

package com.naresh.h_datastructures.j_matrix;
/*
Given a square matrix of integers, find the maximum value of M[c][d] - M[a][b] over every choice of indexes such that c > a and d > b in a single traversal of the matrix.
Input Matrix:

{  1,   2,  -1,  -4, -20 }
{ -8,  -3,   4,   2,   1 }
{  3,   8,   6,   1,   3 }
{ -4,  -1,   1,   7,  -6 }
{  0,  -4,  10,  -5,   1 }

Output: The maximum value is 18 as M[4][2] – M[1][0] has maximum difference.
 */
public class D4_FindMaxDifference extends MatrixUtils{
    public static void main(String[] args) {

        int[][] M =
                {
                        { 1, 2, -1, -4, -20 },
                        { -8, -3, 4, 2, 1 },
                        { 3, 8, 6, 1, 3 },
                        { -4, -1, 1, 7, -6 },
                        { 0, -4, 10, -5, 1 }
                };
//        findMaxDifference(M);
        findMax(M);
    }

    //O(n^4)
    private static void findMaxDifference(int[][] M) {
        int N = M.length;
        int max = Integer.MIN_VALUE;

        // Consider all possible pairs `M[a][b]` and `M[c][d]`
        for (int a = 0; a < N - 1; a++) {
            for (int b = 0; b < N - 1; b++) {
                for (int c = a + 1; c < N; c++) {
                    for (int d = b + 1; d < N; d++) {
                        if (max < (M[c][d] - M[a][b])) {
                            max = M[c][d] - M[a][b];
                        }
                    }
                }
            }
        }

        System.out.println("Max difference:"+max);
    }

    //TODO did not understand :)
    public static int findMax(int[][] M)
    {
        // base case
        if (M == null || M.length == 0) {
            return 0;
        }

        // get size of the matrix
        int n = M.length;

        // `K[i][j]` stores a maximum of elements in the matrix from `(i, j)`
        // to `(n-1, n-1)`
        int[][] K = new int[n][n];

        // last element of `K[][]` will be the same as that of the specified matrix
        K[n-1][n-1] = M[n-1][n-1];

        int max = M[n-1][n-1];    // Initialize max

        // preprocess the last row
        for (int j = n-2; j >= 0; j--)
        {
            if (M[n-1][j] > max) {
                max = M[n-1][j];
            }
            K[n-1][j] = max;
        }

        max = M[n-1][n-1];        // Initialize max

        // preprocess the last column
        for (int i = n-2; i >= 0; i--)
        {
            if (M[i][n-1] > max) {
                max = M[i][n-1];
            }
            K[i][n-1] = max;
        }
        System.out.println("K matrix:");;
        print(K);
        max = Integer.MIN_VALUE;        // Initialize max

        // preprocess the rest of the matrix from the bottom
        for (int i = n-2; i >= 0; i--)
        {
            for (int j = n-2; j >= 0; j--)
            {
                // update the max value
                if (K[i+1][j+1] - M[i][j] > max) {
                    max = K[i+1][j+1] - M[i][j];
                }

                // assign `K[i][j]`
                K[i][j] = Math.max(M[i][j], Math.max(K[i][j+1], K[i+1][j]));
            }
        }

        return max;
    }
}

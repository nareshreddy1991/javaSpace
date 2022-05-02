package com.naresh.h_datastructures.j_matrix;

/*
Find minimum cost to reach the last cell of a matrix from its first cell
Given an M × N matrix of integers where each cell has a cost associated with it,
find the minimum cost to reach the last cell (M-1, N-1) of the matrix from its first cell (0, 0).
 We can only move one unit right or one unit down from any cell, i.e., from cell (i, j), we can move to (i, j+1) or (i+1, j).

For example,

Minimum cost path in a matrix
           { 4, 7, 8, 6, 4 },
            { 6, 7, 3, 9, 2 },
            { 3, 8, 1, 2, 4 },
            { 7, 1, 7, 3, 7 },
            { 2, 9, 8, 9, 3 }

Ans)4 6 7 3 1 2 3 73
 */
public class G_FindMinCostRecursion {
    public static void main(String[] args) {
        int[][] cost =
                {
                        {4, 7, 8, 6, 4},
                        {6, 7, 3, 9, 2},
                        {3, 8, 1, 2, 4},
                        {7, 1, 7, 3, 7},
                        {2, 9, 8, 9, 3}
//                        {1, 3, 1},
//                        {2, 3, 2},
//                        {4, 3, 1}
                };

        System.out.println("The minimum cost is " + findMinCost(cost, cost.length, cost[0].length));
        System.out.println("The minimum cost is " + findMinCost2(cost, cost.length-1, cost[0].length-1));
        System.out.println("The minimum cost is top down:" + findMinCost3(cost, 0, 0));
        System.out.println("The minimum cost is diagonal allowed" + minCost(cost, cost.length-1, cost[0].length-1));
    }

    /*
    bottom up approach
    Formula= cost of current element + min( cost of top element, cost of left element)
     */
    private static int findMinCost(int[][] a, int m, int n) {
        if (m == 0 || n == 0) {
            return Integer.MAX_VALUE;
        }
        if (m == 1 && n == 1)
            return a[0][0];
        return Integer.min(findMinCost(a, m, n - 1), findMinCost(a, m - 1, n)) + a[m - 1][n - 1];
    }

    /*pass m, n as position
    TODO preferred solution in recursion*/
    private static int findMinCost2(int[][] a, int m, int n) {
        if (m < 0 || n < 0)
            return Integer.MAX_VALUE;
        if (m == 0 && n == 0)
            return a[m][n];
        return Integer.min(
                findMinCost2(a, m, n - 1),
                findMinCost2(a, m - 1, n)
        ) + a[m][n];
    }

    //top down
    //Formula= cost of current element + min( cost of right element, cost of bottom element)
    private static int findMinCost3(int[][] a, int i, int j) {
        int M = a.length;
        int N = a[0].length;
        if (i >= M || j >= N)
            return Integer.MAX_VALUE;
        if (i == M - 1 && j == N - 1)
            return a[i][j];
        return Integer.min(
                findMinCost3(a, i, j + 1),
                findMinCost3(a, i + 1, j)
        ) + a[i][j];
    }

    static int minCost(int cost[][], int m,
                       int n) {
        if (n < 0 || m < 0)
            return Integer.MAX_VALUE;
        else if (m == 0 && n == 0)
            return cost[m][n];
        else
            return cost[m][n] +
                    min(
                            minCost(cost, m - 1, n),
                            minCost(cost, m, n - 1),
                            minCost(cost, m - 1, n - 1));//TODO diagonal moves are allowed
    }

    static int min(int x, int y, int z) {
        if (x < y)
            return (x < z) ? x : z;
        else
            return (y < z) ? y : z;
    }
}

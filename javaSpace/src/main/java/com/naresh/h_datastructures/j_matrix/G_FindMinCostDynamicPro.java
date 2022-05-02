package com.naresh.h_datastructures.j_matrix;

public class G_FindMinCostDynamicPro extends MatrixUtils {
    public static void main(String[] args) {
        int[][] cost =
                {
                        {4, 7, 8, 6, 4},
                        {6, 7, 3, 9, 2},
                        {3, 8, 1, 2, 4},
                        {7, 1, 7, 3, 7},
                        {2, 9, 8, 9, 3}
                };
        int minCost = findMinCost(cost);
        System.out.println("Min cost" + minCost);
    }

    private static int findMinCost(int[][] a) {
        int M = a.length;
        int N = a[0].length;
        int[][] cost = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0) {
                    if (j == 0)
                        cost[i][j] = a[i][j];
                    else
                        cost[i][j] = a[i][j] + cost[i][j - 1];

                } else if (j == 0) {
                    cost[i][j] = a[i][j] + cost[i - 1][j];
                } else {
                    cost[i][j] = Integer.min(cost[i - 1][j], cost[i][j - 1]) + a[i][j];//TODO for diagonal just add one more value
                }
            }
        }
        print(cost);
        return cost[M - 1][N - 1];
    }
}

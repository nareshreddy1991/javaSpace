package com.naresh.h_datastructures.j_matrix;

import java.util.Arrays;
import java.util.stream.Collectors;

public class G1_FindMinTravelCost {

    // DP function to calculate the minimum cost to reach the destination city `n`
    // from the source city 0
    public static int findMinCost(int[][] cost) {
        // base case
        if (cost == null || cost.length == 0) {
            return 0;
        }

        // `N × N` matrix
        int N = cost.length;

        // `lookup[i]` stores the minimum cost to reach city `i` from city 0
        int[] lookup = new int[N];

        // Initialize `lookup[]` with the direct ticket price from the source city
        for (int i = 0; i < N; i++) {
            lookup[i] = cost[0][i];
        }

        // repeat loop till `lookup[]` is filled with all minimum values
        boolean isFilled = false;
        while (!isFilled) {
            isFilled = true;
            System.out.println("Outer Iteration:");
            // fill `lookup[]` in a bottom-up manner
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (lookup[i] > lookup[j] + cost[j][i]) {
                        System.out.print("Condition satisfied for i" + i + "-j" + j + "Before:");
                        Arrays.stream(lookup).forEach(e -> System.out.print(e + ","));
                        lookup[i] = lookup[j] + cost[j][i];
                        System.out.print("::After:");
                        Arrays.stream(lookup).forEach(e -> System.out.print(e + ","));
                        System.out.println();
                        isFilled = false;
                    } else {
                        System.out.println("Condition failed for i" + i + "-j" + j);
                    }
                }
            }
        }

        // return the minimum cost to reach city `N-1` from city 0
        return lookup[N - 1];
    }

    public static void main(String[] args) {
        int[][] cost =
                {
                        {0, 20, 30, 100},
                        {20, 0, 15, 75},
                        {30, 15, 0, 50},
                        {100, 75, 50, 0}
                };

        System.out.print("The minimum cost is " + findMinCost(cost));
    }
}

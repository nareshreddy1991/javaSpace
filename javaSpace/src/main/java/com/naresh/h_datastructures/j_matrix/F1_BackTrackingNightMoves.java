package com.naresh.h_datastructures.j_matrix;

import java.util.Arrays;

public class F1_BackTrackingNightMoves extends MatrixUtils {
    static int count=0;
    public static void main(String[] args) {
        int N = 8;
        int[][] a = new int[N][N];
//        Arrays.fill(a, -1); work only for 1D array
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = -1;
            }
        }
        nightMoves(a);
    }

    private static void nightMoves(int[][] a) {
        int rMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int cMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        a[0][0] = 0;
        if (nightMoveRecursion(0, 0, 1, a, rMove, cMove)) {
            print(a);
            System.out.println("count:"+count);
        } else {
            System.out.println("No tour found");
        }
    }

    private static boolean nightMoveRecursion(int r, int c, int position, int[][] a, int[] rMove, int[] cMove) {
        if (position == 8 * 8) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            int rNext = r + rMove[i];
            int cNext = c + cMove[i];
            if (isSafe(rNext, cNext, a)) {
                a[rNext][cNext] = position;
                count++;
//                System.out.println("moved"+rNext+"-"+cNext);
                if (nightMoveRecursion(rNext, cNext, position + 1, a, rMove, cMove)) {
                    return true;
                } else {
                    a[rNext][cNext] = -1;
//                    System.out.println("reverted"+rNext+"-"+cNext);
                }

            }
        }
        return false;
    }

    private static boolean isSafe(int r, int c, int[][] a) {
        int N = a.length;
        if (r >= 0 && r < N && c >= 0 && c < N && a[r][c] == -1) return true;
        return false;
    }
}

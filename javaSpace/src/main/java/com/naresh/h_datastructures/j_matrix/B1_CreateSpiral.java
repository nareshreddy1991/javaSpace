package com.naresh.h_datastructures.j_matrix;

public class B1_CreateSpiral extends MatrixUtils {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        int[][] result = createSpiral2(input);
        print(result);
    }

    /*
    This approach requires extra space to store visited nodes
     */
    private static int[][] createSpiral(int[] a) {
        int N = (int) Math.sqrt(a.length);
        int[][] result = new int[N][N];
        boolean[][] seen = new boolean[N][N];
        int[] rd = {0, 1, 0, -1};
        int[] cd = {1, 0, -1, 0};
        int c = 0, r = 0, di = 0;
        for (int i = 0; i < N * N; i++) {
            result[r][c] = a[i];
            seen[r][c] = true;
            int cr = r + rd[di];
            int cc = c + cd[di];
            if (0 <= cr && cr < N && cc < N && 0 <= cc
                    && !seen[cr][cc]) {//TODO 0<= cr/cc it can be zero
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r = r + rd[di];
                c = c + cd[di];
            }
        }
        return result;
    }

    /*
                c
                |
            r-->1 2 3 4
                5 6 7 8
                9 1 2 3
                4 5 6 7
           m--->4        |4
                         n

     */
    private static int[][] createSpiral2(int[] a) {
        int N = (int) Math.sqrt(a.length);
        int[][] result = new int[N][N];
        int r = 0, c = 0, m = N, n = N, k = 0;
        while (r < m && c < n) {
            for (int i = c; i < n; i++) {
                result[r][i] = a[k++];
            }
            r++;
            for (int i = r; i < m; i++) {
                result[i][n-1] = a[k++];
            }
            n--;
            if (r < m) {
                for (int i = n - 1; i >= c; i--) {
                    result[m - 1][i] = a[k++];
                }
                m--;
            }
            if (c < n) {
                for (int i = m - 1; i >= r; i--) {
                    result[i][c] = a[k++];
                }
                c++;
            }
        }
        return result;
    }
}

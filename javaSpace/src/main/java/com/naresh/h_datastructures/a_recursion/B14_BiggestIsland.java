package com.naresh.h_datastructures.a_recursion;

public class B14_BiggestIsland {
    static int N = 4;

    public static void main(String[] args) {
        int graph[][] = new int[][]{

                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0}
        };
        int maxArea = findMaxIsland(graph, 5);
        System.out.println("maxArea:" + maxArea);
        int count = countIslands(graph, 5);
        System.out.println("count:" + count);

    }

    public static int findMaxIsland(int[][] a, int N) {
        int[][] visited = new int[N][N];
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int currentArea = maxArea(a, i, j, visited, N);
                maxArea = Integer.max(currentArea, maxArea);
            }
        }
        return maxArea;
    }

    public static int countIslands(int[][] a, int N) {
        int[][] visited = new int[N][N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count += countIslands(a, i, j, visited, N);
            }
        }
        return count;
    }

    public static int maxArea(int[][] a, int i, int j, int[][] visited, int N) {
        if (i < 0 || j < 0 || i >= N || j >= N || visited[i][j] == 1 || a[i][j] == 0) {
            return 0;
        }
        visited[i][j] = 1;
        return 1 + maxArea(a, i, j + 1, visited, N)
                + maxArea(a, i + 1, j, visited, N)
                + maxArea(a, i, j - 1, visited, N)
                + maxArea(a, i - 1, j, visited, N);
    }

    public static int countIslands(int[][] a, int i, int j, int[][] visited, int N) {
        if (i < 0 || j < 0 || i >= N || j >= N || visited[i][j] == 1 || a[i][j] == 0) {
            return 0;
        }
        visited[i][j] = 1;
        countIslands(a, i, j + 1, visited, N);
        countIslands(a, i + 1, j, visited, N);
        countIslands(a, i, j - 1, visited, N);
        countIslands(a, i - 1, j, visited, N);
        return 1;
    }

    /*public static int biggestIsland(int[][] a, int row, int col, int[][] visited, int currentCount, int maxCount) {
        boolean isCompleted = true;
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                if (a[m][n] == 0) {
                    isCompleted = false;
                    break;
                }
            }
        }
        if (isCompleted)
            return 0;

        int[] rd = {0, 1, 0, -1, -1, 1, 1, -1};
        int[] cd = {1, 0, -1, 0, 1, 1, -1, -1};

        for (int i = row; i < N; i++) {
            for (int j = col; j < N; j++) {
                if (visited[i][j] != 1) {
                    if (a[i][j] == 0) {
                        visited[i][j] = 1;
                        continue;
                    } else {
                        int count = 0;
                        for (int k = 0; k < 8; k++) {
                            int nr = i + rd[k];
                            int nc = j + rd[k];
                            if ((nr >= 0 && nc >= 0) && nr > N && nc > N && visited[nr][nc] != 1) {
                                if (a[nr][nc] == 1) {
                                    visited[nr][nc] = 1;
                                    biggestIsland(a, nr, nc, visited, currentCount + 1, maxCount);
                                }
                            }
                        }
                        return Integer.max(currentCount, maxCount);
                    }
                }
            }
        }

    }*/
}

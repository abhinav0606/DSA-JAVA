package Striver.DynamicProgramming;

import java.util.Arrays;
import java.util.Map;

public class GridsDP {
    public static void main(String[] args) {

    }

    // Recursion
    public int uniquePathFuncRec(int m, int n) {
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        int up = uniquePathFuncRec(m - 1, n);
        int left = uniquePathFuncRec(m, n - 1);
        return up + left;
    }

    public int uniquePathsRec(int m, int n) {
        return uniquePathFuncRec(m - 1, n - 1);
    }

    // Memoization
    public int uniquePathFuncMem(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        if (dp[m][n] != -1) return dp[m][n];
        int up = uniquePathFuncRec(m - 1, n);
        int left = uniquePathFuncRec(m, n - 1);
        return dp[m][n] = up + left;
    }

    public int uniquePathsMem(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] rows : dp) Arrays.fill(rows, -1);
        return uniquePathFuncMem(m - 1, n - 1, dp);
    }

    public int uniquePathFuncTab(int m, int n, int[][] dp) {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                dp[i][j] = up + left;
            }
        }
        return dp[m][n];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return uniquePathFuncTab(m - 1, n - 1, dp);
    }

    // Recursion
    public int uniquePathsWithObstaclesRec(int i, int j, int[][] matrix) {
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;
        if (i > 0 && j > 0 && matrix[i][j] == 1) return 0;
        int up = uniquePathsWithObstaclesRec(i - 1, j, matrix);
        int left = uniquePathsWithObstaclesRec(i, j - 1, matrix);
        return up + left;
    }

    public int uniquePathsWithObstacles(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        return uniquePathsWithObstaclesRec(m - 1, n - 1, matrix);
    }

    // Tabulation
    public int uniquePathsWithObstaclesMem(int i, int j, int[][] matrix, int[][] dp) {
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;
        if (i > 0 && j > 0 && matrix[i][j] == 1) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int up = uniquePathsWithObstaclesMem(i - 1, j, matrix, dp);
        int left = uniquePathsWithObstaclesMem(i, j - 1, matrix, dp);
        return dp[i][j] = up + left;
    }

    public int uniquePathsWithObstaclesMem(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int[] rows : dp) Arrays.fill(rows, -1);
        return uniquePathsWithObstaclesMem(m - 1, n - 1, matrix, dp);
    }

    // Tabulation
    public int uniquePathsWithObstaclesTab(int m, int n, int[][] matrix, int[][] dp) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                dp[i][j] = up + left;
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstaclesTab(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        return uniquePathsWithObstaclesTab(m, n, matrix, dp);
    }

    // Recursion
    private int minFallingPathSumRec(int i, int j, int m, int[][] matrix) {
        if (j < 0 || j >= m) return (int) 1e9;
        if (i == 0) return matrix[0][j];
        int up = matrix[i][j] + minFallingPathSumRec(i - 1, j, m, matrix);
        int leftDiag = matrix[i][j] + minFallingPathSumRec(i - 1, j - 1, m, matrix);
        int rightDiag = matrix[i][j] + minFallingPathSumRec(i - 1, j + 1, m, matrix);
        return Math.min(up, Math.min(leftDiag, rightDiag));
    }

    public int minFallingPathSumRec(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int ans = minFallingPathSumRec(n - 1, i, m, matrix);
            min = Math.min(min, ans);
        }
        return min;
    }

    // Memoization
    private int minFallingPathSumMem(int i, int j, int m, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= m) return (int) 1e9;
        if (i == 0) return matrix[0][j];
        if (dp[i][j] != -1) return dp[i][j];
        int up = matrix[i][j] + minFallingPathSumMem(i - 1, j, m, matrix, dp);
        int leftDiag = matrix[i][j] + minFallingPathSumMem(i - 1, j - 1, m, matrix, dp);
        int rightDiag = matrix[i][j] + minFallingPathSumMem(i - 1, j + 1, m, matrix, dp);
        return dp[i][j] = Math.min(up, Math.min(leftDiag, rightDiag));
    }

    public int minFallingPathSumMem(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int min = Integer.MAX_VALUE;
        int[][] dp = new int[n][m];
        for (int[] rows : dp) Arrays.fill(rows, -1);
        for (int i = 0; i < m; i++) {
            int ans = minFallingPathSumMem(n - 1, i, m, matrix, dp);
            min = Math.min(min, ans);
        }
        return min;
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int up = matrix[i][j] + dp[i - 1][j];
                int leftDiag = matrix[i][j];
                if (j - 1 >= 0) {
                    leftDiag = leftDiag + dp[i - 1][j - 1];
                } else {
                    leftDiag = Integer.MAX_VALUE;
                }
                int rightDiag = matrix[i][j];
                if (j + 1 <= m - 1) {
                    rightDiag = rightDiag + dp[i - 1][j + 1];
                } else {
                    rightDiag = Integer.MAX_VALUE;
                }
                dp[i][j] = Math.min(up, Math.min(leftDiag, rightDiag));
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            min = Math.min(dp[n - 1][i], min);
        }
        return min;
    }

    // Recursion
    private int minTriangleSumRec(int i, int j, int[][] triangle, int n) {
        if (i == n - 1) return triangle[i][j];
        int down = triangle[i][j] + minTriangleSumRec(i + 1, j, triangle, n);
        int diagnol = triangle[i][j] + minTriangleSumRec(i + 1, j + 1, triangle, n);
        return Math.min(down, diagnol);
    }

    public int minTriangleSumRec(int[][] triangle) {
        int n = triangle.length;
        return minTriangleSumRec(0, 0, triangle, n);
    }

    // Memoization
    private int minTriangleSumMem(int i, int j, int[][] triangle, int n, int[][] dp) {
        if (i == n - 1) return triangle[i][j];
        if (dp[i][j] != -1) return dp[i][j];
        int down = triangle[i][j] + minTriangleSumMem(i + 1, j, triangle, n, dp);
        int diagnol = triangle[i][j] + minTriangleSumMem(i + 1, j + 1, triangle, n, dp);
        return Math.min(down, diagnol);
    }

    public int minTriangleSumMem(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        for (int[] rows : dp) Arrays.fill(rows, -1);
        return minTriangleSumMem(0, 0, triangle, n, dp);
    }

    // Tabulation
    private int minTriangleSumTab(int[][] triangle, int[][] dp, int n) {
        // Filling the last row with whatever triangle has
        for (int i = 0; i <= n - 1; i++) {
            dp[n - 1][i] = triangle[n - 1][i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle[i][j] + dp[i + 1][j];
                int diag = triangle[i][j] + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diag);
            }
        }
        return dp[0][0];
    }

    public int minTriangleSum(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        return minTriangleSumTab(triangle, dp, n);
    }

    private int cherryPickupRec(int i, int j1, int j2, int n, int m, int[][] matrix) {
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) return Integer.MIN_VALUE;
        if (i == n - 1) {
            if (j1 == j2) {
                return matrix[i][j1];
            } else {
                return matrix[i][j1] + matrix[i][j2];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int ans;
                if (j1 == j2) {
                    ans = matrix[i][j1] + cherryPickupRec(i + 1, j1 + di, j2 + dj, n, m, matrix);
                } else {
                    ans = matrix[i][j1] + matrix[i][j2] + cherryPickupRec(i + 1, j1 + di, j2 + dj, n, m, matrix);
                }
                max = Math.max(max, ans);
            }
        }
        return max;
    }

    public int cherryPickup(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        return cherryPickupRec(0, 0, m - 1, n, m, matrix);
    }

    // Mem
    private int cherryPickupMem(int i, int j1, int j2, int n, int m, int[][] matrix, int[][][] dp) {
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) return Integer.MIN_VALUE;
        if (i == n - 1) {
            if (j1 == j2) {
                return matrix[i][j1];
            } else {
                return matrix[i][j1] + matrix[i][j2];
            }
        }
        if (dp[i][j1][j2] != -1) return dp[i][j1][j2];
        int max = Integer.MIN_VALUE;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int ans;
                if (j1 == j2) {
                    ans = matrix[i][j1] + cherryPickupMem(i + 1, j1 + di, j2 + dj, n, m, matrix, dp);
                } else {
                    ans = matrix[i][j1] + matrix[i][j2] + cherryPickupMem(i + 1, j1 + di, j2 + dj, n, m, matrix, dp);
                }
                max = Math.max(max, ans);
            }
        }
        return dp[i][j1][j2] = max;
    }

    public int cherryPickupMem(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][][] dp = new int[n][m][m];
        for (int[][] row1 : dp) {
            for (int[] row2 : row1) {
                Arrays.fill(row2, -1);
            }
        }
        return cherryPickupMem(0, 0, m - 1, n, m, matrix, dp);
    }

    // Tabulation
    private int cherryPickupTabulation(int n, int m, int[][] matrix) {
        int[][][] dp = new int[n][m][m];
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 != j2) {
                    dp[n - 1][j1][j2] = matrix[n - 1][j1] + matrix[n - 1][j2];
                } else {
                    dp[n - 1][j1][j2] = matrix[n - 1][j1];
                }
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = Integer.MIN_VALUE;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int ans;
                            if (j1 == j2) {
                                ans = matrix[i][j1];
                            } else {
                                ans = matrix[i][j1] + matrix[i][j2];
                            }
                            if (j1 + di < 0 || j1 + di >= m || j2+dj < 0 || j2+dj >=m) {
                                ans = ans + Integer.MAX_VALUE;
                            } else {
                                ans = ans + dp[i+1][j1+di][j2+dj];
                            }
                            maxi = Math.max(maxi,ans);
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][m-1];
    }

    public int cherryPickupTabulation(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        return cherryPickupTabulation(n, m, matrix);
    }

}

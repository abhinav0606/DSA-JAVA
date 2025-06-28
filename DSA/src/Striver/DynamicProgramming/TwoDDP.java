package Striver.DynamicProgramming;

import java.util.Arrays;

public class TwoDDP {
    public static void main(String[] args) {

    }

    // Recursion
    private int ninjaTrainingREC(int day, int last, int[][] matrix) {
        if (day == 0) {
            int max = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    max = Math.max(max, matrix[0][i]);
                }
            }
            return max;
        }
        int max = 0;
        for (int i = 0; i < 3; i++) {
            if (i != last) {
                int activity = matrix[day][i] + ninjaTrainingREC(day - 1, i, matrix);
                max = Math.max(max, activity);
            }
        }
        return max;
    }

    public int ninjaTraining(int[][] matrix) {
        int days = matrix.length;
        return ninjaTrainingREC(days - 1, 3, matrix);
    }

    // Memoization
    private int ninjaTrainingMem(int day, int last, int[][] matrix, int[][] dp) {
        if (dp[day][last] != -1) return dp[day][last];
        if (day == 0) {
            int max = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    max = Math.max(max, matrix[0][i]);
                }
            }
            return dp[day][last] = max;
        }
        int max = 0;
        for (int i = 0; i < 3; i++) {
            if (i != last) {
                int activity = matrix[day][i] + ninjaTrainingREC(day - 1, i, matrix);
                max = Math.max(max, activity);
            }
        }
        return dp[day][last] = max;
    }

    public int ninjaTrainingMemoization(int[][] matrix) {
        int days = matrix.length;
        int[][] dp = new int[days][4];
        for (int[] rows : dp) {
            Arrays.fill(rows, -1);
        }
        return ninjaTrainingMem(days - 1, 3, matrix, dp);
    }

    public int ninjaTrainingTabulation(int[][] matrix) {
        int days = matrix.length;
        int[][] dp = new int[days][4];
        dp[0][0] = Math.max(matrix[0][1], matrix[0][2]);
        dp[0][1] = Math.max(matrix[0][0], matrix[0][2]);
        dp[0][2] = Math.max(matrix[0][0], matrix[0][1]);
        dp[0][3] = Math.max(matrix[0][0], Math.max(matrix[0][2], matrix[0][1]));
        for (int day = 1; day < days; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        int activity = matrix[day][task] + dp[day-1][last];
                        dp[day][last] = Math.max(activity,dp[day][last]);
                    }
                }
            }
        }
        return dp[days-1][3];
    }


}

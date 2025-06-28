package Striver.DynamicProgramming;

import java.util.Arrays;
import java.util.Map;

public class StocksDP {
    public static void main(String[] args) {

    }

    public int stockBuySell(int[] arr, int n) {
        int maxProfit = 0;
        int min = arr[0];
        for (int i = 1; i < n; i++) {
            int cprofit = arr[i] - min;
            maxProfit = Math.max(cprofit, maxProfit);
            min = Math.min(min, arr[i]);
        }
        return maxProfit;
    }

    // Recursion
    private int stockBuySell2Rec(int index, int buy, int n, int[] arr) {
        if (index == n) return 0;
        int profit = 0;
        if (buy == 0) {
            profit = Math.max(stockBuySell2Rec(index + 1, 0, n, arr), (-1 * arr[index]) + stockBuySell2Rec(index + 1, 1, n, arr));
        } else {
            profit = Math.max(stockBuySell2Rec(index + 1, 1, n, arr), arr[index] + stockBuySell2Rec(index + 1, 0, n, arr));
        }
        return profit;
    }

    public int stockBuySell2(int[] arr, int n) {
        if (n == 0) return 0;
        int ans = stockBuySell2Rec(0, 0, n, arr);
        return ans;
    }

    // Memoization
    private int stockBuySell2Mem(int index, int buy, int n, int[] arr, int[][] dp) {
        if (index == n) return 0;
        int profit = 0;
        if (dp[index][buy] != -1) return dp[index][buy];
        if (buy == 0) {
            profit = Math.max(stockBuySell2Mem(index + 1, 0, n, arr, dp), (-1 * arr[index]) + stockBuySell2Mem(index + 1, 1, n, arr, dp));
        } else {
            profit = Math.max(stockBuySell2Mem(index + 1, 1, n, arr, dp), arr[index] + stockBuySell2Mem(index + 1, 0, n, arr, dp));
        }
        return dp[index][buy] = profit;
    }

    public int stockBuySell2Mem(int[] arr, int n) {
        if (n == 0) return 0;
        int[][] dp = new int[n][2];
        for (int[] rows : dp) {
            Arrays.fill(rows, -1);
        }
        return stockBuySell2Mem(0, 0, n, arr, dp);
    }

    // Tabulation
    public int stockBuySell2Tab(int[] arr, int n) {
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;
        int profit = 0;
        for (int index = n - 1; index >= 0; index++) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) {
                    profit = Math.max(dp[index + 1][0], (-1 * arr[index]) + dp[index + 1][1]);
                } else {
                    profit = Math.max(dp[index + 1][1], arr[index] + dp[index + 1][0]);
                }
                dp[index][buy] = profit;
            }
        }
        return dp[0][0];
    }

    // Recursion
    private int stockBuySell3Rec(int index, int buy, int cap, int[] arr, int n) {
        if (index == n || cap == 0) return 0;
        int profit = 0;
        if (buy == 0) {
            profit = Math.max(stockBuySell3Rec(index + 1, 0, cap, arr, n), (-1 * arr[index]) + stockBuySell3Rec(index + 1, 1, cap, arr, n));
        } else {
            profit = Math.max(stockBuySell3Rec(index + 1, 1, cap, arr, n), arr[index] + stockBuySell3Rec(index + 1, 0, cap - 1, arr, n));
        }
        return profit;
    }

    public int stockBuySell3(int[] arr, int n) {
        if (n == 0) return 0;
        int ans = stockBuySell3Rec(0, 0, 2, arr, n);
        return ans;
    }

    // Memoization
    private int stockBuySell3Mem(int index, int buy, int cap, int[] arr, int n, int[][][] dp) {
        if (index == n || cap == 0) return 0;
        int profit = 0;
        if (dp[index][buy][cap] != -1) return dp[index][buy][cap];
        if (buy == 0) {
            profit = Math.max(stockBuySell3Mem(index + 1, 0, cap, arr, n, dp), (-1 * arr[index]) + stockBuySell3Mem(index + 1, 1, cap, arr, n, dp));
        } else {
            profit = Math.max(stockBuySell3Mem(index + 1, 1, cap, arr, n, dp), arr[index] + stockBuySell3Mem(index + 1, 0, cap - 1, arr, n, dp));
        }
        return dp[index][buy][cap] = profit;
    }

    public int stockBuySell3Mem(int[] arr, int n) {
        if (n == 0) return 0;
        int[][][] dp = new int[n][2][3];
        for (int[][] row : dp) {
            for (int[] row1 : row) {
                Arrays.fill(row1, -1);
            }
        }
        int ans = stockBuySell3Mem(0, 0, 2, arr, n, dp);
        return ans;
    }

    public int stockBuySell3Tab(int[] arr, int n) {
        if (n == 0) return 0;
        int[][][] dp = new int[n + 1][2][3];
        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 0) {
                        dp[index][buy][cap] = Math.max(dp[index + 1][0][cap], (-1 * arr[index]) + dp[index + 1][1][cap]);
                    } else {
                        dp[index][buy][cap] = Math.max(dp[index + 1][1][cap], arr[index] + dp[index + 1][0][cap - 1]);
                    }
                }
            }
        }
        return dp[0][0][2];
    }

    // Recursion
    private int stockBuySell4Rec(int index, int buy, int cap, int n, int[] arr) {
        if (index == n || cap == 0) return 0;
        int profit = 0;
        if (buy == 0) {
            profit = Math.max(stockBuySell4Rec(index + 1, 0, cap, n, arr), (-1 * arr[index]) + stockBuySell4Rec(index + 1, 1, cap, n, arr));
        } else {
            profit = Math.max(stockBuySell4Rec(index + 1, 1, cap, n, arr), arr[index] + stockBuySell4Rec(index + 1, 0, cap - 1, n, arr));
        }
        return profit;
    }

    public int stockBuySell4Rec(int[] arr, int n, int k) {
        if (n == 0) return 0;
        return stockBuySell4Rec(0, 0, k, n, arr);
    }

    // MEMOIZATION
    // Recursion
    private int stockBuySell4Mem(int index, int buy, int cap, int n, int[] arr, int[][][] dp) {
        if (index == n) return 0;
        int profit = 0;
        if (dp[index][buy][cap] != -1) return dp[index][buy][cap];
        if (buy == 0) {
            profit = Math.max(stockBuySell4Mem(index + 1, 0, cap, n, arr, dp), (-1 * arr[index]) + stockBuySell4Mem(index + 1, 1, cap, n, arr, dp));
        } else {
            profit = Math.max(stockBuySell4Mem(index + 1, 1, cap, n, arr, dp), arr[index] + stockBuySell4Mem(index + 1, 0, cap - 1, n, arr, dp));
        }
        return dp[index][buy][cap] = profit;
    }

    public int stockBuySell4Mem(int[] arr, int n, int k) {
        if (n == 0) return 0;
        int[][][] dp = new int[n][2][k + 1];
        for (int[][] row1 : dp) {
            for (int[] row2 : row1) {
                Arrays.fill(row2, -1);
            }
        }
        return stockBuySell4Mem(0, 0, k, n, arr, dp);
    }

    public int stockBuySell4Tab(int[] arr, int n, int k) {
        if (n == 0) return 0;
        int[][][] dp = new int[n + 1][2][k + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 0) {
                        dp[index][buy][cap] = Math.max(dp[index+1][0][cap],(-1*arr[index]) + dp[index+1][1][cap]);
                    } else {
                        dp[index][buy][cap] = Math.max(dp[index+1][1][cap],arr[index] + dp[index+1][0][cap-1]);
                    }
                }
            }
        }
        return dp[0][0][k];
    }

    // Recursion
    private int stockBuySell5Rec(int index, int buy, int fee, int n, int[] arr) {
        if (index == n) return 0;
        int profit = 0;
        if (buy == 0) {
            profit = Math.max(stockBuySell5Rec(index+1,0,fee,n,arr),(-1*arr[index]) + stockBuySell5Rec(index+1,1,fee,n,arr));
        } else {
            profit = Math.max(stockBuySell5Rec(index+1,1,fee,n,arr),(arr[index] - fee) + stockBuySell5Rec(index+1,0,fee,n,arr));
        }
        return profit;
    }
    public int stockBuySell5Rec(int[] arr, int n, int fee) {
        if (n == 0) return 0;
        return stockBuySell5Rec(0,0,fee,n,arr);
    }

    // Memoization
    private int stockBuySell5Mem(int index, int buy, int fee, int n, int[] arr,int[][] dp) {
        if (index == n) return 0;
        int profit = 0;
        if (dp[index][buy] != -1) return dp[index][buy];
        if (buy == 0) {
            profit = Math.max(stockBuySell5Mem(index+1,0,fee,n,arr,dp),(-1*arr[index]) + stockBuySell5Mem(index+1,1,fee,n,arr,dp));
        } else {
            profit = Math.max(stockBuySell5Mem(index+1,1,fee,n,arr,dp),(arr[index] - fee) + stockBuySell5Mem(index+1,0,fee,n,arr,dp));
        }
        return dp[index][buy]=profit;
    }
    public int stockBuySell5Mem(int[] arr, int n, int fee) {
        if (n == 0) return 0;
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row,-1);
        }
        return stockBuySell5Mem(0,0,fee,n,arr,dp);
    }
    public int stockBuySell5Tab(int[] arr, int n, int fee) {
        if (n == 0) return 0;
        int[][] dp = new int[n+1][2];
        for (int index = n-1;index <= 0;index++) {
            for (int buy = 0;buy <= 1;buy++) {
                if (buy == 0) {
                    dp[index][buy] = Math.max(dp[index+1][0],(-1*arr[index]) + dp[index+1][1]);
                } else {
                    dp[index][buy] = Math.max(dp[index+1][1],(arr[index] - fee) + dp[index+1][0]);
                }
            }
        }
        return dp[0][0];
    }
}

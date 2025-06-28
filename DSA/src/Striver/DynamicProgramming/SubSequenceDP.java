package Striver.DynamicProgramming;

import javax.naming.ldap.StartTlsRequest;
import java.util.Arrays;
import java.util.Map;

public class SubSequenceDP {
    public static void main(String[] args) {

    }

    private boolean isSubsetSumRec(int index, int target, int[] arr) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        boolean notTaken = isSubsetSumRec(index - 1, target, arr);
        boolean taken = false;
        if (arr[index] <= target) {
            taken = isSubsetSumRec(index - 1, target - arr[index], arr);
        }
        return notTaken || taken;
    }

    public boolean isSubsetSumRec(int[] arr, int target) {
        int n = arr.length;
        return isSubsetSumRec(n - 1, target, arr);
    }

    private boolean isSubsetSumMem(int index, int target, int[] arr, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        if (dp[index][target] != -1) {
            return dp[index][target] != 0;
        }
        boolean notTaken = isSubsetSumMem(index - 1, target, arr, dp);
        boolean taken = false;
        if (arr[index] <= target) {
            taken = isSubsetSumMem(index - 1, target - arr[index], arr, dp);
        }
        dp[index][target] = notTaken || taken ? 1 : 0;
        return notTaken || taken;
    }

    public boolean isSubsetSumMem(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return isSubsetSumMem(n - 1, target, arr, dp);
    }

    public boolean isSubsetSumTab(int[] arr, int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][target + 1];
        for (int index = 0; index < n; index++) {
            dp[index][0] = true;
        }
        // If the first element is less than equal to target then add that elements as true
        if (arr[0] <= target) {
            dp[0][arr[0]] = true;
        }
        for (int index = 1; index < n; index++) {
            for (int i = 1; i <= target; i++) {
                boolean notTaken = dp[index - 1][i];
                boolean taken = false;
                if (arr[index] <= i) {
                    taken = dp[index - 1][i - arr[index]];
                }
                dp[index][i] = notTaken || taken;
            }
        }
        return dp[n - 1][target];
    }

    private boolean equalPartitionRec(int index, int target, int n, int[] arr) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        boolean notTaken = equalPartitionRec(index - 1, target, n, arr);
        boolean taken = false;
        if (arr[index] <= target) {
            taken = equalPartitionRec(index - 1, target - arr[index], n, arr);
        }
        return notTaken || taken;
    }

    public boolean equalPartitionRec(int n, int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        if (totalSum % 2 == 1) return false;
        else {
            int k = totalSum / 2;
            return equalPartitionRec(n - 1, k, n, arr);
        }
    }

    // Memoization
    private boolean equalPartitionMem(int index, int target, int n, int[] arr, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        if (dp[index][target] != -1) {
            return dp[index][target] != 0;
        }
        boolean notTaken = equalPartitionMem(index - 1, target, n, arr, dp);
        boolean taken = false;
        if (arr[index] <= target) {
            taken = equalPartitionMem(index - 1, target - arr[index], n, arr, dp);
        }
        dp[index][target] = notTaken || taken ? 1 : 0;
        return notTaken || taken;
    }

    public boolean equalPartitionMem(int n, int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        if (totalSum % 2 == 1) return false;
        else {
            int k = totalSum / 2;
            int[][] dp = new int[n][k + 1];
            for (int[] row : dp) {
                Arrays.fill(row, -1);
            }
            return equalPartitionMem(n - 1, k, n, arr, dp);
        }
    }

    // Tabulation
    public boolean equalPartitionTab(int n, int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        if (totalSum % 2 == 1) return false;
        else {
            int k = totalSum / 2;
            boolean[][] dp = new boolean[n][k + 1];
            for (int index = 0; index < n; index++) {
                dp[index][0] = true;
            }
            if (arr[0] <= k) {
                dp[0][arr[0]] = true;
            }
            for (int index = 1; index < n; index++) {
                for (int target = 1; target <= k; target++) {
                    boolean notTaken = dp[index - 1][target];
                    boolean taken = false;
                    if (arr[index] <= target) {
                        taken = dp[index - 1][target - arr[index]];
                    }
                    dp[index][target] = taken || notTaken;
                }
            }
            return dp[n - 1][k];
        }
    }

    private boolean minDifferenceRec(int index, int target, int n, int[] arr) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        boolean notTaken = equalPartitionRec(index - 1, target, n, arr);
        boolean taken = false;
        if (arr[index] <= target) {
            taken = equalPartitionRec(index - 1, target - arr[index], n, arr);
        }
        return notTaken || taken;
    }

    public int minDifferenceRec(int[] arr, int n) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i <= totalSum; i++) {
            if (minDifferenceRec(n - 1, i, n, arr)) {
                int diff = Math.abs(i - (totalSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    // Memoization
    private boolean minDifferenceMem(int index, int target, int n, int[] arr, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        if (dp[index][target] != -1) return dp[index][target] != 0;
        boolean notTaken = minDifferenceMem(index - 1, target, n, arr, dp);
        boolean taken = false;
        if (arr[index] <= target) {
            taken = minDifferenceMem(index - 1, target - arr[index], n, arr, dp);
        }
        dp[index][target] = taken || notTaken ? 1 : 0;
        return notTaken || taken;
    }

    public int minDifferenceMem(int[] arr, int n) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        int[][] dp = new int[n][totalSum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i <= totalSum; i++) {
            minDifferenceMem(n - 1, i, n, arr, dp);
        }
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i <= totalSum; i++) {
            if (dp[n - 1][i] == 1) {
                int diff = Math.abs(i - (totalSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    public int minDifferenceTab(int[] arr, int n) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        boolean[][] dp = new boolean[n][totalSum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (arr[0] <= totalSum) {
            dp[0][arr[0]] = true;
        }
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= totalSum; target++) {
                boolean notTaken = dp[index - 1][target];
                boolean taken = false;
                if (arr[index] <= target) {
                    taken = dp[index - 1][target - arr[index]];
                }
                dp[index][target] = taken || notTaken;
            }
        }
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i <= totalSum; i++) {
            if (dp[n - 1][i]) {
                int diff = Math.abs(i - (totalSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

    // Recursion
    private int perfectSumRec(int index, int target, int n, int[] arr) {
        if (target == 0) return 1;
        if (index == 0) {
            return arr[0] == target ? 1 : 0;
        }
        int notTaken = perfectSumRec(index - 1, target, n, arr);
        int taken = 0;
        if (arr[index] <= target) {
            taken = perfectSumRec(index - 1, target - arr[index], n, arr);
        }
        return taken + notTaken;
    }

    public int perfectSumRec(int[] arr, int K) {
        int n = arr.length;
        return perfectSumRec(n - 1, K, n, arr);
    }

    // Memoization
    private int perfectSumMem(int index, int target, int n, int[] arr, int[][] dp) {
        if (target == 0) return 1;
        if (index == 0) {
            return arr[0] == target ? 1 : 0;
        }
        if (dp[index][target] != -1) return dp[index][target];
        int notTaken = perfectSumMem(index - 1, target, n, arr, dp);
        int taken = 0;
        if (arr[index] <= target) {
            taken = perfectSumMem(index - 1, target - arr[index], n, arr, dp);
        }
        return dp[index][target] = taken + notTaken;
    }

    public int perfectSumMem(int[] arr, int K) {
        int n = arr.length;
        int[][] dp = new int[n][K + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return perfectSumMem(n - 1, K, n, arr, dp);
    }

    public int perfectSumTab(int[] arr, int K) {
        int n = arr.length;
        int[][] dp = new int[n][K + 1];
        for (int index = 0; index < n; index++) {
            dp[index][0] = 1;
        }
        if (arr[0] <= K) {
            dp[0][arr[0]] = 1;
        }
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= K; target++) {
                int notTaken = dp[index - 1][target];
                int taken = 0;
                if (arr[index] <= target) {
                    taken = dp[index - 1][target - arr[index]];
                }
                dp[index][target] = taken + notTaken;
            }
        }
        return dp[n - 1][K];
    }

    private int countPartitionsRec(int index, int target, int n, int[] arr) {
        if (index == 0) {
            if (target == 0 && arr[0] == 0) return 2;
            else if (target == 0 || target == arr[0]) return 1;
        }
        int notTaken = countPartitionsRec(index-1,target,n,arr);
        int taken = 0;
        if (arr[index] <= target) {
            taken = countPartitionsRec(index-1,target-arr[index],n,arr);
        }
        return taken+notTaken;
    }

    public int countPartitionsRec(int n, int diff, int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        int s = totalSum - diff;
        if (s < 0) return 0;
        if (s % 2 == 1) return 0;
        return countPartitionsRec(n-1,s/2,n,arr);
    }

    private int countPartitionsMem(int index, int target, int n, int[] arr,int[][] dp) {
        if (index == 0) {
            if (target == 0 && arr[0] == 0) return 2;
            else if (target == 0 || target == arr[0]) return 1;
        }
        if (dp[index][target] != -1) {
            return dp[index][target];
        }
        int notTaken = countPartitionsMem(index-1,target,n,arr,dp);
        int taken = 0;
        if (arr[index] <= target) {
            taken = countPartitionsMem(index-1,target-arr[index],n,arr,dp);
        }
        return dp[index][target] = taken+notTaken;
    }

    public int countPartitionsMem(int n, int diff, int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        int s = totalSum - diff;
        if (s < 0) return 0;
        if (s % 2 == 1) return 0;
        s = s/2;
        int[][] dp = new int[n][s+1];
        for (int[] rows : dp) {
            Arrays.fill(rows,-1);
        }
        return countPartitionsMem(n-1,s/2,n,arr,dp);
    }
    public int countPartitionsTab(int n, int diff, int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = totalSum + arr[i];
        }
        int s = totalSum - diff;
        if (s < 0) return 0;
        if (s % 2 == 1) return 0;
        s = s/2;
        int[][] dp = new int[n][s+1];
        if (arr[0] == 0) {
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;
        }
        if (arr[0] != 0 && arr[0] <= s) dp[0][arr[0]] = 1;
        for (int index = 1; index<n;index++) {
            for (int target = 0;target <= s;target++) {
                int notTaken = dp[index-1][target];
                int taken = 0;
                if (arr[index] <= target) {
                    taken = dp[index-1][target-arr[index]];
                }
                dp[index][target]  = taken + notTaken;
            }
        }
        return dp[n-1][s];
    }
    // Recursion
    private int knapsack01rec(int index, int weight, int[] wt, int[] val) {
        if (weight == 0 || index < 0) return 0;
        int notTaken = knapsack01rec(index-1,weight,wt,val);
        int taken = 0;
        if (wt[index] <= weight) {
            taken = val[index] + knapsack01rec(index-1,weight-wt[index],wt,val);
        }
        return Math.max(taken,notTaken);
    }
    public int knapsack01rec(int[] wt, int[] val, int n, int W) {
        return knapsack01rec(n-1,W,wt,val);
    }

    private int knapsack01Mem(int index, int weight, int[] wt, int[] val,int[][] dp) {
        if (weight == 0 || index < 0) return 0;
        if (dp[index][weight] != -1) return dp[index][weight];
        int notTaken = knapsack01Mem(index-1,weight,wt,val,dp);
        int taken = 0;
        if (wt[index] <= weight) {
            taken = val[index] + knapsack01Mem(index-1,weight-wt[index],wt,val,dp);
        }
        return dp[index][weight] = Math.max(taken,notTaken);
    }
    public int knapsack01Mem(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n][W+1];
        for (int[] row : dp) {
            Arrays.fill(row,-1);
        }
        return knapsack01Mem(n-1,W,wt,val,dp);
    }

    public int knapsack01Tab(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n][W+1];
        for (int i = wt[0];i <= W;i++) {
            dp[0][i] = val[0];
        }
        for (int index = 1;index<n;index++) {
            for (int cap = 0;cap<=W;cap++) {
                int notTaken = dp[index-1][cap];
                int taken = 0;
                if (wt[index] <= cap) {
                    taken = val[index] + dp[index-1][cap-wt[index]];
                }
                dp[index][cap] = Math.max(taken,notTaken);
            }
        }
        return dp[n-1][W];
    }
    public int MinimumCoinsRec(int index, int target, int n, int[] arr) {
        if (index == 0) {
            if (target % arr[index] == 0) return target/arr[0];
            else return (int) 1e9;
        }
        int notTaken = MinimumCoinsRec(index-1, target, n, arr);
        int taken = (int) 1e9;
        if (arr[index] <= target) {
            // Because we can repeat that coin thats why index not index-1
            taken = 1 + MinimumCoinsRec(index,target-arr[index],n,arr);
        }
        return Math.min(taken,notTaken);
    }
    public int MinimumCoinsRec(int[] coins, int amount) {
        int n = coins.length;
        int ans = MinimumCoinsRec(n-1,amount,n,coins);
        if (ans >= (int)1e9) return -1;
        return ans;
    }
    public int MinimumCoinsMem(int index, int target, int n, int[] arr,int[][] dp) {
        if (index == 0) {
            if (target % arr[index] == 0) return target/arr[0];
            else return (int) 1e9;
        }
        if (dp[index][target] != -1) return dp[index][target];
        int notTaken = MinimumCoinsMem(index-1, target, n, arr,dp);
        int taken = (int) 1e9;
        if (arr[index] <= target) {
            taken = 1 + MinimumCoinsMem(index,target-arr[index],n,arr,dp);
        }
        return dp[index][target] = Math.min(taken,notTaken);
    }
    public int MinimumCoinsMem(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for (int[] row : dp) Arrays.fill(row,-1);
        int ans = MinimumCoinsMem(n-1,amount,n,coins,dp);
        if (ans >= (int)1e9) return -1;
        return ans;
    }

    public int MinimumCoinsTab(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for (int i=0;i<amount;i++) {
            if (i%coins[0] == 0) {
                dp[0][i] = i/coins[0];
            } else {
                dp[0][i] = (int) 1e9;
            }
        }
        for (int index = 1;index<n;index++) {
            for (int am = 0;am<= amount;am++) {
                int notTaken = dp[index-1][am];
                int taken = (int) 1e9;
                if (coins[index] <= am) {
                    taken = 1 + dp[index][am - coins[index]];
                }
                dp[index][am] = Math.min(taken,notTaken);
            }
        }
        return dp[n-1][amount];
    }
    // Recursion
    public int targetSumRec(int index, int target, int n,int[] nums) {
        if (index == 0) {
            if (target == 0 && nums[0] == 0) return 2;
            else if (target == 0 || target == nums[0]) return 1;
            else return 0;
        }
        int notTaken = targetSumRec(index-1,target,n,nums);
        int taken = 0;
        if (nums[index] <= target) {
            taken = targetSumRec(index-1,target-nums[index],n,nums);
        }
        return taken+notTaken;
    }
    public int targetSumRec(int n, int target, int[] nums) {
        int totalSum = 0;
        for (int i=0;i<n;i++) {
            totalSum = totalSum + nums[i];
        }
        int s = totalSum - target;
        if (s < 0) return 0;
        if (s % 2 == 1) return 0;
        s = s/2;
        return targetSumRec(n-1,s,n,nums);
    }
    public int targetSumMem(int index, int target, int n,int[] nums,int[][] dp) {
        if (index == 0) {
            if (target == 0 && nums[0] == 0) return 2;
            else if (target == 0 || target == nums[0]) return 1;
            else return 0;
        }
        if (dp[index][target] != -1) return dp[index][target];
        int notTaken = targetSumMem(index-1,target,n,nums,dp);
        int taken = 0;
        if (nums[index] <= target) {
            taken = targetSumMem(index-1,target-nums[index],n,nums,dp);
        }
        return dp[index][target] = taken+notTaken;
    }
    public int targetSumMem(int n, int target, int[] nums) {
        int totalSum = 0;
        for (int i=0;i<n;i++) {
            totalSum = totalSum + nums[i];
        }
        int s = totalSum - target;
        if (s < 0) return 0;
        if (s % 2 == 1) return 0;
        s = s/2;
        int[][] dp = new int[n][s+1];
        for (int[] row : dp) Arrays.fill(row,-1);
        return targetSumMem(n-1,s,n,nums,dp);
    }

    public int targetSumTab(int n, int target, int[] nums) {
        int totalSum = 0;
        for (int i=0;i<n;i++) {
            totalSum = totalSum + nums[i];
        }
        int s = totalSum - target;
        if (s < 0) return 0;
        if (s % 2 == 1) return 0;
        s = s/2;
        int[][] dp = new int[n][s+1];
        if (nums[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;
        if (nums[0] != 0 && nums[0] <= s) dp[0][nums[0]] = 1;
        for (int index = 1;index<n;index++){
            for (int cap = 0;cap<=s;cap++) {
                int notTaken = dp[index-1][cap];
                int taken = 0;
                if (nums[index] <= cap) {
                    taken = dp[index-1][target-nums[index]];
                }
                dp[index][cap] = taken + notTaken;
            }
        }
        return dp[n-1][s];
    }
    // Recursion
    private int countRec(int index, int target, int[] coins) {
        if (index == 0) {
            return target % coins[0] == 0 ? 1 : 0;
        }
        int notTaken = countRec(index-1,target,coins);
        int taken = 0;
        if (coins[index] <= target) {
            taken = countRec(index,target-coins[index],coins);
        }
        return taken + notTaken;
    }
    public int countRec(int[] coins, int N, int amount) {
        return countRec(N-1,amount,coins);
    }

    private int countMem(int index, int target, int[] coins,int[][] dp) {
        if (index == 0) {
            return target % coins[0] == 0 ? 1 : 0;
        }
        if (dp[index][target] != -1) return dp[index][target];
        int notTaken = countMem(index-1,target,coins,dp);
        int taken = 0;
        if (coins[index] <= target) {
            taken = countMem(index,target-coins[index],coins,dp);
        }
        return dp[index][target] = taken + notTaken;
    }
    public int countMem(int[] coins, int N, int amount) {
        int[][] dp = new int[N][amount+1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return countMem(N-1,amount,coins,dp);
    }
    public int countTab(int[] coins, int N, int amount) {
            int[][] dp = new int[N][amount+1];
            for (int am = 0;am <= amount;am++) {
                if (am % coins[0] == 0) dp[0][am] = 1;
            }
            for (int index = 1; index < N; index++) {
                for (int target = 0;target <= amount; target++) {
                    int notTaken = dp[index-1][target];
                    int taken = 0;
                    if (coins[index] <= target) {
                        taken = dp[index][target-coins[index]];
                    }
                    dp[index][target] = taken + notTaken;
                }
            }
            return dp[N-1][amount];
    }

    private int unboundedKnapsackRec(int index, int w, int[] wt, int[] val) {
        if (index == 0) {
            return (wt[0]/w)*val[0];
        }
        int notTaken = unboundedKnapsackRec(index-1,w,wt,val);
        int taken = Integer.MIN_VALUE;
        if (wt[index] <= w) {
            taken = val[index] + unboundedKnapsackRec(index,w-wt[index],wt,val);
        }
        return Math.max(taken,notTaken);
    }
    public int unboundedKnapsackRec(int[] wt, int[] val, int n, int W) {
        return unboundedKnapsackRec(n-1,W,wt,val);
    }

    private int unboundedKnapsackMem(int index, int w, int[] wt, int[] val,int[][] dp) {
        if (index == 0) {
            return (wt[0]/w)*val[0];
        }
        if (dp[index][w] != -1) return dp[index][w];
        int notTaken = unboundedKnapsackMem(index-1,w,wt,val,dp);
        int taken = Integer.MIN_VALUE;
        if (wt[index] <= w) {
            taken = val[index] + unboundedKnapsackMem(index,w-wt[index],wt,val,dp);
        }
        return dp[index][w] = Math.max(taken,notTaken);
    }
    public int unboundedKnapsackMem(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n][W+1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return unboundedKnapsackMem(n-1,W,wt,val,dp);
    }

    public int unboundedKnapsackTab(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n][W+1];
        for (int i = wt[0];i<=W;i++) {
            dp[0][i] = (i/wt[0]) * val[0];
        }
        for (int index = 1;index<n;index++) {
            for (int w = 0;w<=W;w++) {
                int notTaken = dp[index-1][w];
                int taken = Integer.MIN_VALUE;
                if (wt[index] <= w) {
                    taken = dp[index][w-wt[index]];
                }
                dp[index][w] = Math.max(taken,notTaken);
            }
        }
        return dp[n-1][W];
    }
}


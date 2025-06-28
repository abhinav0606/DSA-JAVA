package Striver.DynamicProgramming;

import java.util.Arrays;

public class OneDDP {
    public static void main(String[] args) {
        fib(5);
    }
    // Recursion
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        return climbStairs(n-1) + climbStairs(n-2);
    }
    // Memoization
    private int funcMemoization(int n,int[] dp) {
        if (n <= 1) return 1;
        if (dp[n] != -1) return dp[n];
        return dp[n] = funcMemoization(n-1,dp) + funcMemoization(n-2,dp);
    }
    public int climbStairsMemoization(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return funcMemoization(n, dp);
    }
    //Tabulation
    public int climbStairsTabulation(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2;i<=n;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public int climbStairsSpace(int n) {
        int prev = 1;
        int prev2 = 1;
        for (int i=2;i<=n;i++) {
            int current = prev + prev2;
            prev2 = prev;
            prev = current;
        }
        return prev;
    }

    // Recursion
    private int solveFrogJumpRecursion(int[] heights, int index) {
        if (index == 0) return 0;
        int frogJump1 = solveFrogJumpRecursion(heights,index-1) + Math.abs(heights[index] - heights[index-1]);
        int frogJump2 = Integer.MAX_VALUE;
        if (index > 1) {
            frogJump2 = solveFrogJumpRecursion(heights,index-2) + Math.abs(heights[index] - heights[index-2]);
        }
        return Math.min(frogJump1,frogJump2);
    }
    public int frogJump(int[] heights) {
        int n = heights.length;
        return solveFrogJumpRecursion(heights, n-1);
    }

    // Memoization
    private int solveFrogJumpMem(int[] heights,int[] dp, int index) {
        if (index == 0) return 0;
        if (dp[index] != -1) return dp[index];
        int jump1 = solveFrogJumpMem(heights,dp,index-1) + Math.abs(heights[index] - heights[index-1]);
        int jump2 = Integer.MAX_VALUE;
        if (index > 1) {
            jump2 = solveFrogJumpMem(heights,dp,index-2) + Math.abs(heights[index] - heights[index-2]);
        }
        return dp[index] = Math.min(jump1,jump2);
    }
    public int frogJumpMem(int[] heights) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp,-1);
        return solveFrogJumpMem(heights,dp,heights.length-1);
    }

    // Tabulation
    public int frogJumpTab(int[] heights) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for (int i=1;i<heights.length;i++) {
            int jump1 = Math.abs(heights[i]-heights[i-1]) + dp[i-1];
            int jump2 = Integer.MAX_VALUE;
            if (i > 1) {
                jump2 = Math.abs(heights[i]-heights[i-2]) + dp[i-2];
            }
            dp[i] = Math.min(jump1,jump2);
        }
        return dp[heights.length-1];
    }

    // Recursion
    private int solverFrogJump2Rec(int[] heights, int index, int k) {
        if (index == 0) return 0;
        int mm = Integer.MAX_VALUE;
        for (int i=1;i<=k;i++) {
            if (index-i>=0){
                int jump = solverFrogJump2Rec(heights,index-i,k) + Math.abs(heights[index]-heights[index-i]);
                mm = Math.min(jump,mm);
            }
        }
        return mm;
    }
    public int frogJump2(int[] heights, int k) {
        return solverFrogJump2Rec(heights,heights.length-1,k);
    }

    // Memoization
    private int solverFrogJump2Mem(int[] heights, int index, int k,int[] dp) {
        if (index == 0) return 0;
        if (dp[index] != -1) return dp[index];
        int mm = Integer.MAX_VALUE;
        for (int i=1;i<=k;i++) {
            if (index-i>=0){
                int jump = solverFrogJump2Mem(heights,index-i,k,dp) + Math.abs(heights[index]-heights[index-i]);
                mm = Math.min(jump,mm);
            }
        }
        dp[index] = mm;
        return mm;
    }
    public int frogJump2Mem(int[] heights, int k) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp,-1);
        return solverFrogJump2Mem(heights,heights.length-1,k,dp);
    }

    // Tabulation
    public int frogJump2Tab(int[] heights, int k) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for (int i=1;i<heights.length;i++) {
            int mm = Integer.MAX_VALUE;
            for (int j=1;j<=k;j++) {
                if (i-j>=0) {
                    int jump = dp[i - j] + Math.abs(heights[i]-heights[i-j]);
                    mm = Math.min(mm,jump);
                }
            }
            dp[i] = mm;
        }
        return dp[heights.length-1];
    }

    // Recursion
    private int nonAdjacentRec(int[] nums,int index) {
        if (index == 0) return nums[index];
        if (index < 0) return 0;
        // Pick
        int pick = nums[index] + nonAdjacentRec(nums,index-2);
        // Dont pick
        int noPick = nonAdjacentRec(nums,index-1);
        return Math.max(pick,noPick);
    }

    public int nonAdjacent(int[] nums) {
        int n = nums.length;
        return nonAdjacentRec(nums,n-1);
    }

    // Memoization

    private int nonAdjacentMem(int[] nums,int index, int[] dp) {
        if (index == 0) return nums[index];
        if (index < 0) return 0;
        if (dp[index] != -1) return dp[index];
        // Pick
        int pick = nums[index] + nonAdjacentMem(nums,index-2,dp);
        // Dont pick
        int noPick = nonAdjacentMem(nums,index-1,dp);
        return dp[index]=Math.max(pick,noPick);
    }

    public int nonAdjacentMem(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i=1;i<n;i++) {
            int pick = nums[i];
            if (i > 1) {
                pick = pick + dp[i-2];
            }
            int noPick = dp[i-1];
            dp[i] = Math.max(pick,noPick);
        }
        return dp[n-1];
    }

    // Recursion
    private int houseRobberRec(int[] arr, int index) {
        if (index == 0) return arr[index];
        if (index < 0) return 0;
        // pick
        int pick = arr[index] + houseRobberRec(arr,index-2);
        int noPick = houseRobberRec(arr,index-1);
        return Math.max(pick,noPick);
    }
    public int houseRobber(int[] money) {
        int n = money.length;
        if (n==0) return 0;
        if (n==1) return money[0];
        int[] arr1 = new int[n-1];
        int[] arr2 = new int[n-1];
        System.arraycopy(money, 0, arr1, 0, n - 2 + 1);
        System.arraycopy(money, 1, arr2, 0, n - 1);
        int ans1 = houseRobberRec(arr1,n-2);
        int ans2 = houseRobberRec(arr2,n-1);
        return Math.max(ans1,ans2);
    }

    // Memoization
    // Recursion
    private int houseRobberMem(int[] arr, int index, int[] dp) {
        if (index == 0) return arr[index];
        if (index < 0) return 0;
        if (dp[index] != -1) return dp[index];
        // pick
        int pick = arr[index] + houseRobberRec(arr,index-2);
        int noPick = houseRobberRec(arr,index-1);
        return dp[index] = Math.max(pick,noPick);
    }
    public int houseRobberMemoization(int[] money) {
        int n = money.length;
        if (n==0) return 0;
        if (n==1) return money[0];
        int[] arr1 = new int[n-1];
        int[] arr2 = new int[n-1];
        System.arraycopy(money, 0, arr1, 0, n - 2 + 1);
        System.arraycopy(money, 1, arr2, 0, n - 1);
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1,-1);
        Arrays.fill(dp2,-1);
        int ans1 = houseRobberMem(arr1,n-2,dp1);
        int ans2 = houseRobberMem(arr2,n-1,dp2);
        return Math.max(ans1,ans2);
    }

    public int houseRobberTabulation(int[] money) {
        int n = money.length;
        if (n==0) return 0;
        if (n==1) return money[0];
        int[] arr1 = new int[n-1];
        int[] arr2 = new int[n-1];
        System.arraycopy(money, 0, arr1, 0, n - 2 + 1);
        System.arraycopy(money, 1, arr2, 0, n - 1);
        int[] dp1 = new int[n-1];
        int[] dp2 = new int[n-1];
        dp1[0] = arr1[0];
        dp2[0] = arr2[0];
        for (int i=1;i<arr1.length;i++) {
            int pick = arr1[i];
            if (i>1) {
                pick = pick + arr1[i-2];
            }
            int noPick = arr1[i-1];
            dp1[i] = Math.max(pick,noPick);
        }

        for (int i=1;i<arr2.length;i++) {
            int pick = arr2[i];
            if (i>1) {
                pick = pick + dp2[i-2];
            }
            int noPick = dp2[i-1];
            dp2[i] = Math.max(pick,noPick);
        }
        return Math.max(dp1[n-2],dp2[n-2]);
    }

    public static int fibRec(int n) {
        if (n <= 1) return n;
        return fibRec(n-1) + fibRec(n-2);
    }
    public static void fib(int n) {
        int i=0;
        while (i <= n-1){
            System.out.print(fibRec(i) + " ");
            i++;
        }
    }

}

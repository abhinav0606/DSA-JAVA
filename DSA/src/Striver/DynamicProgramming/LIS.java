package Striver.DynamicProgramming;

import java.net.CookieHandler;
import java.util.*;

public class LIS {
    public static void main(String[] args) {
    }
    private int LISRec(int index, int prevIndex, int[] nums, int[][] dp) {
        if (index == nums.length-1) {
            if (prevIndex == -1 || nums[prevIndex] < nums[index]) return 1;
            return 0;
        }
        if (dp[index][prevIndex+1] != -1) return dp[index][prevIndex+1];
        int noTake = LISRec(index+1,prevIndex,nums,dp);
        int take = 0;
        if (prevIndex == -1) {
            take = 1 + LISRec(index+1,index,nums,dp);
        } else if (nums[prevIndex] < nums[index]) {
            take = 1 + LISRec(index+1,index,nums,dp);
        }
        return dp[index][prevIndex+1] = Math.max(take,noTake);
    }
    public int LIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n+1];
        for (int[] row : dp) Arrays.fill(row,-1);
        return LISRec(0,-1,nums,dp);
    }

    public List<Integer> longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int lastIndex = 0;
        int[] parent = new int[n];
        int maxLength = 0;
        List<Integer> ans = new ArrayList<>();
        Arrays.fill(dp,1);
        for (int i=0;i<n;i++) {
            parent[i] = i;
            for (int prev = 0;prev < i;prev++){
                if (arr[prev] <arr[i] && dp[i] < dp[prev] + 1) {
                    dp[i] = dp[prev] + 1;
                    parent[i] = prev;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        int i = lastIndex;
        while (parent[i] != i) {
            ans.add(i);
            i = parent[i];
        }
        ans.add(arr[i]);
        Collections.reverse(ans);
        return ans;
    }

    public List<Integer> largestDivisibleSubset(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int lastIndex = 0;
        int[] parent = new int[n];
        int maxLength = 0;
        List<Integer> ans = new ArrayList<>();
        Arrays.fill(dp,1);
        Arrays.sort(arr);
        for (int i=0;i<n;i++) {
            parent[i] = i;
            for (int prev = 0;prev < i;prev++){
                if (arr[i] % arr[prev] == 0 && dp[i] < dp[prev] + 1) {
                    dp[i] = dp[prev] + 1;
                    parent[i] = prev;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        int i = lastIndex;
        while (parent[i] != i) {
            ans.add(arr[i]);
            i = parent[i];
        }
        ans.add(arr[i]);
        Collections.reverse(ans);
        return ans;
    }

    public int longestStringChain(String[] words) {
        int n = words.length;
        Map<String,Integer> dp = new HashMap<>();
        int lastIndex = 0;
        int[] parent = new int[n];
        int maxLength = 0;
        List<Integer> ans = new ArrayList<>();
        String[] sortedWords = Arrays.copyOf(words, words.length);
        Arrays.sort(sortedWords, (a, b) -> a.length() - b.length());
        for (String word : sortedWords) {
            dp.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                String pred = word.substring(0, i) + word.substring(i + 1);
                if (dp.containsKey(pred) && dp.get(pred) + 1 > dp.get(word)) {
                    maxLength = Math.max(maxLength,dp.get(pred) + 1);
                    dp.put(word,dp.get(pred) + 1);
                }
            }
        }
        return maxLength;
    }

    private int leftSeq(int index, int prev, int[] arr) {
        if (index < 0) return 0;
        int include = 0;
        if (arr[prev] > arr[index]) {
            include = 1 + leftSeq(index-1,index,arr);
        }
        return Math.max(include,leftSeq(index-1,prev,arr));
    }
    private int rightSeq(int index, int prev, int[] arr) {
        if (index < 0) return 0;
        int include = 0;
        if (arr[prev] > arr[index]) {
            include = 1 + rightSeq(index+1,index,arr);
        }
        return Math.max(include,rightSeq(index+1,prev,arr));
    }

    public int LongestBitonicSequence(int[] arr) {
        int maxLength = 0;
        for (int i=1;i<arr.length-1;i++) {
            int leftLen = leftSeq(i-1,i,arr);
            int rightLen = rightSeq(i+1,i,arr);
            if (rightLen == 0 || leftLen == 0){
                rightLen = 0;
                leftLen = 0;
            }
            maxLength = Math.max(maxLength,rightLen+leftLen + 1);
        }
        return maxLength < 3 ? 0 : maxLength;
    }
    public int LongestBitonicSequenceTab(int[] arr) {
        int n = arr.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int maxLength = 0;
        Arrays.fill(dp1,1);
        Arrays.fill(dp2,1);
        for (int i=0;i<n;i++) {
            for (int prev = 0;prev < i;prev++){
                if (arr[prev] <arr[i] && dp1[i] < dp1[prev] + 1) {
                    dp1[i] = dp1[prev] + 1;
                }
            }
        }
        for (int i=n-1;i>=0;i--) {
            for (int prev = n-1;prev > i;prev--){
                if (arr[prev] <arr[i] && dp2[i] < dp2[prev] + 1) {
                    dp2[i] = dp2[prev] + 1;
                }
            }
        }
        for (int i=0;i<n;i++) {
            maxLength = Math.max(maxLength,dp1[i] + dp2[i] -1);
        }
        return maxLength;
    }

    public int numberOfLIS(int[] arr) {
        int n = arr.length;
        int[] dp1 = new int[n];
        int[] count = new int[n];
        int maxLength = 0;
        Arrays.fill(dp1,1);
        Arrays.fill(count,1);
        int ans = 0;
        for (int i=0;i<n;i++) {
            for (int prev = 0;prev < i;prev++){
                if (arr[prev] <arr[i]) {
                    if (dp1[i] < dp1[prev] + 1) {
                        dp1[i] = dp1[prev] + 1;
                    } else if (dp1[i] == dp1[prev] + 1) {
                        count[i] = count[i] + count[prev];
                    }
                }
            }
            maxLength = Math.max(maxLength,dp1[i]);
        }
        for (int i=0;i<n;i++) {
            if (maxLength == dp1[i]) {
                ans = ans + count[i];
            }
        }
        return ans;
    }
}

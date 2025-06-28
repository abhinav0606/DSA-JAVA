package Striver.DynamicProgramming;

import java.util.Arrays;
import java.util.Map;

public class StringDP {
    public static void main(String[] args) {

    }
    private int lcsRec(int i1, int i2, String s1, String s2) {
        if (i1 < 0 || i2 < 0) return 0;
        if (s1.charAt(i1) == s2.charAt(i2)) {
            return 1 + lcsRec(i1-1,i2-1,s1,s2);
        } else {
            return Math.max(lcsRec(i1-1,i2,s1,s2),lcsRec(i1,i2-1,s1,s2));
        }
    }
    public int lcs(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        return lcsRec(n-1,m-1,str1,str2);
    }
    private int lcsMem(int i1, int i2, String s1, String s2, int[][] dp) {
        if (i1 < 0 || i2 < 0) return 0;
        if (dp[i1][i2] != -1) return dp[i1][i2];
        if (s1.charAt(i1) == s2.charAt(i2)) {
            return dp[i1][i2] = 1 + lcsMem(i1-1,i2-1,s1,s2,dp);
        } else {
            return dp[i1][i2] = Math.max(lcsMem(i1-1,i2,s1,s2,dp),lcsMem(i1,i2-1,s1,s2,dp));
        }
    }
    public int lcsMem(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return lcsMem(n-1,m-1,str1,str2,dp);
    }
    public int lcsTab(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) {
            dp[i][0] = 0;
        }
        for (int i=0;i<=m;i++) {
            dp[0][i] = 0;
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=m;index2++) {
                if (str1.charAt(index1-1) == str2.charAt(index2-1)) {
                    dp[index1][index2] = 1 + dp[index1-1][index2-1];
                } else {
                    dp[index1][index2] = Math.max(dp[index1-1][index2],dp[index1][index2-1]);
                }
            }
        }
        return dp[n][m];
    }
    public int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        int ans = 0;
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=m;index2++) {
                if (str1.charAt(index1-1) == str2.charAt(index2-1)) {
                    dp[index1][index2] = 1 + dp[index1-1][index2-1];
                } else {
                    dp[index1][index2] = 0;
                }
                ans = Math.max(ans,dp[index1][index2]);
            }
        }
        return ans;
    }

    public int longestPalinSubseq(String s) {
        int n = s.length();
        String t = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<=n;i++) {
            dp[i][0] = 0;
        }
        for (int i=0;i<=n;i++) {
            dp[0][i] = 0;
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=n;index2++) {
                if (s.charAt(index1-1) == t.charAt(index2-1)) {
                    dp[index1][index2] = 1 + dp[index1-1][index2-1];
                } else {
                    dp[index1][index2] = Math.max(dp[index1-1][index2],dp[index1][index2-1]);
                }
            }
        }
        return dp[n][n];
    }
    public int minInsertion(String s) {
        int n = s.length();
        String t = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<=n;i++) {
            dp[i][0] = 0;
        }
        for (int i=0;i<=n;i++) {
            dp[0][i] = 0;
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=n;index2++) {
                if (s.charAt(index1-1) == t.charAt(index2-1)) {
                    dp[index1][index2] = 1 + dp[index1-1][index2-1];
                } else {
                    dp[index1][index2] = Math.max(dp[index1-1][index2],dp[index1][index2-1]);
                }
            }
        }
        return n-dp[n][n];
    }
    public int minOperations(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) {
            dp[i][0] = 0;
        }
        for (int i=0;i<=m;i++) {
            dp[0][i] = 0;
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=m;index2++) {
                if (str1.charAt(index1-1) == str2.charAt(index2-1)) {
                    dp[index1][index2] = 1 + dp[index1-1][index2-1];
                } else {
                    dp[index1][index2] = Math.max(dp[index1-1][index2],dp[index1][index2-1]);
                }
            }
        }
        return n + m - (2*dp[n][m]);
    }
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) {
            dp[i][0] = 0;
        }
        for (int i=0;i<=m;i++) {
            dp[0][i] = 0;
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=m;index2++) {
                if (str1.charAt(index1-1) == str2.charAt(index2-1)) {
                    dp[index1][index2] = 1 + dp[index1-1][index2-1];
                } else {
                    dp[index1][index2] = Math.max(dp[index1-1][index2],dp[index1][index2-1]);
                }
            }
        }
        StringBuilder s = new StringBuilder();
        int len = dp[n][m];
        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                s.append(str1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                s.append(str1.charAt(i-1));
                i--;
            } else {
                s.append(str2.charAt(j-1));
                j--;
            }
        }
        while (i > 0) {
            s.append(str1.charAt(i-1));
            i--;
        }
        while (j > 0) {
            s.append(str2.charAt(j-1));
            j--;
        }
        return s.reverse().toString();
    }

    public int distinctSubsequences(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) {
            dp[i][0] = i;
        }
        for (int i=1;i<=m;i++) {
            dp[0][i] = 0;
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=m;index2++) {
                if (s.charAt(index1-1) == t.charAt(index2-1)) {
                    dp[index1][index2] = dp[index1-1][index2-1] + dp[index1-1][index2];
                } else {
                    dp[index1][index2] = dp[index1-1][index2];
                }
            }
        }
        return dp[n][m];
    }
    public int editDistance(String start, String target) {
        int n = start.length();
        int m = target.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) {
            dp[i][0] = i;
        }
        for (int i=0;i<=m;i++) {
            dp[0][i] = i;
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=m;index2++) {
                if (start.charAt(index1-1) == target.charAt(index2-1)) {
                    dp[index1][index2] = dp[index1-1][index2-1];
                } else {
                    dp[index1][index2] = Math.min(dp[index1-1][index2-1],Math.min(dp[index1-1][index2],dp[index1][index2-1]));
                }
            }
        }
        return dp[n][m];
    }

    public boolean wildCard(String str, String pat) {
        int n = str.length();
        int m = pat.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for (int i=1;i<=n;i++) {
            dp[i][0] = pat.charAt(i-1) == '*' && dp[i-1][0];
        }
        for (int index1 = 1;index1<=n;index1++) {
            for (int index2 = 1;index2 <=m;index2++) {
                if (str.charAt(index1-1) == pat.charAt(index2-1) || pat.charAt(index1-1) == '?') {
                    dp[index1][index2] = dp[index1-1][index2-1];
                }else if (pat.charAt(index1-1) == '*') {
                    dp[index1][index2] = dp[index1-1][index2] || dp[index1][index2-1];
                }
                else {
                    dp[index1][index2] = false;
                }
            }
        }
        return dp[n][m];
    }
}

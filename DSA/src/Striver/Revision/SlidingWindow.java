package Striver.Revision;

import javax.swing.plaf.IconUIResource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
    public static void main(String[] args) {
        longestNonRepeatingSubstring("abcddabac");
        System.out.println(numSubarraysWithSum(new int[] {0,0,0,0,1}, 0));
    }

    public static int maxScore(int[] cardScore, int k) {
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < k; i++) leftSum = leftSum + cardScore[i];
        int maxSum = leftSum + rightSum;
        int index = cardScore.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            leftSum = leftSum - cardScore[i];
            rightSum = rightSum + cardScore[index];
            maxSum = Math.max(maxSum, leftSum + rightSum);
            index--;
        }
        return maxSum;
    }

    public static int longestNonRepeatingSubstring(String s) {
        int[] array = new int[256];
        Arrays.fill(array, -1);
        int l = 0;
        int r = 0;
        int maxLength = 0;
        while (r < s.length()) {
            if (array[s.charAt(r)] != -1) {
                l = Math.max(array[s.charAt(r)] + 1, l);
            }
            maxLength = Math.max(maxLength, r - l + 1);
            array[s.charAt(r)] = r;
            r++;
        }
        return maxLength;
    }

    public int longestOnes(int[] nums, int k) {
        int maxLength = 0;
        int l = 0;
        int r = 0;
        int zeroCount = 0;
        while (r < nums.length) {
            if (nums[r] == 0) zeroCount++;
            if (zeroCount > k) {
                while (zeroCount > k) {
                    if (nums[l] == 0) zeroCount--;
                    l++;
                }
            }
            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }

    public int totalFruits(int[] fruits) {
        int l = 0;
        int r = 0;
        int maxFruits = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < fruits.length) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) map.remove(fruits[l]);
                l++;
            }
            maxFruits = Math.max(maxFruits, r - l + 1);
            r++;
        }
        return maxFruits;
    }

    public int kDistinctChar(String s, int k) {
        int l = 0;
        int r = 0;
        int maxFruits = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (r < s.length()) {
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
            while (map.size() > 2) {
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                if (map.get(s.charAt(l)) == 0) map.remove(s.charAt(l));
                l++;
            }
            maxFruits = Math.max(maxFruits, r - l + 1);
            r++;
        }
        return maxFruits;
    }

    public int characterReplacement(String s, int k) {
        int l = 0;
        int r = 0;
        int maxLength = 0;
        int[] array = new int[26];
        int maxFreq = 0;
        while (r < s.length()) {
            array[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, array[s.charAt(r) - 'A']);
            while ((r - l + 1) - maxFreq > k) {
                array[s.charAt(l) - 'A']--;
                maxFreq = Math.max(maxFreq, array[s.charAt(l) - 'A']);
                l++;
            }
            maxLength = Math.max((r - l + 1), maxLength);
            r++;
        }
        return maxLength;
    }

    public String minWindow(String s, String t) {
        int minLength = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        int n = s.length();
        int[] hash = new int[256];
        int count = 0;
        for (char c : t.toCharArray()) hash[c]++;
        int index = -1;
        while (r < s.length()) {
            if (hash[s.charAt(r)] > 0) count++;
            hash[s.charAt(r)]--;
            while (count == t.length()) {
                if (r - l + 1 < minLength) {
                    minLength = r - l + 1;
                    index = l;
                }
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0) count--;
                l++;
            }
            r++;
        }
        return index == -1 ? "" : s.substring(index, minLength + index);
    }

    public int numberOfSubstrings(String s) {
        int[] lastSeen = {-1, -1, -1};
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                count = count + 1 + Math.min(Math.min(lastSeen[0], lastSeen[1]), lastSeen[2]);
            }
        }
        return count;
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int l = 0;
        int r = 0;
        int n = nums.length;
        int count = 0;
        int sum = 0;
        while (l < n) {
            sum = sum + nums[r];
            if (sum > goal) {
                l++;
                r = l;
                sum = 0;
                continue;
            }
            if (sum == goal) {
                count = count + 1;
            }
            r++;
            if (r == n) {
                l++;
                r = l;
            }
        }
        return count;
    }

}
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
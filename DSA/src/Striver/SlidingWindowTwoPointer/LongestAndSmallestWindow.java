package Striver.SlidingWindowTwoPointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestAndSmallestWindow {
    public static void main(String[] args) {
        System.out.println(longestNonRepeatingSubstring("abcddabac"));
    }
    public static int longestNonRepeatingSubstring(String s) {
        int hashValue = 256;
        int n = s.length();
        int r=0;
        int l=0;
        int maxLength = 0;
        int[] hashArray = new int[hashValue];
        Arrays.fill(hashArray,-1);
        while (r < n) {
            if (hashArray[s.charAt(r)] != -1) {
                // we are doing this just to recover a correct l value
                // just consider
                l = Math.max(hashArray[s.charAt(r)] + 1,l);
            }
            maxLength = Math.max(maxLength,r-l+1);
            hashArray[s.charAt(r)] = r;
            r++;
        }
        return maxLength;
    }
    // sliding window approach
    public int longestOnesSW(int[] nums, int k) {
        int l=0;
        int r=0;
        int ml = 0;
        int zc = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                zc++;
            }
            while (zc > k) {
                if (nums[l] == 0) {
                    zc--;
                }
                l++;
            }
            ml = Math.max(ml,r-l+1);
            r++;
        }
        return ml;
    }
    // 2-p approach
    public int longestOnes2p(int[] nums, int k) {
        int l=0;
        int r=0;
        int ml = 0;
        int zc = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                zc++;
            }
            if (zc > k) {
                if (nums[l] == 0) {
                    zc--;
                }
                l++;
            }
            if (zc <= k) {
                ml = Math.max(ml, r - l + 1);
            }
            r++;
        }
        return ml;
    }
    // sliding window
    public int totalFruitsSW(int[] fruits) {
        int ml = 0;
        int l=0;
        int r=0;
        Map<Integer,Integer> map = new HashMap<>();
        while (r < fruits.length) {
            map.put(fruits[r],map.getOrDefault(fruits[r],0)+1);
            while (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            ml = Math.max(ml,r-l+1);
            r++;
        }
        return ml;
    }
    // 2 POINTER
    public int totalFruits2p(int[] fruits) {
        int ml = 0;
        int l=0;
        int r=0;
        Map<Integer,Integer> map = new HashMap<>();
        while (r < fruits.length) {
            map.put(fruits[r],map.getOrDefault(fruits[r],0)+1);
            if (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            if (map.size() <= 2) {
                ml = Math.max(ml, r - l + 1);
            }
            r++;
        }
        return ml;
    }
    // sliding window
    public int kDistinctCharSW(String s, int k) {
        int l=0;
        int r=0;
        int ml=0;
        Map<Character,Integer> map = new HashMap<>();
        while (r < s.length()) {
            map.put(s.charAt(r),map.getOrDefault(s.charAt(r),0)+1);
            while (map.size() > k) {
                map.put(s.charAt(l),map.get(s.charAt(l)) - 1);
                if (map.get(s.charAt(l)) == 0) {
                    map.remove(s.charAt(l));
                }
                l++;
            }
            ml = Math.max(ml,r-l+1);
            r++;
        }
        return ml;
    }
    // 2pointer
    public int kDistinctChar2p(String s, int k) {
        int l=0;
        int r=0;
        int ml=0;
        Map<Character,Integer> map = new HashMap<>();
        while (r < s.length()) {
            map.put(s.charAt(r),map.getOrDefault(s.charAt(r),0)+1);
            if (map.size() > k) {
                map.put(s.charAt(l),map.get(s.charAt(l)) - 1);
                if (map.get(s.charAt(l)) == 0) {
                    map.remove(s.charAt(l));
                }
                l++;
            }
            if (map.size() <= k) {
                ml = Math.max(ml, r - l + 1);
            }
            r++;
        }
        return ml;
    }

    public int characterReplacementSW(String s, int k) {
        int l=0;
        int r=0;
        int maxFreq=0;
        int ml = 0;
        int[] hash = new int[26];
        while (r < s.length()) {
            hash[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, hash[s.charAt(r) - 'A']);
            while ((r-l+1) - maxFreq > k) {
                hash[s.charAt(l) - 'A']--;
                maxFreq = 0;
                for (int i=0;i<26;i++) {
                    maxFreq = Math.max(maxFreq,hash[s.charAt(i) - 'A']);
                }
                l++;
            }
            ml = Math.max(ml,(r-l+1));
            r++;
        }
        return ml;
    }

    public int characterReplacement2p(String s, int k) {
        int l=0;
        int r=0;
        int maxFreq=0;
        int ml = 0;
        int[] hash = new int[26];
        while (r < s.length()) {
            hash[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, hash[s.charAt(r) - 'A']);
            if ((r-l+1) - maxFreq > k) {
                hash[s.charAt(l) - 'A']--;
                l++;
            }
            ml = Math.max(ml,(r-l+1));
            r++;
        }
        return ml;
    }

    public String minWindow(String s, String t) {
        int l=0;
        int r=0;
        int count=0;
        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;
        int[] hash = new int[256];
        for (char c : t.toCharArray()) {
            hash[c]++;
        }
        while (r < s.length()) {
            if (hash[s.charAt(r)] > 0) {
                count++;
            }
            hash[s.charAt(r)]--;
            while (count == t.length()) {
                if ((r-l+1) < minLength) {
                    minLength = r-l+1;
                    startIndex = l;
                }
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0) {
                    count--;
                }
                l++;
            }
            r++;
        }
        return startIndex==-1 ? "" : s.substring(startIndex,minLength+startIndex);
    }
}

package Striver;

import java.util.*;

public class imp1 {
    public static void main(String[] args) {
        System.out.println(merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
        System.out.println(lengthOfLongestSubstring(" "));
    }
    // Two sum problems
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = 1;
        while (i<nums.length && j<nums.length) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i,j};
            } else {
                if (j==nums.length-1) {
                    i++;
                    j = i+1;
                } else {
                    j++;
                }
            }
        }
        return new int[]{-1,-1};
    }
    // Best buy and sell problem
    public int maxProfit(int[] prices) {
        int minValue = prices[0];
        int maxProfit = 0;
        for (int i=1;i<prices.length;i++) {
            if (prices[i]< minValue) {
                minValue = prices[i];
            } else if (prices[i]-minValue > maxProfit) {
                maxProfit = prices[i]-minValue;
            }
        }
        return maxProfit;
    }
    // Merge Interval
    // 2d arrays will be given and we need to check wether we can merge any of them
    // [[1,3],[2,6],[8,10]]
    // as the first one and second one overlaps each other as 3>2
    // end is greater than start
    public static int[][] merge(int[][] intervals) {
        // Sorting them on the basis of their starting so that it will be easy for us to start overlapping
        Arrays.sort(intervals,(a, b) -> Integer.compare(a[0],b[0]));
        // Created a list of int arrays
        List<int[]> result  = new ArrayList<>();
        // Iterating on the intervals and adding if list is not present in them
        // comparing the added ending and current starting if current starting is greater than previous ending then adding the whole
        // if there is a overall so changing the current ending
        // fetching out the max value bw both previous ending and current ending.
        for (int[] interval : intervals) {
            if (result.isEmpty() || result.getLast()[1] < interval[0]) {
                result.add(interval);
            } else {
                result.get(result.size()-1)[1] = Math.max(result.get(result.size()-1)[1],interval[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    // we can do with hashset as well and hashmap as well
    public static int lengthOfLongestSubstring(String s) {
        // Adding 128 to include all kinds of characters " " and all everything
        int[] arr = new int[128];
        int i = 0;
        int j = 0;
        int maxLength = 0;
        while (j<s.length()) {
            // Assigning the new array
            // Indecing will be done directly no need to do - 'a'
            arr[s.charAt(j)]++;
            // Need to check if there is any repeating char
            while (arr[s.charAt(j)] > 1) {
                arr[s.charAt(i)]--;
                i++;
            }
            maxLength = Math.max(maxLength,j-i+1);
            j++;
        }
        return maxLength;
    }
}

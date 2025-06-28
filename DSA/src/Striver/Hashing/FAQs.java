package Striver.Hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FAQs {
    public static void main(String[] args) {

    }

    // Using a hashset to maintain the exact unique copies of integer and elements
    // now iterating on those hashsets and checking if they are the starting element
    // if yes then iterating and checking till how much consecutive elements are present
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int result = Integer.MIN_VALUE;
        for (int st : set) {
            if (!set.contains(st-1)) {
                int count = 1;
                int x = st;
                while (set.contains(x+1)) {
                    count++;
                    x++;
                }
                result = Math.max(result,count);
            }
        }
        return result;
    }
    public int longestSubarray(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        Map<Integer,Integer> preSumMap = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            sum = sum + nums[i];
            // If the presum itself is equal to the target value then compare it with the results
            if (sum == k) {
                result = Math.max(result,i+1);
            }
            // Check the sum - target if there is any
            int remaining = sum-k;
            if (preSumMap.containsKey(remaining)) {
                result = Math.max(result,i-preSumMap.get(remaining));
            }
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum,i);
            }
        }
        return result;
    }
    // This is bit different than longest subarray as in that we are going to use the prefix sum concept to
    // get the longest subarray but calculating the remaining
    // Here what we will do is we will calculate the prefix sum frequency how many times it is coming thats how with the help
    // of remaining we can calculate the count of subArrays
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        Map<Integer,Integer> preSumMap = new HashMap<>();
        // Already adding a prefix starting from 0 that is
        preSumMap.put(0,1);
        // the above means that currently before starting we will calculate the prefix sum
        for (int i=0;i<nums.length;i++) {
            sum = sum + nums[i];
            int remaining = sum - k;
            result = result + preSumMap.getOrDefault(remaining,0);
            preSumMap.put(sum,preSumMap.getOrDefault(sum,0)+1);
        }
        return result;
    }
    // This is exactly same just the thing is we need to xor
    public int subarraysWithXorK(int[] nums, int k) {
        int result = 0;
        int xor = 0;
        Map<Integer,Integer> preSumMap = new HashMap<>();
        // Already adding a prefix starting from 0 that is
        preSumMap.put(0,1);
        // the above means that currently before starting we will calculate the prefix sum
        for (int i=0;i<nums.length;i++) {
            xor = xor ^ nums[i];
            int remaining = xor ^ k;
            result = result + preSumMap.getOrDefault(remaining,0);
            preSumMap.put(xor,preSumMap.getOrDefault(xor,0)+1);
        }
        return result;
    }
}

package Striver.Revision;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Hashing {
    public static void main(String[] args) {

    }
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num);
        }
        int result = Integer.MIN_VALUE;
        for (int s : st) {
            if (!st.contains(s-1)) {
                int count = 1;
                int x = s;
                while (st.contains(x+1)) {
                    count++;
                    x = x + 1;
                }
                result = Math.max(count,result);
            }
        }
        return result;
    }
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int result = 0;
        for (int num : nums) {
            sum = sum + num;
            result = result + map.getOrDefault(k-sum,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return result;
    }
}

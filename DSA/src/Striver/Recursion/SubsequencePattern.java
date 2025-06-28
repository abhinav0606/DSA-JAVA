package Striver.Recursion;

import java.util.ArrayList;
import java.util.List;

public class SubsequencePattern {
    public static void main(String[] args) {
        System.out.println(countSubsequenceWithTargetSum(new int[] {4, 9, 2, 5, 1},10));
    }
    // for subsequence, we will use the above pattern
    public static boolean checkSubsequenceSum(int[] nums, int k) {
        List<Integer> current = new ArrayList<>();
        return buildPowerSet2(0,current,nums,k);
    }
    private static boolean buildPowerSet2(int index,List<Integer> current,int[] nums,int k) {
        boolean result = false;
        if (index == nums.length) {
            int sum = 0;
            if (!current.isEmpty()) {
                for (int num : current) {
                    sum = sum + num;
                }
            }
            return sum == k;
        }
        if (buildPowerSet2(index + 1,current,nums,k)) return true;
        current.add(nums[index]);
        if (buildPowerSet2(index+1,current,nums,k)) return true;
        current.removeLast();
        return result;
    }

    public static int countSubsequenceWithTargetSum(int[] nums, int k) {
        List<Integer> current = new ArrayList<>();
        return buildPowerSet3(0,current,nums,k);
    }
    private static int buildPowerSet3(int index,List<Integer> current,int[] nums,int k) {
        int count = 0;
        if (index == nums.length) {
            int sum = 0;
            if (!current.isEmpty()) {
                for (int num : current) {
                    sum = sum + num;
                }
            }
            if (sum == k) {
                return 1;
            }
            return 0;
        }
        count = count + buildPowerSet3(index + 1,current,nums,k);
        current.add(nums[index]);
        count = count + buildPowerSet3(index+1,current,nums,k);
        current.removeLast();
        return count;
    }
}

package Striver.Recursion;

import java.util.*;

public class Medium {
    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[] {2, 1, 2, 7, 6, 1, 5},8));
        System.out.println(subsetSums(new int[] {2,3}));
        System.out.println(subsetsWithDup(new int[] {1,2,2}));
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        buildCombinationSum(0,result,current,target,candidates);
        return result;
    }
    private static void buildCombinationSum(int index,List<List<Integer>> result,List<Integer> current,int target,int[] nums) {
        if(target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0 || index == nums.length) {
            return;
        }
        current.add(nums[index]);
        buildCombinationSum(index,result,current,target - nums[index],nums);
        current.removeLast();
        buildCombinationSum(index+1,result,current,target,nums);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Arrays.sort(candidates);
        buildCombinationSum2(0,result,current,target,candidates);
        return new ArrayList<>(result);
    }
    private static void buildCombinationSum2(int index, List<List<Integer>> result, List<Integer> current, int target, int[] nums) {
        if(target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0 || index == nums.length) {
            return;
        }
        current.add(nums[index]);
        buildCombinationSum2(index + 1,result,current,target - nums[index],nums);
        current.remove(current.size()-1);
        for (int i = index+1;i<nums.length;i++) {
            if (nums[i] != nums[index]) {
                buildCombinationSum2(i,result,current,target,nums);
                break;
            }
        }
    }

    // This was a simple power set question in which we just need to return the sum of subarrays
    public static List<Integer> subsetSums(int[] nums) {
        List<Integer> current = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        buildSubsetSumCombination(0,nums,current,result);
        return result;
    }

    private static void buildSubsetSumCombination(int index,int[] nums,List<Integer> current, List<Integer> result) {
        if(index == nums.length) {
            if (current.isEmpty()) {
                result.add(0);
            } else {
                int sum = 0;
                for (int num : current) {
                    sum = sum + num;
                }
                result.add(sum);
            }
            return;
        }
        current.add(nums[index]);
        buildSubsetSumCombination(index + 1,nums,current,result);
        current.remove(current.size()-1);
        buildSubsetSumCombination(index+1,nums,current,result);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        buildSubsetSumCombination2(0,nums,current,result);
        return result;
    }

    private static void buildSubsetSumCombination2(int index,int[] nums,List<Integer> current, List<List<Integer>> result) {
        if(index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[index]);
        buildSubsetSumCombination2(index + 1,nums,current,result);
        current.remove(current.size()-1);
        for (int i = index+1;i<nums.length;i++) {
            if (nums[i] != nums[index]) {
                buildSubsetSumCombination2(i,nums,current,result);
                return;
            }
        }
        buildSubsetSumCombination2(nums.length,nums,current,result);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        buildCombinationSum3(1,k,n,current,result);
        return result;
    }
    private static void buildCombinationSum3(int start, int k, int sum, List<Integer> current, List<List<Integer>> result) {
        if (sum == 0 && current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (sum < 0 || current.size() > k) return;

        for (int i = start;i<=9;i++) {
            if (i <= sum) {
                current.add(i);
                buildCombinationSum3(i+1, k, sum - i, current, result);
                current.remove(current.size() - 1);
            } else {
                break;
            }
        }
    }
}

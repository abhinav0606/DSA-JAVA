package Striver.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysLogicBuilding {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZeroes(new int[]{2, 5, 0, 1, 2, 3, 0, 9, 0, 7})));
    }

    public static int[] moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        return nums;
    }

    public int removeDuplicates(int[] nums) {
        // Use two pointer approach
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        // return the size of new array
        return i + 1;
    }

    // Missing Positive smallest
    public int missingNumber(int[] nums) {
        // Natural number sum
        int sizeOfArray = nums.length;
        // Calculating the sum of first n prime natural numbers
        int sumOfNNaturalNumbers = sizeOfArray * (sizeOfArray + 1) / 2;
        int sumOfArray = Arrays.stream(nums).sum();
        return sumOfNNaturalNumbers - sumOfArray;
    }

    public int[] unionArray(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                if (!result.isEmpty() && result.contains(nums1[i])) i++;
                else result.add(nums1[i++]);
            } else {
                if (!result.isEmpty() && result.contains(nums2[j])) j++;
                else result.add(nums2[j++]);
            }
        }
        while (i < n1) {
            if (!result.isEmpty() && result.contains(nums1[i])) i++;
            else result.add(nums1[i++]);
        }
        while (j < n2) {
            if (!result.isEmpty() && result.contains(nums2[j])) j++;
            else result.add(nums2[j++]);
        }
        return result.stream().mapToInt(l -> l).toArray();
    }

    public int[] intersectionArray(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        Arrays.sort(nums1);
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                result.add(nums1[i]);
                i++;
                j++;
            }
        }
        return result.stream().mapToInt(l -> l).toArray();
    }
}

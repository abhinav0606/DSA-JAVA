package Striver.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraysFAQHard {
    public static void main(String[] args) {
        System.out.println(reversePairs(new int[] {6, 4, 1, 2, 7}));
    }
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length/2) return entry.getKey();
        }
        return -1;
    }
    public List<Integer> majorityElementTwo(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length/3) result.add(entry.getKey());
        }
        return result;
    }
    // Using mathematical equations over here as in when we will use this concept
    // sum of n natural numbers and sum of squares of n numbers and then do some mathematical
    // but this kind of scenarios will not work for bigger sets
    public static int[] findMissingRepeatingNumbers(int[] nums) {
        int n = nums.length;
        int sumOfNumbers = 0;
        int sumOfSquares = 0;
        for (int num : nums) {
            sumOfNumbers = sumOfNumbers + num;
            sumOfSquares = sumOfSquares + (num*num);
        }
        int sumOfNNumbers = (n*(n+1))/2;
        int sumOfSquaresNNumbers = (n*(n+1)*(2*n + 1))/6;
        int val1 = sumOfNumbers - sumOfNNumbers;
        int val2 = sumOfSquares - sumOfSquaresNNumbers;
        val2 = val2/val1;
        int repeatedNumber = (val1+val2)/2;
        int missingNumber = repeatedNumber - val1;
        return new int[] {repeatedNumber,missingNumber};
    }
    // Instead of the above algorithm we can use the hashset one
    // Create another frequency array which element is around 2 that is repeated and which is 0 that is missing
    public static int[] findMissingRepeatingNumbersApproach2(int[] nums) {
        int n = nums.length;
        int[] hash = new int[n+1];
        for (int i=0;i<n;i++) {
            hash[nums[i]]++;
        }
        int missing = -1;
        int repeated = -1;
        for (int i = 1;i<hash.length;i++) {
            if (hash[i] == 2) repeated = i;
            else if (hash[i] == 0) missing = i;
            if (repeated!=-1 && missing!=-1) break;
        }
        return new int[] {repeated,missing};
    }
    // For this kind of questions we have two approaches one is kind of kadane's algorithm
    // and another one is the suffix prefix method which is the most optimal method and lets understand that method
    public int maxProduct(int[] nums) {
        // Assigning the first element as the first product
        int result = nums[0];
        for (int i=0;i<nums.length;i++) {
            int p = nums[i];
            for (int j=i+1;j<nums.length;j++) {
                result = Math.max(result,p);
                p = p*nums[j];
            }
            result = Math.max(result,p);
        }
        return result;
    }
    // Using the prefix and suffix thing
    public int maxProductTwo(int[] nums) {
        int prefix = 1;int suffix = 1;
        int result = Integer.MIN_VALUE;
        for (int i=0;i<nums.length;i++) {
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;
            prefix = prefix * nums[i];
            suffix = suffix * nums[nums.length-i-1];
            result = Math.max(result,Math.max(prefix,suffix));
        }
        return result;
    }
    // Count inversion problem for this kind of problems i will prefer to use merge sort in which
    // when merging two arrays i will get to compare two values together
    public static long numberOfInversions(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        return mergeSort(nums,left,right);
    }
    public static int mergeSort(int[] nums,int left,int right) {
        int count = 0;
        if (left < right) {
            int mid = (left+right)/2;
            count = count + mergeSort(nums,left,mid);
            count = count + mergeSort(nums,mid+1,right);
            count = count + merge(nums,left,mid,right);
        }
        return count;
    }

    private static int merge(int[] nums,int left,int mid,int right) {
        int n1 = mid-left+1;
        int n2 = right-mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        int count = 0;
        System.arraycopy(nums, left, leftArray, 0, n1);
        for (int i=0;i<n2;i++) {
            rightArray[i] = nums[mid + i + 1];
        }
        int i=0;
        int j=0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k] = leftArray[i];
                i++;
            } else {
                nums[k] = rightArray[j];
                j++;
                // Why are we doing this because as we are merging two sorted arrays
                // now here we will just take all the values above the current ones also because they will be also greater
                // but why -i?
                // because lets assume 1 3 5 and 2 4 6
                // we are at 3 that is i=1 now and other side is at 2 that is j=0
                // pairs are 3,2 5,2 total 2 n1 is 3 and i is 1 3-1 => 2
                count = count +  n1 -i;
            }
            k++;
        }
        while (i < n1) {
            nums[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            nums[k] = rightArray[j];
            j++;
            k++;
        }
        return count;
    }

    // Question in which arr[i] > 2*arr[j] and i<j
    public static int reversePairs(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        return mergeSort2(nums,left,right);
    }
    public static int mergeSort2(int[] nums,int left,int right) {
        int count = 0;
        if (left < right) {
            int mid = (left+right)/2;
            count = count + mergeSort2(nums,left,mid);
            count = count + mergeSort2(nums,mid+1,right);
            count = count + merge2(nums,left,mid,right);
        }
        return count;
    }
    private static int merge2(int[] nums,int left,int mid,int right) {
        int n1 = mid-left+1;
        int n2 = right-mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        int count = 0;
        System.arraycopy(nums, left, leftArray, 0, n1);
        for (int i=0;i<n2;i++) {
            rightArray[i] = nums[mid + i + 1];
        }
        int i=0;
        int j=0;
        int k = left;
        for (int l=0;l<n1;l++) {
            int m = 0;
            while (m<n2 && leftArray[l] > 2*rightArray[m]) {
                m++;
            }
            count = count + m;
        }
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k] = leftArray[i];
                i++;
            } else {
                nums[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            nums[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            nums[k] = rightArray[j];
            j++;
            k++;
        }
        return count;
    }
    // Merge two sorted arrays without extra space
    // Can we use here the merge sort merge method?
    // This is the best approach that i have figured out and most optimal
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;
        while (i>=0 && j>=0) {
            if (nums1[i] > nums2[j]) nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
        while (i>=0) {
            nums1[k--] = nums1[i--];
        }
        while (j>=0) {
            nums1[k--] = nums2[j--];
        }
    }
}

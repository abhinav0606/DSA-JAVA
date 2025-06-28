package Striver.Arrays;

public class ArraysBasic {
    public static void main(String[] args) {
        System.out.println(secondLargestElement(new int[] {10,10,10,10,10}));
        System.out.println(findMaxConsecutiveOnes(new int[] {0, 0, 0, 0, 0, 0, 0, 0}));
    }
    private static int linearSearch(int[] num,int target) {
        int index = -1;
        for (int i=0;i<num.length;i++) {
            if (num[i] == target) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int largestElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i=0;i<nums.length;i++) {
            max = Math.max(max,nums[i]);
        }
        return max;
    }
    public static int secondLargestElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = -1;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
            } else if (nums[i] > secondMax && nums[i] != max) {
                secondMax = nums[i];
            }
        }
        if (secondMax == Integer.MIN_VALUE) return -1;
        return secondMax;
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        // Max can be set to 0
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                max = Math.max(count,max);
                count = 0;
            }
        }
        return Math.max(max,count);
    }
    public static void rotateArrayByOne(int[] nums) {
        int key = nums[0];
        for (int i=1;i<nums.length;i++) {
            nums[i-1] = nums[i];
        }
        nums[nums.length-1] = key;
    }
    public void rotateArray(int[] nums, int k) {
        int length = nums.length;
        k = k%length;
        reverseArray(nums,0,k-1);
        reverseArray(nums,k,length-1);
        reverseArray(nums,0,length-1);
    }

    private void reverseArray(int[] nums, int low, int high) {
        while(low<high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }
    // First and Last position of an sorted array
    public int[] searchRange(int[] nums, int target) {
        int i = 0;
        int start = -1;
        int end = -1;
        while (i<nums.length) {
            if (nums[i] == target) {
                start = i;
                break;
            }
            i++;
        }
        int j = nums.length-1;
        while (j>=0) {
            if (nums[j] == target) {
                end = j;
                break;
            }
            j--;
        }
        return new int[] {start,end};
    }



}

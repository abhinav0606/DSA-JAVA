package Striver.Revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ArraysRev {
    public static void main(String[] args) {

    }

    public static int secondLargestElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
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

    public void rotateArray(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, k - 1);
        reverse(nums, k - 1, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }

    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    }

    public int removeDuplicates(int[] arr) {
        int i = 0;
        int j = 1;
        while (j < arr.length) {
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
            j++;
        }
        return i + 1;
    }

    public ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > max) {
                ans.add(nums[i]);
            }
            max = Math.max(max, nums[i]);
        }
        Collections.reverse(ans);
        return ans;
    }

    public int[] rearrangeArray(int[] nums) {
        int posIndex = 0;
        int negIndex = 1;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                ans[posIndex] = nums[i];
                posIndex = posIndex + 2;
            } else {
                ans[negIndex] = nums[i];
                negIndex = negIndex + 2;
            }
        }
        return ans;
    }

    public void rotateMatrix(int[][] matrix) {
        // Create a transponse and reverse each rows
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
        for (int row = 0; row < matrix.length; row++) {
            int low = 0;
            int high = matrix[row].length - 1;
            while (low < high) {
                int temp = matrix[row][low];
                matrix[row][low] = matrix[row][high];
                matrix[row][high] = temp;
                low++;
                high--;
            }
        }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sumLeft = target - nums[i];
            if (map.containsKey(sumLeft)) {
                return java.util.Arrays.stream(new int[]{i, map.get(sumLeft)}).sorted().toArray();
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // Dutch National Flag algo
    public void sortZeroOneTwo(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) mid++;
            else {
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
            }
        }
    }

    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public long numberOfInversions(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        return mergeSort(nums, left, right);
    }

    private int mergeSort(int[] nums, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            count = count + mergeSort(nums, left, mid);
            count = count + mergeSort(nums, mid + 1, right);
            count = count + merge(nums, left, mid, right);
        }
        return count;
    }

    private int merge(int[] nums, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        for (int i = 0; i < n1; i++) {
            leftArr[i] = nums[i];
        }
        for (int i = 0; i < n2; i++) {
            rightArr[i] = nums[mid + 1 + i];
        }
        int i = 0;
        int j = 0;
        int k = left;
        int count = 0;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                nums[k++] = leftArr[i++];
            } else {
                nums[k++] = rightArr[j++];
                count = count + mid - left + 1 - i;
            }
        }
        while (i < n1) {
            nums[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            nums[k] = rightArr[j];
            j++;
            k++;
        }
        return count;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else nums1[k--] = nums2[j--];
        }
        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}

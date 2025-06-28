package Striver;

import java.util.Arrays;

public class imp5 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(quickSort(new int[]{3, 4, 5, 2, 1, 5, 6}, 0, 6)));
        System.out.println(Arrays.toString(mergeSort(new int[]{3, 4, 5, 2, 1, 5, 6}, 0, 6)));
    }
    // JUMP GAME but try with DP thing
    // We will iterate at each index maintaining a max number
    // we will calculate the max value by max of current max and index + value at that index
    // if the max value itself is greater than or less than the length than we can say that it is possible to jump
    // before that we have to put a check that max value should not be less than the index value otherwise it is impossible to
    // jump
    public boolean canJump(int[] nums) {
        int max = 0;
        int length = nums.length - 1;
        for (int i=0;i<=length;i++){
            if (i>max) return false;
            max = Math.max(max,i+nums[i]);
            if (max>=length) return true;
        }
        return false;
    }

    // Quick Sort
    // Basically in quick sort you need to create a pivot element mostly last first or median
    // take a pointer for the smaller elements to swap
    // take another pointer to iterate
    public static int[] quickSort(int[] nums,int low,int high) {
        if (low < high) {
            int pivotIndex = partition(nums,low,high);
            quickSort(nums,low,pivotIndex-1);
            quickSort(nums,pivotIndex+1,high);
        }
        return nums;
    }

    private static int partition(int[] nums,int low,int high) {
        int pivot = nums[high];
        int i = low - 1;
        for (int j=low;j<high;j++) {
            if (nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i+1];
        nums[i+1] = nums[high];
        nums[high] = temp;
        return i+1;
    }



    // Quick Sort question:

    // Randomize the pivot selection to solve the larger use cases and test cases otherwise it can go into o(n2)
    // instead of o(nlogn)
    public static int[] sortArray(int[] nums) {
        return quickSort(nums,0,nums.length-1);
    }
    private static int[] sort(int[] nums,int low, int high) {
        if(low < high) {
            int partition = sortingPartition(nums,low,high);
            sort(nums,low,partition-1);
            sort(nums,partition+1,high);
        }
        return nums;
    }
    private static int sortingPartition(int[] nums,int low, int high) {
        int i = low-1;
        int pivot = nums[high];
        for (int j=low;j<high;j++) {
            if (nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i+1];
        nums[i+1] = nums[high];
        nums[high] = temp;
        return i+1;
    }

    //Merge Sort
    // Divide the array bw 2 parts and then recursively divide them
    // Post that merge all the array in the sorted forms.
    // all cases complexity is nlogn
    public static int[] mergeSort(int[] nums,int left,int right) {
        if (left < right) {
            int mid = (left+right)/2;
            mergeSort(nums,left,mid);
            mergeSort(nums,mid+1,right);
            merge(nums,left,mid,right);
        }
        return nums;
    }

    private static void merge(int[] nums,int left,int mid,int right) {
        int n1 = mid-left+1;
        int n2 = right-mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        for (int i=0;i<n1;i++) {
            leftArray[i] = nums[left+i];
        }
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
    }
}

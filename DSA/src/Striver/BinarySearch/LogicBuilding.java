package Striver.BinarySearch;

import java.util.ArrayList;

public class LogicBuilding {
    public static void main(String[] args) {

    }
    // Search a target and if not found return the index where it can be found?
    // We will use same binary search and try to use the lower/upper bound process
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        int ans = nums.length;
        while (low <= high) {
            int mid = (low+high)/2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[mid] > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    // Floor and Ceil Question
    public int[] getFloorAndCeil(int[] nums, int x) {
        int low = 0;
        int high = nums.length-1;
        int ans = nums.length;
        int floor = -1;
        int ceil = -1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (x == nums[mid]) {
                return new int[] {x,x};
            }
            if (nums[mid] > x) {
                // ceil will come here because mid is greater than target so might be mid can be a element
                ceil = nums[mid];
                high = mid - 1;
            } else {
                // mid can be a element because mid is less than x
                floor = nums[mid];
                low = mid + 1;
            }
        }
        return new int[]{floor,ceil};
    }
    // First and Last Occurrence of target element
    // if there is nothing return -1,-1
    public static int[] searchRange(int[] nums, int target) {
        // We will create two binary search one for first and one for last
        int low = 0;
        int high = nums.length-1;
        int start = -1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (target == nums[mid]) {
                start = mid;
                high = mid-1;
            }
            else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        int end = -1;
        low = 0;
        high = nums.length-1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (target == nums[mid]) {
                end = mid;
                low = mid+1;
            }
            else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[] {start,end};
    }

    // This question is basically a search in rotated sorted array
    // We will implement a binary search to find out that:
    // If left array is sorted and figure out if target <= mid then continue with left
    // otherwise go with right and try to find out the sorted array again and do the same process
    public int searchRotated(int[] nums, int k) {
        int low = 0;
        int high = nums.length-1;
        int index = -1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (k == nums[mid]) {
                return mid;
            }
            // left array sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= k && k <= nums[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
            else {
                if (k >= nums[mid] && nums[high] >= k) {
                    low = mid+1;
                } else {
                    high = mid -1;
                }
            }
        }
        return index;
    }

    public static boolean searchRotated2(ArrayList<Integer> nums, int k) {
        int low = 0;
        int high = nums.size()-1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (k == nums.get(mid)) {
                return true;
            }
            // Handling of duplicates otherwise handle it in a way to convert it to a set
            if (nums.get(low).equals(nums.get(mid)) && nums.get(mid).equals(nums.get(high))) {
                low = low+1;
                high = high-1;
                continue;
            }
            // left array sorted
            if (nums.get(low) <= nums.get(mid)) {
                if (nums.get(low) <= k && k <= nums.get(mid)) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
            else {
                if (k >= nums.get(mid) && nums.get(high) >= k) {
                    low = mid+1;
                } else {
                    high = mid -1;
                }
            }
        }
        return false;
    }
    // Same question we need to find the minimum in the rotated sorted array
    public static int findMin(ArrayList<Integer> arr) {
        int low = 0;
        int high = arr.size()-1;
        int minimum = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low+high)/2;
            minimum = Math.min(minimum,arr.get(mid));
            if (arr.get(high) < arr.get(mid)) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return minimum;
    }
    // Rotation will be figured out with the index of the smallest numbers so we will use the above code
    public int findKRotation(ArrayList<Integer> nums) {
        int low = 0;
        int high = nums.size()-1;
        int minimum = Integer.MAX_VALUE;
        int rotation = -1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (nums.get(mid) < minimum) {
                minimum = Math.min(minimum,nums.get(mid));
                rotation = mid;
            }
            if (nums.get(high) < nums.get(mid)) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return rotation;
    }
    // So in this code we need to just check that  because every number appear twice so basically
    // if we will take the mid that might decide the thing where the number will lie in which half
    // if mid == mid + 1 then it will lie in left side else right side
    public static int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        // EDGE CASES
        int n = nums.length; // Size of the array.
        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];
        while (low <= high) {
            int mid = (low+high)/2;
            if (nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1]) return nums[mid];
            // This is completely a maths because:
            // if the mid is at odd position and mid + 1 = mid then at the right side all elements are duplicate
            // same goes with even position and mid - 1- mid then all element at right are duplicate
            if ((nums[mid] == nums[mid-1] && mid%2==1)||(nums[mid] == nums[mid+1] && mid%2==0)) {
                low = mid + 1;
            } else{
                high = mid-1;
            }

        }
        return -1;
    }
}

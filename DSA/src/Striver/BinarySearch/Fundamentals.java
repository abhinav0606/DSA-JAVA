package Striver.BinarySearch;

public class Fundamentals {
    public static void main(String[] args) {
    }
    // This problem states that we have to search a target and return its index
    // We have to use binary search => so basically whenever there is such type of questions
    // like search in sorted array we need to converge the things and reduce search space
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) low = mid+1;
            else high = mid-1;
        }
        return -1;
    }
    // We can do the same using recursive approach as well
    private int func(int[] nums,int low,int high,int target) {
        if (low>high) return -1;
        int mid = (low+high)/2;
        if (nums[mid] == target) return mid;
        else if (target > nums[mid]) return func(nums,mid+1,high,target);
        else return func(nums,low,mid-1,target);
    }
    // Figure out the lower bound
    public int lowerBound(int[] nums, int x) {
        int low = 0;
        int high = nums.length-1;
        int ans = nums.length;
        while (low <= high) {
            int mid = (low+high)/2;
            if (nums[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    // just opposite of the above question
    public int upperBound(int[] nums, int x) {
        int low = 0;
        int high = nums.length-1;
        int ans = nums.length;
        while (low <= high) {
            int mid = (low+high)/2;
            if (nums[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}

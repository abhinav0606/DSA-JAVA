package Striver.SlidingWindowTwoPointer;

public class CountSubArrSubString {
    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcba"));
    }
    public static int numberOfSubstrings(String s) {
        int[] lastSeen = {-1,-1,-1};
        int count = 0;
        for (int i=0;i<s.length();i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                count = count + 1 + Math.min(Math.min(lastSeen[0],lastSeen[1]),lastSeen[2]);
            }
        }
        return count;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        return goalFunc(nums,goal) - goalFunc(nums,goal-1);
    }
    private static int goalFunc(int[] nums, int goal) {
        if (goal < 0) return 0;
        int sum=0;
        int r=0;
        int l=0;
        int count=0;
        while (r < nums.length) {
            sum = sum + nums[r];
            while (sum > goal) {
                sum = sum - nums[l];
                l++;
            }
        count = count + (r-l+1);
        r++;
        }
        return count;
    }

    public int numberOfOddSubarrays(int[] nums, int k) {
        // exactly like above question
        return helper(nums,k) - helper(nums,k-1);
    }
    private static int helper(int[] nums, int goal) {
        if (goal < 0) return 0;
        int sum=0;
        int r=0;
        int l=0;
        int count=0;
        while (r < nums.length) {
            sum = sum + nums[r]%2;
            while (sum > goal) {
                sum = sum - (nums[l]%2);
                l++;
            }
            count = count + (r-l+1);
            r++;
        }
        return count;
    }
}

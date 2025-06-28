package Striver.BinarySearch;

public class OnAnswer {
    public static void main(String[] args) {
        System.out.println((floorSqrt(25)));
        System.out.println(NthRoot(3,27));
        System.out.println(NthRoot(4,69));
        System.out.println(minimumRateToEatBananas(new int[] {805306368,805306368,805306368},1000000000));
    }

    // Basically when we run binary search try to optimise the approach here
    // take low = 1 and high as n
    // take mid
    // if mid^2 is less than equal to n
    // move to right
    // otherwise move left
    // and return high because we want to floor value of it.
    public static long floorSqrt(long n) {
        long low = 1;
        long high = n;
        while (low<=high) {
            long mid = (low+high)/2;
            long val = mid * mid;
            if (val <= n) {
                low = mid + 1;
            } else {
                high = mid-1;
            }
        }
        return high;
    }

    // Basically start from 1 and end at M+1
    // and almost same as square root question
    public static int NthRoot(int N, int M) {
        int low = 1;
        int high = M+1;
        while (low <= high) {
            int mid = (low+high)/2;
            int val = (int) Math.pow(mid,N);
            if (val == M) return mid;
            if (val< M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // So this is the smallest divisor question
    // Smallest divisor will lie bw 1 and max of nums
    // then we will do the same thing
    // and here as we to return the smallest so return low here
    // here the loop will be different because we are dividing
    // as dividing with greater number results in less number
    public int smallestDivisor(int[] nums, int limit) {
        int low = 1;
        int high = Integer.MIN_VALUE;
        for (int num : nums) {
            high = Math.max(num,high);
        }
        while (low <= high) {
            int mid = (low+high)/2;
            int value = summation(nums,mid);
            if (value <= limit) {
                high = mid-1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    private static int summation(int[] nums,int mid) {
        int sum = 0;
        for (int num : nums) {
            if (num%mid == 0) {
                sum = sum + (num/mid);
            } else {
                sum = sum + ((num/mid)+1);
            }
        }
        return sum;
    }

    // Classic problem of KOKOEating Banana
    // Exactly like above
    public static int minimumRateToEatBananas(int[] nums, int h) {
        int low = 1;
        int high = Integer.MIN_VALUE;
        for (int num : nums) {
            high = Math.max(num,high);
        }
        while (low <= high) {
            int mid = (low+high)/2;
            int value = summation(nums,mid);
            if (value <= h) {
                high = mid-1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Rose Garden Blooming question
    public int roseGarden(int n, int[] nums, int k, int m) {
        //Edge case:
        // m is number of bouquet
        // k is number of flowers
        if (n < k*m) return -1;
        int ans = -1;
        int low = 1;
        int high = Integer.MIN_VALUE;
        for (int i=0;i<n;i++) {
            high = Math.max(high, nums[i]);
        }
        while (low <= high) {
            int mid = (low + high)/2;
            boolean possible = possibleRoses(nums, mid, k, m);
            if (possible) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    private static boolean possibleRoses(int[] nums, int mid, int k, int m) {
        int length = nums.length;
        int count = 0;
        int flowerCount = 0;
        for (int i=0;i<length;i++) {
            if (nums[i] <= mid) {
                flowerCount = flowerCount + 1;
            } else {
                flowerCount = 0;
            }
            if (flowerCount == k) {
                count = count + 1;
                flowerCount = 0;
            }
        }
        return count >= m;
    }

}

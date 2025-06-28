package Striver.BinarySearch;

import java.util.Arrays;

public class FAQ {
    public static void main(String[] args) {
        System.out.println(kthElement(new int[] {2, 3, 6, 7, 9},new int[] {1, 4, 8, 10},5));
    }
    public int aggressiveCows(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int low = 1;
        // Because high here will be the maximum distance at which we can place our cow
        // as the first cow will be at the first stall and calculating the distance bw first and last stall
        int high = nums[n-1] - nums[0];
        while (low <= high) {
            int mid = (high + low)/2;
            if (canWePlace(nums,mid,k)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
    private static boolean canWePlace(int[] nums, int mid, int cows) {
        int n = nums.length;
        int lastCow = nums[0];
        int cntCow = 1;
        for (int i=1;i<n;i++) {
            if (nums[i]-lastCow >= mid) {
                cntCow = cntCow + 1;
                // updating the last cow placed value
                lastCow = nums[i];
            }
            if (cntCow == cows) return true;
        }
        return false;
    }

    // This question is also very similar to the above question
    // basically there are several books and their page count is given in a array
    // Now basically we need to divide the books among the students and need to figure out
    // a number so that the books that students contains should have max that number of pages.
    public int findPages(int[] nums, int m) {
        int low = Integer.MIN_VALUE;
        int high = 0;
        int n = nums.length;
        if (m > n) return -1;
        for (int i=0;i<n;i++) {
            low = Math.max(nums[i],low);
            high = high + nums[i];
        }
        while (low <= high) {
            int mid = (low + high)/2;
            if (bookAllotment(nums, mid) > m) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
    private int bookAllotment(int[] nums, int mid) {
        int n = nums.length;
        // As we can allocate all the books to a single student
        int student = 1;
        int pages = 0;
        for (int i=0;i<n;i++) {
            if (pages + nums[i] <= mid) {
                pages = pages + nums[i];
            } else {
                student++;
                pages = nums[i];
            }
        }
        return student;
    }
    // So for the above 2 questions we got the pattern close enough how to solve the allotment problem
    // as if we want to allot some something

    // find the peak element which is greater than previous and the successor element also
    public int findPeakElement(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        if (arr[0] > arr[1]) return 0;
        if (arr[n-1] > arr[n-2]) return n-1;
        int low = 1;
        int high = n-2;
        while (low <= high) {
            int mid = (low + high)/2;
            if ((arr[mid-1] < arr[mid]) &&(arr[mid] > arr[mid+1])) return mid;
            if (arr[mid+1] > arr[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // Median of two sorted array using binary search
    public double median(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        // because if arr1 size is smaller search space will be less
        if (n1>n2) return median(arr2,arr1);
        int low = 0;
        int high = n1;
        int leftArraySize = (n1+n2+1)/2;
        int overallSize = n2+n1;
        while (low <= high) {
            int mid1 = (low + high)/2;
            int mid2 = leftArraySize - mid1;
            int l1 = mid1 > 0 ? arr1[mid1-1] : Integer.MIN_VALUE;
            int r1 = mid1 < n1 ? arr1[mid1] : Integer.MAX_VALUE;
            int l2 = mid2 > 0 ? arr2[mid2-1] : Integer.MIN_VALUE;
            int r2 = mid2 < n2 ? arr2[mid2] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                if (overallSize%2==1) return Math.max(l1,l2);
                else {
                    return (Math.max(l1,l2) + Math.min(r1,r2))/2.0;
                }
            } else if (l1 > r2) high = mid1-1;
            else low = mid1 + 1;
        }
        //dummy
        return 0;
    }
    // Good and Important Question about min distance bw gas stations
    // this is one of the most important pattern for the double binary seach
    // So here instead of low <= high we can have to compare it with two numbers
    // and it is almost similar to the above pattersn
    public double minimiseMaxDistance(int[] arr, int k) {
        double low = 0;
        double high = 0;
        int n = arr.length;
        for (int i = 0;i< n-2;i++) {
            high = Math.max(high,arr[i+1]-arr[i]);
        }
        double difference = 1e-6;
        while (high-low > difference) {
            double mid = (high + low)/2;
            int count = countNumberOfGasStation(mid, arr);
            if (count > k) low = mid;
            else high = mid;
        }
        return high;
    }
    private int countNumberOfGasStation(double mid, int[] arr) {
        int count = 0;
        for (int i=1;i<arr.length;i++) {
            int numberInBw = (int)((arr[i] - arr[i-1])/mid);
            if ((arr[i] - arr[i-1]) == mid*numberInBw) numberInBw--;
            count = count + numberInBw;
        }
        return count;
    }

    public static int kthElement(int[] a, int[] b, int k) {
        int n1 = a.length;
        int n2 = b.length;
        if (n1>n2) kthElement(b,a,k);
        // considering if k-n1 is not negative
        int low = Math.max(0,k-n1);
        // Considering if k is smaller than n2
        int high = Math.min(k,n2);
        int overallSize = n2+n1;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = k - mid1;
            int l1 = mid1 > 0 ? a[mid1 - 1] : Integer.MIN_VALUE;
            int r1 = mid1 < n1 ? a[mid1] : Integer.MAX_VALUE;
            int l2 = mid2 > 0 ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r2 = mid2 < n2 ? b[mid2] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                // because left array contains k elements
                return Math.max(l1,l2);
            } else if (l1 > r2) high = mid1-1;
            else low = mid1 + 1;
        }
        return -1;
    }

    // Painter's Partition (Exactly copy of allocate books)
    public int largestSubarraySumMinimized(int[] a, int k) {
        int low = Integer.MIN_VALUE;
        int high = 0;
        int n = a.length;
        if (k > n) return -1;
        for (int i=0;i<n;i++) {
            low = Math.max(a[i],low);
            high = high + a[i];
        }
        while (low <= high) {
            int mid = (low + high)/2;
            if (bookAllotment(a, mid) > k) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
}

package Striver.Revision;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {

    }

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public int lowerBound(int[] nums, int x) {
        int ans = nums.length;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= x) {
                high = mid - 1;
                ans = mid;
            } else low = mid + 1;
        }
        return ans;
    }

    public int[] getFloorAndCeil(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int floor = -1;
        int ceil = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == x) return new int[]{x, x};
            else if (nums[mid] > x) {
                ceil = mid;
                high = mid - 1;
            } else {
                floor = mid;
                low = mid + 1;
            }
        }
        return new int[]{floor, ceil};
    }

    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int end = -1;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                start = mid;
                high = mid - 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                end = mid;
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{start, end};
    }

    public boolean searchInARotatedSortedArrayII(ArrayList<Integer> nums, int k) {
        int low = 0;
        int high = nums.size() - 1;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (nums.get(mid) == k) return true;
            if (nums.get(mid).equals(nums.get(low)) && nums.get(mid).equals(nums.get(high))) {
                low = low + 1;
                high = high - 1;
                continue;
            }
            if (nums.get(low) <= nums.get(mid)) {
                if (nums.get(low) <= k && nums.get(mid) >= k) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums.get(high) >= k && nums.get(mid) <= k) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    public int findMin(ArrayList<Integer> arr) {
        int low = 0;
        int high = arr.size() - 1;
        int min = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            min = Math.min(min, arr.get(mid));
            if (arr.get(high) < arr.get(mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return min;
    }
    public int findKRotation(ArrayList<Integer> nums) {
        int low = 0;
        int high = nums.size() - 1;
        int min = Integer.MAX_VALUE;
        int rotation = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) < min) {
                min = Math.min(min, nums.get(mid));
                rotation = mid;
            }
            if (nums.get(high) < nums.get(mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return rotation;
    }

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n-1] != nums[n-2]) return nums[n-1];
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) return nums[mid];
            if ((mid%2 == 0 && nums[mid] == nums[mid+1]) || (mid%2 != 0 && nums[mid] == nums[mid-1])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public long floorSqrt(long n) {
        long low = 1;
        long high = n;
        long ans = 0;
        while (low <= high) {
            long mid = (low + high)/2;
            if (mid*mid <= n) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid -1;
            }
        }
        return ans;
    }

    public int minimumRateToEatBananas(int[] nums, int h) {
        long low = 1;
        long high = Integer.MIN_VALUE;
        for (int num : nums) {
            high = Math.max(high,num);
        }
        long ans = 0;
        while (low <= high) {
            long mid = (low + high)/2;
            long value = summation(nums,mid);
            if ((int)value <= h) {
                high = mid-1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int)ans;
    }
    private long summation(int[] nums, long mid) {
        long sum = 0;
        for (int num : nums) {
            if (num % mid == 0) {
                sum = sum + (num / mid);
            } else {
                sum = sum + (num/mid) + 1;
            }
        }
        return sum;
    }

    public int roseGarden(int n, int[] nums, int k, int m) {
        int low = 1;
        int high = Integer.MIN_VALUE;
        for (int num : nums) {
            high = Math.max(high,num);
        }
        int ans = -1;
        while (low <= high) {
            int mid = (low + high)/2;
            boolean possible = possibility(nums,mid,k,m);
            if (possible) {
                ans = mid;
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    private boolean possibility(int[] nums, int mid, int k, int m){
        int flowerCount = 0;
        int count = 0;
        for (int num : nums) {
            if (num <= mid) flowerCount++;
            else flowerCount = 0;
            if (flowerCount == k) {
                count++;
                flowerCount=0;
            }
        }
        return count >= m;
    }

    public int aggressiveCows(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 1;
        int high = nums[nums.length-1] - nums[0];
        int ans = -1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (canWePlaceCow(nums,mid,k)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
    private boolean canWePlaceCow(int[] nums, int mid, int k) {
        int lastCow = nums[0];
        int count = 1;
        for (int i = 1;i<=nums.length-1;i++) {
            if (nums[i] - lastCow >= mid) {
                count++;
                lastCow = nums[i];
            }
        }
        return count == k;
    }

    public int findPages(int[] nums, int m) {
        int n = nums.length;
        if (m > n) return -1;
        int low = Integer.MIN_VALUE;
        int high = 0;
        for (int num : nums) {
            low = Math.max(low,num);
            high = high + num;
        }
        while (low <= high) {
            int mid = (low + high)/2;
            if (checkStudents(nums,mid) > m) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    private int checkStudents(int[] nums, int mid) {
        int n = nums.length;
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
    public double median(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        if (n1 > n2) return median(arr2,arr1);
        int low = 0;
        int high = n1;
        int overAllSize = n1+n2;
        int leftSubArraySize = (n1 + n2 + 1)/2;
        while (low <= high) {
            int mid1 = (low + high)/2;
            int mid2 = leftSubArraySize - mid1;
            int l1 = mid1 > 0 ? arr1[mid1-1] : Integer.MIN_VALUE;
            int r1 = mid1 < n1 ? arr1[mid1] : Integer.MAX_VALUE;
            int l2 = mid2 > 0 ? arr1[mid2-1] : Integer.MIN_VALUE;
            int r2 = mid2 < n2 ? arr1[mid2] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                if (overAllSize%2==1) return Math.max(l1,l2);
                else {
                    return (Math.max(l1,l2) + Math.min(r1,r2))/2.0;
                }
            } else if (l1 > r2) high = mid1-1;
            else {
                low = mid1 + 1;
            }
        }
        return 0;
    }

    public double minimiseMaxDistance(int[] arr, int k) {
        int n = arr.length;
        double low = 0;
        double high = 0;
        for (int i=0;i<=n-2;i++) {
            high = Math.max(high,arr[i+1]- arr[i]);
        }
        double difference = 1e-6;
        while (high-low > difference) {
            double mid = (low + high)/2;
            int numberOfGasStation = numberOfGasStations(arr,mid);
            if (numberOfGasStation < k) low = mid;
            else high = mid;
        }
         return high;
    }
    private int numberOfGasStations(int[] arr, double mid) {
        int count = 0;
        int n = arr.length;
        for (int i=0;i<=n-2;i++) {
            int numberInBw = (int)((arr[i+1] - arr[i])/mid);
            if ((arr[i+1] - arr[i]) == mid * numberInBw) numberInBw--;
            count = count + numberInBw;
        }
        return count;
    }

    public int kthElement(int[] a, int[] b, int k) {
        int n1 = a.length;
        int n2 = b.length;
        if (n1 > n2) return kthElement(b,a,k);
        int low = 0;
        int high = n1;
        int overAllSize = n1+n2;
        while (low <= high) {
            int mid1 = (low + high)/2;
            int mid2 = k - mid1;
            int l1 = mid1 > 0 ? a[mid1-1] : Integer.MIN_VALUE;
            int r1 = mid1 < n1 ? a[mid1] : Integer.MAX_VALUE;
            int l2 = mid2 > 0 ? b[mid2-1] : Integer.MIN_VALUE;
            int r2 = mid2 < n2 ? b[mid2] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1,l2);
            } else if (l1 > r2) high = mid1-1;
            else {
                low = mid1 + 1;
            }
        }
        return 0;
    }

    public int largestSubarraySumMinimized(int[] a, int k) {
        int n = a.length;
        if (k > n) return -1;
        int low = Integer.MIN_VALUE;
        int high = 0;
        for(int num : a) {
            low = Math.max(low,num);
            high = high + num;
        }
        while (low <= high) {
            int mid = (low + high)/2;
            if (checkAllotment(a, mid) < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    private int checkAllotment(int[] a, int mid) {
        int n = a.length;
        int count = 1;
        int total = 0;
        for (int num : a) {
            if (total + num <= mid) {
                total = total + num;
            } else {
                count++;
                total = num;
            }
        }
        return count;
    }

    public int rowWithMax1s(int[][] mat) {
        int rows = mat.length;
        int columnSize = mat[0].length;
        int maxOnes = -1;
        for (int i=0;i<rows;i++) {
            int low = 0;
            int high = mat[i].length-1;
            int startingOne = -1;
            while (low<=high) {
                int mid = (low + high)/2;
                if (mat[i][mid] == 1) {
                    startingOne = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            if (startingOne != -1) {
                int numberOfOnes = columnSize - startingOne;
                if (numberOfOnes > maxOnes) {
                    maxOnes = numberOfOnes;
                }
            }
        }
        return maxOnes;
    }
    public boolean searchMatrix(int[][] mat, int target) {
        int low = 0;
        int high = mat.length * mat[0].length;
        while (low <= high) {
            int mid = (low + high)/2;
            int newRow = mid / mat[0].length;
            int newCol = mid % mat[0].length;
            if (mat[newRow][newCol] == target) return true;
            else if (mat[newRow][newCol] < target) low = mid +1;
            else high = mid -1;
        }
        return false;
    }
}

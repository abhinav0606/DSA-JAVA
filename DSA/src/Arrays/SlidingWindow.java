package Arrays;

public class SlidingWindow {
    public static void main(String[] args) {
        int[] arr = {7,1,5,2,1,6,1,1,2,2};
        System.out.println(containerWithMostWater(arr));
        sumOfSubArrayWithSizeBruteForce(arr,2);
        sumOfSubArrayWithSlidingWindow(arr,2);
        System.out.println(maxOfSubArrarSum(arr,2));
        System.out.println("SUB-ARRAY-START");
        System.out.println(longestSubArrayWithSumBruteForce(arr,6));
        System.out.println(longestSubArrayWithSum(arr,6));
        System.out.println("-------------------------");
        System.out.println(smallestSubArrayWithSumBruteForce(arr,6));
        System.out.println(smallestSubArrayWithSum(arr,6));
        System.out.println("----------");
        int[] arr1 = {1,1,1,1,1,1,0,0,1,1,1,0,1,1,0,1};
        System.out.println(maxConsecutive1s(arr1,1));
    }
    private static int containerWithMostWater(int [] arr) {
        int left = 0;
        int right = arr.length-1;
        int maxArea = 0;
        while (left<right) {
            int width = right-left;
            int minHeight = Math.min(arr[left],arr[right]);
            int area = width * minHeight;
            maxArea = Math.max(maxArea,area);
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
    private static void sumOfSubArrayWithSizeBruteForce(int[] arr,int k) {
        for (int i=0;i<=arr.length-k;i++) {
            int windowSum = 0;
            for (int j=i;j<i+k;j++) {
                windowSum = windowSum + arr[j];
            }
            System.out.println(windowSum);
        }
    }
    private static void sumOfSubArrayWithSlidingWindow(int[] arr,int k) {
        // initializing starting and end pointer
        int i=0;
        int j=0;
        int cws = 0;
        while (j<arr.length) {
            cws = cws + arr[j];
            if (j-i+1 == k) {
                System.out.println(cws);
                cws = cws - arr[i];
                i++;
            }
            j++;
        }
    }
    private static int maxOfSubArrarSum(int[] arr, int k) {
        int i=0;
        int j=0;
        int cws = 0;
        int maxSum = Integer.MIN_VALUE;
        while (j<arr.length) {
            cws = cws + arr[j];
            if (j-i+1 == k) {
                maxSum = Math.max(maxSum,cws);
                System.out.println(cws);
                cws = cws - arr[i];
                i++;
            }
            j++;
        }
        return maxSum;
    }
    private static int longestSubArrayWithSumBruteForce(int [] arr,int sum) {
        int maxLength = -1;
        for (int i=0;i<arr.length;i++) {
            int currentSum = 0;
            for (int j=i;j<arr.length;j++) {
                currentSum = currentSum + arr[j];
                if (currentSum == sum) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }
    private static int smallestSubArrayWithSumBruteForce(int [] arr,int sum) {
        int minLength = Integer.MAX_VALUE;
        for (int i=0;i<arr.length;i++) {
            int currentSum = 0;
            for (int j=i;j<arr.length;j++) {
                currentSum = currentSum + arr[j];
                if (currentSum == sum) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength;
    }
    private static int longestSubArrayWithSum(int [] arr, int sum) {
        int i=0;
        int j=0;
        int maxLength = Integer.MIN_VALUE;
        int cws = 0;
        while (j<arr.length) {
            cws = cws + arr[j];
            while (cws>sum) {
                cws = cws - arr[i];
                i++;
            }
            if (cws == sum) {
                maxLength = Math.max(maxLength,j-i+1);
                cws = cws - arr[i];
                i++;
            }
            j++;
        }
        return maxLength;
    }
    private static int smallestSubArrayWithSum(int [] arr, int sum) {
        int i=0;
        int j=0;
        int minLength = Integer.MAX_VALUE;
        int cws = 0;
        while (j<arr.length) {
            cws = cws + arr[j];
            while (cws>sum) {
                cws = cws - arr[i];
                i++;
            }
            if (cws == sum) {
                minLength = Math.min(minLength,j-i+1);
                cws = cws - arr[i];
                i++;
            }
            j++;
        }
        return minLength;
    }
    private static int maxConsecutive1s(int [] arr, int flip) {
        int i=0;int j=0;int maxLength = Integer.MIN_VALUE;
        int zc = 0;
        while (j<arr.length) {
            if (arr[j] == 0) {
                zc++;
            }
            while (zc>flip) {
                if (arr[i] == 0) { zc--; }
                i++;
            }
            maxLength = Math.max(maxLength,j-i+1);
            j++;
        }
        return maxLength;
    }

    public static int findIndexofZeroForConsecutiveOnes(int n, int[] arr) {
        int i=0;int j=0;int maxLength = Integer.MIN_VALUE;
        int zc = 0;
        int zindex = -1;
        while (j<n) {
            if (arr[j] == 0) {
                zc++;
            }
            while (zc>1) {
                if (arr[i] == 0) {
                    zc--; }
                i++;
            }
            if (maxLength < j-i+1) {
                maxLength = j-i+1;
                for (int k=i;k<=j;k++) {
                    if (arr[k] == 0) {
                        zindex = k;
                        break;
                    }
                }
            }
            j++;
        }
        return zindex;

    }
}

package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem {
    public static void main(String[] args) {
        int[] arr = {3,4,1,12,5,6};
        System.out.println("-----------------------------Easy-----------------------------");
        System.out.println("Maximum element in an array");
        System.out.println(maxElementInArray(arr));
        System.out.println("Remove Duplicate from Sorted Array");
        arr = new int[]{1, 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 7};
        System.out.println(Arrays.toString(removeDuplicateFromSortedArray(arr)));
        System.out.println("Rotate an array k times");
        arr = new int[]{1,2,3,4,5};
        rotateArrayKTimes(arr,3);
        System.out.println(Arrays.toString(arr));
        System.out.println("Second Largest element of array");
        System.out.println(secondLargest(arr));
        System.out.println("Check if array is sorted and rotated");
        arr = new int[]{1,2,3,4,5};
        rotateArrayKTimes(arr,1);
        System.out.println(isSortedAndRotated(arr));
        arr = new int[]{1,2,3,4,5};
        rotateArrayKTimes(arr,2);
        System.out.println(isSortedAndRotated(arr));
        arr = new int[] {1,2,4,3,6,5};
        System.out.println(isSortedAndRotated(arr));
        System.out.println("Move Zero to end");
        arr = new int[] {1,2,0,4,5,0,0,0,1};
        moveZerosToEnd(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("Intersection of 2 arrays");
        int[] arr1 = new int[]{1,3,2,4,5,6,6};
        int[] arr2 = new int[]{2,1,3,5,8,9};
        findIntersectionOfArrays(arr1,arr2);
        System.out.println("Pairs with Given Sum");
        arr = new int[]{4,3,2,7,1,4};
        pairWithGivenSum(arr,8);
        System.out.println("---------------------Medium-------------------");
        System.out.println("SubArray with given sum");
        arr = new int[]{4,3,2,7,1,-4};
        subArrayWithGivenSum(arr,5);
        System.out.println("Kadanes Algorithms");
        System.out.println(kadaneAlgo(arr));
        System.out.println("Dutch National Flag Algo");
        arr = new int[]{1,2,0,0,2,1,1,0};
        dutchNationalFlag(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("Majority Elemenet");
        arr = new int[]{1,4,1,1};
        System.out.println(majorityElement(arr));
        System.out.println("Smallest subarray with sum greater than 4");
        System.out.println(smallestSubArrayWithSumGreater(arr,4));
        System.out.println("Longest Consequitive elements");
        arr = new int[]{100,101,1,2,5,4,3};
        System.out.println(longestConsecutiveLength(arr));
        System.out.println("Triplet with sum 0");
        arr = new int[] {1,2,-1,-2,-3};
        tripletWithTargetSum(arr);
        int[][] arr2d = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        mergeIntervalProblemUsingList(arr2d);
        System.out.println("-----------------Hard------------------------");
        System.out.println("First missing positive integer");
        arr = new int[]{-1,1,2,4,99};
        System.out.println(firstMissingPositiveInteger(arr));
        System.out.println("Trapping rain water problem");
        arr = new int[] {4,2,0,3,2,5};
        System.out.println(trappingRainWaterProblem(arr));
        System.out.println("Minimum number in rotated sorted array");
        arr=new int[] {99,1,3,4,5};
        System.out.println(minimumInRotatedSortedArray(arr));
        System.out.println("Median of 2 sorted arrays");
        arr1 = new int[] {1,2,3,4,5,6};
        arr2 = new int[] {1,7,8,9,9,10};
        System.out.println(medianOfAnArray(arr1,arr2));
        System.out.println("Product of array except self.");
        arr = new int[] {1,2,3,4};
        System.out.println(Arrays.toString(productOfArrayExceptSelf(arr)));
    }
    private static int maxElementInArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i=0;i<arr.length;i++) {
            max = Math.max(max,arr[i]);
        }
        return max;
    }
    private static int[] removeDuplicateFromSortedArray(int[] arr) {
        int j=0;
        for(int i=1;i<arr.length;i++) {
            if (arr[i]!=arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        return Arrays.copyOfRange(arr,0,j);
    }
    private static void rotateArrayKTimes(int[] arr,int k) {
        int length = arr.length;
        reverseArray(arr,length-k,length-1);
        reverseArray(arr,0,length-k-1);
        reverseArray(arr,0,length-1);
    }
    private static void reverseArray(int[] arr,int start,int end) {
        while (start<end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    private static int secondLargest(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sMax = Integer.MIN_VALUE;
        for (int i: arr) {
            if (i>max) {
                sMax = max;
                max = i;
            } else if (i<max && i>sMax) {
                sMax = i;
            }
        }
        return sMax;
    }
    private static boolean isSortedAndRotated(int[] arr) {
        int i=0;
        int n=arr.length;
        int count = 0;
        for (i=0;i<n;i++) {
            if (arr[i]>arr[(i+1)%n]) {
                count++;
            }
        }
        return count<=1;
    }
    private static void moveZerosToEnd(int[] arr) {
        int j=0;
        for (int i=0;i<arr.length;i++) {
            if (arr[i]!=0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
    }
    private static void findIntersectionOfArrays(int[] arr1,int[] arr2){
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int i=0;
        int j=0;
        while(i<=arr1.length-1 && j<=arr2.length-1) {
            if (arr1[i] == arr2[j]) {
                System.out.print(arr1[i]+" ");
                i++;j++;
            } else if (arr1[i]>arr2[j]) {j++;}
            else {i++;}
        }
    }
    private static void pairWithGivenSum(int[] arr,int targetSum) {
        Arrays.sort(arr);
        int i=0;
        int j=arr.length-1;
        while(i<j) {
            if (arr[i]+arr[j]==targetSum) {
                System.out.printf("%s,%s ",i,j);
                i++;j--;
            } else if (arr[i]+arr[j]>targetSum) {
                j--;
            } else {
                i++;
            }
        }
    }

    private static void subArrayWithGivenSum(int[] arr,int sum) {
        int i=0;
        int j=0;
        int cws = 0;
        while (j<arr.length) {
            cws = cws + arr[j];
            while(cws>sum) {
                cws = cws - arr[i];
                i++;
            }
            if (cws == sum) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(arr, i, j+1)));
                cws = cws - arr[i];
                i++;j++;
                continue;
            }
            j++;
        }
    }
    private static int kadaneAlgo(int[] arr) {
        int ms = arr[0];
        int cs = arr[0];
        for (int i=1;i<arr.length;i++) {
            cs=Math.max(arr[i],cs+arr[i]);
            ms=Math.max(cs,ms);
        }
        return ms;
    }
    private static void dutchNationalFlag(int[] arr) {
        int low=0;
        int mid=0;
        int high=arr.length-1;
        while (mid<high) {
            if (arr[mid]==0) {
                int temp = arr[mid];
                arr[mid] = arr[low];
                arr[low] = temp;
                low++;mid++;
            } else if (arr[mid] == 1) {
                mid ++;
            } else {
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
        }
    }

    private static int majorityElement(int[] arr) {
        int count=1;
        int majorityElement = arr[0];
        for (int i=1;i<arr.length;i++) {
            if (count == 0) {
                majorityElement = arr[i];
                count++;
            }
            else if (arr[i] == arr[i-1]) {
                count++;
            } else {
                count--;
            }
        }
        if (count==0) {return -1;}
        return majorityElement;
    }
    private static int smallestSubArrayWithSumGreater(int[] arr,int x) {
        int i=0;
        int j=0;
        int cws=0;
        int start = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;
        while(j<arr.length) {
            cws = cws + arr[j];
            while (cws>x) {
                minLength = Math.min(minLength,j-i+1);
                cws = cws -arr[i];
                i++;
            }
            j++;
        }
        return minLength;
    }
    private static int longestConsecutiveLength(int[] arr) {
        int cStreak = 1;
        int longestStreak = 1;
        Arrays.sort(arr);
        for (int i=1;i<arr.length;i++) {
            if (arr[i]==arr[i-1]+1) {
                cStreak++;
            } else {
                longestStreak = Math.max(longestStreak,cStreak);
                cStreak = 1;
            }
        }
        longestStreak = Math.max(longestStreak,cStreak);
        return longestStreak;
    }
    private static void tripletWithTargetSum(int[] arr){
        Arrays.sort(arr);
        for (int i=0;i<arr.length-2;i++) {
            int left = i+1;
            int right = arr.length-1;
            while (left<right) {
                if (arr[right] + arr[left] == -arr[i]) {
                    System.out.printf("triplet : %s,%s,%s",arr[i],arr[left],arr[right]);
                    left ++;right--;
                } else if (arr[left] + arr[right] < -arr[i]) {
                    left++;
                } else { right--; }
            }
        }
    }
    private static void mergeIntervalProblemUsingList(int[][] arr) {
        Arrays.sort(arr,(a,b) -> Integer.compare(a[0],b[0]));
        LinkedList<int[]> intervalList = new LinkedList<>();
        for (int[] verl : arr) {
            if (intervalList.isEmpty() || intervalList.getLast()[1] < verl[0]) {
                intervalList.add(verl);
            } else {
                intervalList.getLast()[1] = Math.max(intervalList.getLast()[1], verl[1]);
            }
        }
        System.out.println("");
        System.out.println("Merged intervals:");
        for (int[] interval : intervalList) {
            System.out.println(Arrays.toString(interval));
        }
    }
    private static int firstMissingPositiveInteger(int[] arr) {
        int n = arr.length;
        for (int i=0;i<n;i++) {
            if (arr[i]<=0) {
                arr[i] = n+1;
            }
        }
        for (int i=0;i<n;i++) {
            int absValue = Math.abs(arr[i]);
            if (absValue < n+1) {
                arr[absValue-1] = -arr[absValue-1];
            }
        }
        for (int i=0;i<n;i++) {
            if (arr[i]>0) {
                return i+1;
            }
        }
        return n+1;
    }
    private static int trappingRainWaterProblem(int[] arr) {
        int lp=0;
        int rp=arr.length-1;
        int mlh=0;
        int mrh=0;
        int totalWater=0;
        while(lp<rp) {
            if (arr[lp]<arr[rp]) {
                if (arr[lp] > mlh) {
                    mlh = arr[lp];
                } else {
                    totalWater = totalWater + mlh - arr[lp];
                }
                lp++;
            } else {
                if (arr[rp] > mrh) {
                    mrh = arr[rp];
                } else {
                    totalWater = totalWater + mrh - arr[rp];
                }
                rp--;
            }
        }
        return totalWater;
    }
    private static int minimumInRotatedSortedArray(int[] arr) {
        int result = -1;
        for (int i=0;i<arr.length;i++) {
            if (arr[i]>arr[(i+1)%arr.length]) {
                result = arr[(i+1)%arr.length];
            }
        }
        return result;
    }

    private static float medianOfAnArray(int[] arr1,int[] arr2) {
        int[] ansArr = new int[arr1.length+arr2.length];
        int p1=0;
        int p2=0;
        int k=0;
        while (p1<arr1.length && p2<arr2.length) {
            if (arr1[p1]<=arr2[p2]) {
                ansArr[k++] = arr1[p1++];
            } else {
                ansArr[k++] = arr2[p2++];
            }
        }
        while (p1<arr1.length) {
            ansArr[k++] = arr1[p1++];
        }
        while (p2<arr2.length) {
            ansArr[k++] = arr2[p2++];
        }
        if (ansArr.length % 2 == 0) {
            return (float) (ansArr[(k / 2) - 1] + ansArr[k / 2]) /2;
        } else {
            return (float) (ansArr[k/2]);
        }
    }
    private static int[] productOfArrayExceptSelf(int[] arr){
        int[] result = new int[arr.length];
        result[0] = 1;
        for (int i=1;i<arr.length;i++) {
            result[i] = result[i-1] * arr[i-1];
        }
        int rightProduct = 1;
        for (int i=arr.length-1;i>=0;i--) {
            result [i] = result[i] * rightProduct;
            rightProduct = rightProduct * arr[i];
        }
        return result;
    }
}

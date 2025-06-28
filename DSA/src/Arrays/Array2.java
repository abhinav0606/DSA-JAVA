package Arrays;

import java.util.Arrays;

public class Array2 {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        System.out.println(bestBuyAndSellProblem(arr));
        System.out.println(findMissingSmallestPositiveIntegerBruteForce(arr));
        System.out.println(findMissingSmallestPositiveInteger(arr));
        int[] arr1 = {5,1,4,1,1,4};
        System.out.println(majorityElementMooreAlgo(arr1));
        int[] arr2 = {1,2,3,4,5};
        System.out.println(Arrays.toString(rotateAnArrayBruteForce(arr2, 1)));
        arr2 = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(rotateAnArray(arr2,1)));
        int[] arr3 = {4,2,0,3,2,5};
        System.out.println(trappingWaterProblem(arr3));
    }
    private static int bestBuyAndSellProblem(int[] arr) {
        int minValue = arr[0];
        int maxProfit = 0;
        for (int i=1;i<arr.length;i++) {
            if (minValue > arr[i]) {
                minValue = arr[i];
            } else if (maxProfit < arr[i]-minValue) {
                maxProfit = arr[i]-minValue;
            }
        }
        return maxProfit;
    }
    private static int findMissingSmallestPositiveIntegerBruteForce(int[] arr) {
        // we are sure that we will get the missing +ve bw 1 and arr.length +1
        for (int i=1;i<=arr.length+1;i++) {
            boolean found = false;
            for (int k : arr) {
                if (i == k) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    private static int findMissingSmallestPositiveInteger(int[] arr) {
        //1. mark the negative numbers with arr.length +1
        //2. the numbers which are having a position mark their index value with negative
        //3. return the index value which is having positive number
        for(int i=0;i<arr.length;i++){
            if (arr[i]<=0) {
                arr[i] = arr.length+1;

            }
        }
        for (int i=0;i<arr.length;i++) {
            int absValue = Math.abs(arr[i]);
            if (absValue<arr.length+1) {
                arr[absValue-1] = -arr[absValue-1];
            }
        }
        for (int i=0;i<arr.length;i++) {
            if (arr[i]>0) {
                return i+1;
            }
        }
        return -1;
    }
    private static int majorityElementMooreAlgo(int[] arr) {
        int majElement = arr[0];
        int count = 1;
        for (int i=1;i<arr.length;i++) {
            if (count == 0) {
                majElement = arr[i];
            }
            else if (arr[i] == arr[i-1]) {
                count ++;
            } else {
                count --;
            }
        }
        if (count == 0) {return -1;}
        return majElement;
    }
    private static int[] rotateAnArrayBruteForce(int[] arr,int target) {
        int n = arr.length;
        for(int i=0;i<target;i++) {
            int last = arr[n-1];
            for (int j=n-1;j>0;j--) {
                arr[j] = arr[j-1];
            }
            arr[0] = last;
        }
        return arr;
    }
    private static int[] rotateAnArray(int[] arr,int target) {
        //reverse last k elements
        //reverse first n-k elements
        // reverse whole array
        reverseArray(arr,arr.length-target,arr.length-1);
        reverseArray(arr,0,arr.length-target-1);
        reverseArray(arr,0,arr.length-1);
        return arr;
    }
    private static void reverseArray(int[] arr,int start,int end) {
        while(start<end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    private static int trappingWaterProblem(int[] arr) {
        int lp=0;
        int rp=arr.length-1;
        int mlh=0;
        int mrh=0;
        int totalWater = 0;
        while (lp<rp) {
            if (arr[lp]<arr[rp]) {
                if (mlh <= arr[lp]) {
                    mlh = arr[lp];
                } else {
                    totalWater = totalWater + mlh - arr[lp];
                }
                lp++;
            } else {
                if (mrh <= arr[rp]) {
                    mrh = arr[rp];
                } else {
                    totalWater = totalWater + mrh - arr[rp];
                }
                rp--;
            }
        }
        return totalWater;
    }
}

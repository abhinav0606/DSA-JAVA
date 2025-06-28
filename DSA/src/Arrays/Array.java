package Arrays;

import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        int[] arr = {2, 7,9,10};
        printArray(arr);
        System.out.println();
        System.out.println(sumArray(arr));
        System.out.println(Arrays.toString(prefixSumBruteForce(arr)));
        System.out.println(Arrays.toString(prefixSumOptimized(arr)));
        System.out.println(secondMaxArray(arr));
        System.out.println(secondMinArray(arr));
        System.out.println(isAscending(arr));
        System.out.println(isDescending(arr));
        System.out.println(nthElementOfFibonacci(5));
        System.out.println(Arrays.toString(merge2SortedArray(new int[]{2, 7, 9, 10}, new int[]{1, 3, 5, 7})));
        pairSum(new int[]{1,2,3,4,5});
        System.out.println();
        System.out.println(Arrays.toString(moveZeroToEnd(new int[] {1,2,0,0,5,7,2})));
        System.out.println(Arrays.toString(moveZeroToStart(new int[] {1,2,3,0,0,0,2,3})));
        System.out.println(maxSubArraySumAP1(new int[]{1,2,-3,4,5}));
        System.out.println(maxSubArrayKadane(new int[] {1,2,-3,4,5}));
        System.out.println(Arrays.toString(freqCountOfSortedArray(new int[]{1, 2, 2, 3, 3, 4})));
    }
    private static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int j: arr) {
            sum = sum + j;
        }
        return sum;
    }
    private static int[] prefixSumBruteForce(int[] arr) {
        int[] ansArr = new int[arr.length];
        int sum = 0;
        for(int j=0;j<arr.length;j++) {
            sum = sum + arr[j];
            ansArr[j] = sum;
        }
        return ansArr;
    }
    private static int[] prefixSumOptimized(int[] arr) {
        int sum = 0;
        for(int j=1;j<arr.length;j++) {
            arr[j] = arr[j] + arr[j-1];
        }
        return arr;
    }
    private static int secondMaxArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        for (int j: arr) {
            if (j>max) {
                min = max;
                max = j;
            } else if (j>min && j<max) {
                min = j;
            }
        }
        return min;
    }
    private static int secondMinArray(int[] arr) {
        int max = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j: arr) {
            if (j<max) {
                min = max;
                max = j;
            } else if (j<min && j>max) {
                min = j;
            }
        }
        return min;
    }
    private static boolean isAscending(int[] arr) {
        for (int i=1;i<arr.length;i++) {
            if (arr[i]<arr[i-1]){
                return false;
            }
        }
        return true;
    }
    private static boolean isDescending(int[] arr) {
        for (int i=1;i<arr.length;i++) {
            if (arr[i]>arr[i-1]){
                return false;
            }
        }
        return true;
    }
    private static int nthElementOfFibonacci(int n) {
        if (n==1) return 0;
        else if (n==2) return 1;
        else {
            int a=0;int b = 1; int c = 0;
            for(int i=2;i<n;i++) {
               c = a + b;
               a = b;
               b = c;
            }
            return c;
        }
    }
    private static int[] merge2SortedArray(int[] a1, int[] a2) {
        int[] ans = new int[a1.length + a2.length];
        int i=0;int j=0;int k=0;
        while (i<a1.length && j<a2.length) {
            if (a1[i] <= a2[j]) {
                ans[k++] = a1[i++];
            } else {
                ans[k++] = a2[j++];
            }
        }
        while (i<a1.length) {
            ans[k++] = a1[i++];
        }
        while (j<a2.length) {
            ans[k++] = a2[j++];
        }
        return ans;
    }
    private static void pairSum(int[] arr) {
        int i=0;int j=arr.length-1;
        while (i<j){
            if (arr[i] + arr[j] <6) {
                i++;
            } else if (arr[i] + arr[j] > 6) {
                j--;
            } else {
                System.out.print(i + "-");
                System.out.print(j);
                break;
            }
        }
    }
    private static int[] moveZeroToEnd(int[] arr) {
        int i=0;int j=0;
        for (i=0;i<arr.length;i++) {
            if (arr[i]!=0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        return arr;
    }
    private static int[] moveZeroToStart(int[] arr) {
        int i=0;int j=0;
        for (i=0;i<arr.length;i++) {
            if (arr[i]==0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        return arr;
    }
    private static int maxSubArraySumAP1(int[] arr) {
        int cs=arr[0];
        int ms=arr[0];
        for (int j = 1;j<arr.length;j++) {
            cs = cs + arr[j];
            ms = Math.max(cs, ms);
            if (cs <= 0) {
                cs = 0;
            }
        }
        return ms;
    }
    private static int maxSubArrayKadane(int [] arr) {
        // In this we will check if we need to include the next element or not
        int cs = arr[0];
        int ms = arr[0];
        for (int i=1;i<arr.length;i++) {
            cs= Math.max(arr[i],cs + arr[i]);
            ms = Math.max(cs,ms);
        }
        return ms;
    }
    private static int[] freqCountOfSortedArray(int [] arr) {
        int[] ans = new int [Arrays.stream(arr).max().orElseThrow(() -> new RuntimeException("Array is empty")) +1];
        for (int j : arr) {
            ans[j]++;
        }
        return ans;
    }
}

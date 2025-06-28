package Striver;

import java.util.Arrays;
import java.util.Stack;

public class imp6 {
    public static void main(String[] args) {
        pattern22(5);
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10}));
        System.out.println(Arrays.toString(stockSpan(new int[]{100, 80, 60, 70, 60, 75, 85})));
    }

    //Basically we will check if the number is less than equal to 1 it is not prime because 0 and 1 are not prime
    private static boolean checkPrime(int number) {
        if (number <= 1) return false;
        return prime(number,2);
    }
    // if the sqrt of the number is less than the given to be divisor then it is a prime numbers
    // if the number is able to divide with the given divisor then it is not prime
    // recursively do this with increament of the divisor
    private static boolean prime(int number,int x) {
        if (x > Math.sqrt(number)) return true;
        if (number % x == 0) return false;
        return prime(number,x+1);
    }

    private static void pattern22(int n) {
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                // finding the min distance bw top and left and another one is from right and bottom
                int minDist = Math.min(Math.min(i, j), Math.min(2 * n - 2 - i, 2 * n - 2 - j));
                System.out.print((n - minDist) + " ");
            }
            System.out.println();
        }
    }

    //Median of 2 sorted arrays
    // Merging and then finding the median
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0;
        int j=0;
        int[] result = new int[nums1.length + nums2.length];
        int k=0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                result[k] = nums1[i];
                i++;
                k++;
            } else {
                result[k] = nums2[j];
                j++;
                k++;
            }
        }
        while (j<nums2.length) {
            result[k] = nums2[j];
            j++;
            k++;
        }
        while (i<nums1.length) {
            result[k] = nums1[i];
            i++;
            k++;
        }
        if (result.length % 2 == 0) {
            int middle = result.length/2;
            return (double) (result[middle - 1] + result[middle]) /2;
        } else {
            int middle = result.length/2;
            return result[middle];
        }
    }

    // stock span using stacks
    // If the stack is empty set the array index with the currect index value
    // else set them with i-last set element in stack that will be most probably the highest one than the current
    // if the current value is greater than the previous values pop out the value to reach to a extent to get the number of
    // days of span and set it accordingly
    private static int[] stockSpan(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            while (!stack.isEmpty() && arr[i]>arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = 1;
            } else {
                result[i] = i-stack.peek();
            }
            stack.push(i);
        }
        return result;
    }

    // Important
    static class StockSpanner {
        private Stack<int[]> stack = new Stack<>();
        public StockSpanner() {
            this.stack = new Stack<>();
        }

        public int next(int price) {
            int span = 1;
            while (!stack.isEmpty() && price > stack.peek()[0]) {
                span = span + stack.pop()[1];
            }
            stack.push(new int[] {price,span});
            return span;
        }
    }

}

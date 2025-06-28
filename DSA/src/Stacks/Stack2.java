package Stacks;

import java.util.Arrays;
import java.util.Stack;

public class Stack2 {
    public static void main(String[] args) {
        int[] arr = new int[] {100,80,60,70,60,75,85};
        int[][] arr1 = {{0,1,0},{0,0,0},{0,1,0}};
        stockSpanBruteForce(arr);
        stockSpan(arr);
        celebrityQuestionBruteForce(arr1);
        celebrityQuestion(arr1);
        System.out.println(maxAreaCovered(new int[] {2,1,5,6,2,3}));
        int[][] arr2 = {{0,1,1,0},{1,1,1,1},{1,1,1,1},{1,1,0,0}};
        System.out.println(maximalRectangle(arr2));
    }
    private static void stockSpanBruteForce(int[] arr) {
        int[] ans = new int[arr.length];
        ans[0] = 1;
        for(int i=0;i<arr.length;i++) {
            int count = 1;
            for(int j=i-1;j>=0;j--) {
                if (arr[j] <= arr[i]) {
                    count++;
                } else {
                    break;
                }
            }
            ans[i] = count;
        }
        System.out.println(Arrays.toString(ans));
    }
    private static void stockSpan(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = 1;
            } else {
                ans[i] = i - stack.peek();
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(ans));
    }
    private static void celebrityQuestionBruteForce(int[][] arr) {
        boolean isCelebrity = false;
        for (int i=0;i< arr.length;i++) {
            isCelebrity = true;
            for (int j=0;j<arr[0].length;j++) {
                if (i != j) {
                    if (arr[i][j] == 1 || arr[j][i] == 0) {
                        isCelebrity = false;
                        break;
                    }
                }
            }
            if (isCelebrity) {
                System.out.print("Celebrity is :");
                System.out.println(i+1);
                break;
            }
        }
        if (!isCelebrity) System.out.println(-1);
    }
    private static void celebrityQuestion(int[][] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<arr.length;i++) {
            stack.push(i);
        }
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            if (arr[a][b] == 1 || arr[b][a] == 0) {
                stack.push(b);
            } else {
                stack.push(a);
            }
        }
        if (stack.isEmpty()) {
            System.out.println("No celebrity");
        }
        int candidate = stack.pop();
        for (int i=0;i<arr.length;i++) {
            if (i!=candidate) {
                if (arr[i][candidate] == 1 && arr[candidate][i] == 0) {
                    System.out.print("Celebrity is ");
                    System.out.println(candidate+1);
                    break;
                }
            }
        }
    }
    private static int maxAreaCovered(int[] arr) {
        int area = 0;
        int[] nsl = nextSmallerToLeft(arr);
        int[] nsr = nextSmallerToRight(arr);
        for (int i=0;i<arr.length;i++) {
            int width = nsr[i] - nsl[i] - 1;
            area = Math.max(area,arr[i]*width);
        }
        return area;
    }
    private static int[] nextSmallerToRight(int [] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i=arr.length-1;i>=0;i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = arr.length;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(i);
        }
        return ans;
    }
    private static int[] nextSmallerToLeft(int [] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(i);
        }
        return ans;
    }
    private static int maximalRectangle(int[][] arr) {
        int[] height = new int[arr[0].length];
        int area = 0;
        for (int i=0;i<arr.length;i++) {
            for (int j=0;j<arr[0].length;j++) {
                if (arr[i][j] == 1) {
                    height[j]++;
                } else height[j] = 0;
            }
            area = Math.max(area,maxAreaCovered(height));
        }
        return area;
    }
}

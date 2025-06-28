package Striver.StackQueue;

import java.util.Map;
import java.util.Stack;

public class MonotonicStack {
    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[] {11, 81, 94, 43, 3}));
    }
    public int[] nextLargerElement(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] ansArr = new int[arr.length];
        for (int i=arr.length-1;i>=0;i--) {
            int currentElement = arr[i];
            while (!s.isEmpty() && s.peek() <= currentElement) {
                s.pop();
            }
            if (s.isEmpty()) {
                ansArr[i] = -1;
            } else {
                ansArr[i] = s.peek();
            }
            s.push(arr[i]);
        }
        return ansArr;
    }
    public int[] nextGreaterElements(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] ansArr = new int[arr.length];
        int n = arr.length;
        for (int i=2*n - 1;i>=0;i--) {
            int index = i%n;
            int currentElement = arr[index];
            while (!s.isEmpty() && s.peek() <= currentElement) {
                s.pop();
            }
            if (s.isEmpty()) {
                ansArr[index] = -1;
            } else {
                ansArr[index] = s.peek();
            }
            s.push(arr[index]);
        }
        return ansArr;
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<asteroids.length;i++) {
            if (asteroids[i] > 0) {
                stack.push(asteroids[i]);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroids[i])) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == Math.abs(asteroids[i])) {
                    stack.pop();
                } else if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroids[i]);
                }
            }
        }
        int[] result = new int[stack.size()];
        int index = stack.size()-1;
        while (!stack.isEmpty()) {
            result[index] = stack.pop();
            index--;
        }
        return result;
    }

    private static int[] nse(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] ansArr = new int[arr.length];
        for (int i=arr.length-1;i>=0;i--) {
            int currentElement = arr[i];
            while (!s.isEmpty() && arr[s.peek()] >= currentElement) {
                s.pop();
            }
            if (s.isEmpty()) {
                ansArr[i] = arr.length;
            } else {
                ansArr[i] = s.peek();
            }
            s.push(i);
        }
        return ansArr;
    }
    private static int[] psee(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] ansArr = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            int currentElement = arr[i];
            while (!s.isEmpty() && arr[s.peek()] > currentElement) {
                s.pop();
            }
            if (s.isEmpty()) {
                ansArr[i] = -1;
            } else {
                ansArr[i] = s.peek();
            }
            s.push(i);
        }
        return ansArr;
    }

    private static int[] nge(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] ansArr = new int[arr.length];
        for (int i=arr.length-1;i>=0;i--) {
            int currentElement = arr[i];
            while (!s.isEmpty() && arr[s.peek()] <= currentElement) {
                s.pop();
            }
            if (s.isEmpty()) {
                ansArr[i] = arr.length;
            } else {
                ansArr[i] = s.peek();
            }
            s.push(i);
        }
        return ansArr;
    }
    private static int[] pgee(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] ansArr = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            int currentElement = arr[i];
            while (!s.isEmpty() && arr[s.peek()] < currentElement) {
                s.pop();
            }
            if (s.isEmpty()) {
                ansArr[i] = -1;
            } else {
                ansArr[i] = s.peek();
            }
            s.push(i);
        }
        return ansArr;
    }
    public static int sumSubarrayMins(int[] arr) {
        int[] psee = psee(arr);
        int[] nse = nse(arr);
        int mod = (int)1e9 + 7;
        int sum = 0;
        for (int i=0;i<arr.length;i++) {
            int left = i - psee[i];
            int right = nse[i] - i;
            int freq = (left*right);
            int val = (int)((freq*arr[i])%mod);
            sum = (sum + val) % mod;
        }
        return sum;
    }

    public static int sumSubarrayMaxs(int[] arr) {
        int[] psee = pgee(arr);
        int[] nse = nge(arr);
        int mod = (int)1e9 + 7;
        int sum = 0;
        for (int i=0;i<arr.length;i++) {
            int left = i - psee[i];
            int right = nse[i] - i;
            int freq = (left*right);
            int val = (int)((freq*arr[i])%mod);
            sum = (sum + val) % mod;
        }
        return sum;
    }

    public long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums)-sumSubarrayMins(nums);
    }

    public String removeKdigits(String nums, int k) {
        Stack<Character> st = new Stack<>();
        for (int i=0;i<nums.length();i++) {
            while (!st.isEmpty() && k>0 && st.peek() > nums.charAt(i)) {
                st.pop();
                k--;
            }
            st.push(nums.charAt(i));
        }
        while (!st.isEmpty() && k>0) {
            st.pop();
            k--;
        }
        if (st.isEmpty()) return "0";
        StringBuilder res = new StringBuilder();

        // Adding digits in stack to result
        while(!st.isEmpty()) {
            res.append(st.pop());
        }

        // Trimming the zeroes at the back
        while(!res.isEmpty() &&
                res.charAt(res.length() - 1) == '0') {

            res.deleteCharAt(res.length() - 1);
        }

        // Reverse to get the actual number
        res.reverse();

        // Edge case
        if(res.isEmpty()) return "0";

        // Return the stored result
        return res.toString();
    }
}

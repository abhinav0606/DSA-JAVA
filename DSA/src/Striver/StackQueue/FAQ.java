package Striver.StackQueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class FAQ {
    public static void main(String[] args) {

    }

    static class MinStack {
        private Stack<Integer> st;
        private int min;

        public MinStack() {
            st = new Stack<>();
        }

        public void push(int val) {
            if (st.isEmpty()) {
                st.push(val);
                min = val;
                return;
            }
            if (val < min) {
                st.push(2*val - min);
                min = val;
            } else {
                st.push(val);
            }
        }

        public void pop() {
            if (st.isEmpty()) return;
            int peek = st.pop();
            if (peek < min) {
                min = 2*min - peek;
            }
        }

        public int top() {
            if (st.isEmpty()) return -1;
            int peek = st.peek();
            if (peek < min) {
                return min;
            }
            return peek;
        }

        public int getMin() {
            return min;
        }
    }

    public int[] maxSlidingWindow(int[] arr, int k) {
        int ansIndex = 0;
        int n = arr.length;
        int[] ans = new int[n-k+1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i=0;i<n;i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= (i-k)) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k-1) {
                ans[ansIndex] = arr[deque.peekFirst()];
                ansIndex++;
            }
        }
        return ans;
    }

    private int[] pge(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i=0;i<arr.length;i++) {
            while (!st.isEmpty() && arr[i] >= arr[st.peek()]) {
                st.pop();
            }
            if (st.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = st.peek();
            }
            st.push(arr[i]);
        }
        return result;
    }

    public int[] stockSpan(int[] arr, int n) {
        int[] pge = pge(arr);
        int[] result = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            result[i] = i-pge[i];
        }
        return result;
    }

    public int celebrity(int[][] M) {
        int top = 0;
        int down = M.length - 1;
        while (top < down) {
            if (M[top][down] == 1) top++;
            else if (M[down][top] == 1) down--;
            else {
                top++;
                down--;
            }
        }
        if (top > down) return -1;

        for (int i=0;i<M.length;i++) {
            if (i == top) continue;
            if (M[i][top] == 0 || M[top][i] == 1) return -1;
        }
        return top;
    }

    public int trap(int[] height) {
        int rm = 0;
        int lm = 0;
        int l = 0;
        int r = height.length-1;
        int total = 0;
        while (l < r) {
            if (lm <= rm) {
                if (height[l] < lm) {
                    total = total + height[l];
                } else {
                    lm = height[l];
                }
                l++;
            } else {
                if (height[r] < rm) {
                    total = total + height[r];
                } else {
                    rm = height[r];
                }
                r--;
            }
        }
        return total;
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
    private static int[] pse(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] ansArr = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            int currentElement = arr[i];
            while (!s.isEmpty() && arr[s.peek()] >= currentElement) {
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

    public int largestRectangleArea(int[] heights) {
        int[] nse = nse(heights);
        int[] pse = pse(heights);
        int max = 0;
        for (int i=0;i<heights.length;i++) {
            max = Math.max(max,heights[i]*(nse[i]-pse[i]-1));
        }
        return max;
    }

    public int maximalAreaOfSubMatrixOfAll1(int[][] matrix) {
        int rows= matrix.length;
        int columns = matrix[0].length;
        int[][] prefixSum = new int[rows][columns];
        // Filling up column wise
        for (int column = 0;column < columns; column++) {
            int sum = 0;
            for (int row=0;row<rows;row++) {
                if (matrix[row][column] == 0) {
                    prefixSum[row][column] = 0;
                    sum = 0;
                } else {
                    sum = sum + matrix[row][column];
                    prefixSum[row][column] = sum;
                }
            }
        }
        int max = 0;
        for (int row = 0;row < rows;row++) {
            max = Math.max(max,largestRectangleArea(prefixSum[row]));
        }
        return max;
    }
}

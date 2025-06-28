package Striver.GreedAlgo;

import java.util.Stack;

public class Hard {
    public static void main(String[] args) {

    }
    public boolean isValid(String s) {
        int min = 0;
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                min--;
                max--;
            } else {
                // because we can consider * as ( or ) or empty string
                min--;
                max++;
            }
            if (max < 0) return false;
            if (min < 0) min = 0;
        }
        return min==0;
    }
    // Classic candy problem
    public int candy(int[] ratings) {
        int sum = 1;
        int i = 1;
        int n = ratings.length;
        while (i < n) {
            if (ratings[i] == ratings[i-1]) {
                sum = sum + 1;
                i++;
                continue;
            }
            int peak = 1;
            while (i < n && ratings[i] > ratings[i-1]) {
                peak++;
                sum = sum + peak;
                i++;
            }
            int down = 1;
            while (i < n && ratings[i] < ratings[i-1]) {
                sum = sum + down;
                down++;
                i++;
            }
            if (down > peak) {
                sum = sum + (down - peak);
            }
        }
        return sum;
    }

    public static boolean isValid2(String str) {
        Stack<Character> s = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') s.push(c);
            else {
                if (s.isEmpty()) return false;
                if (s.peek() == '(' && c == ')') {
                    s.pop();
                } else if (s.peek() == '{' && c == '}') {
                    s.pop();
                } else if (s.peek() == '[' && c == ']') {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        return s.isEmpty();
    }
}

package Stacks;

import java.util.List;
import java.util.Stack;

public class Hackerrank {
    public static void main(String[] args) {
        equalStacks(List.of(3,2,1,1,1),List.of(4,3,2),List.of(1,1,4,1));
    }
    private static String validParenthesis(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '('){
                stack.pop();
            }
            else if (c == '}' && !stack.isEmpty() && stack.peek() == '{'){
                stack.pop();
            }
            else if (c == ']' && !stack.isEmpty() && stack.peek() == '['){
                stack.pop();
            } else {
                return "No";
            }
        }
        return stack.isEmpty() ? "Yes" : "NO";
    }
    private static int equalStacks(List<Integer> l1,List<Integer> l2,List<Integer> l3) {
        int sum1=0;
        int sum2=0;
        int sum3=0;
        for(int i: l1) sum1 = sum1 + i;
        for(int i: l2) sum2 = sum2 + i;
        for(int i: l3) sum3 = sum3 + i;

        while (sum1 != sum2 || sum2 != sum3) {
            if (sum1 > sum2 && sum1 > sum3) {
                sum1 = sum1 - l1.remove(0);
            } else if (sum2 > sum1 && sum2 > sum3) {
                sum2 = sum2 - l2.removeFirst();
            } else if (sum3 > sum1 && sum3 > sum2){
                sum3 = sum3 - l3.removeFirst();
            }
            if (l1.isEmpty() || l2.isEmpty() || l3.isEmpty()) {
                return 0;
            }
         }
        return sum1;
    }
}

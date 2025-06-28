package Stacks;

import java.util.Arrays;
import java.util.Stack;

public class Stack1 {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class NodeWrapper {
        Node head;

        public NodeWrapper(Node head) {
            this.head = head;
        }
    }

    public static void main(String[] args) {
        // Last in First out
        int[] arr = new int[10];
        int top = -1;
        pop(arr, top);
        peek(arr, top);
        top = push(arr, 5, top);
        System.out.println(Arrays.toString(arr));

        NodeWrapper nodeWrapper = new NodeWrapper(null); // Wrapper for linked list stack
        pushList(nodeWrapper, 5);
        pushList(nodeWrapper, 6);
        traverseLinkedList(nodeWrapper.head);
        System.out.println((validParenthesisBruteForce("{}[]()")));
        System.out.println(validParenthesisBruteForce("{}[)]("));
        System.out.println((validParenthesis("{}[]()")));
        System.out.println(validParenthesis("{}[)]("));
        System.out.println(Arrays.toString(nextGreaterToRightBrute(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(nextGreaterToRight(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(nextSmallerToRight(new int[]{2, 1, 3, 2})));
        System.out.println(Arrays.toString(nextGreaterToLeft(new int[]{2, 1, 3, 2})));
        System.out.println(Arrays.toString(nextSmallerToLeft(new int[]{2, 1, 3, 2})));
    }

    public static int push(int[] arr, int element, int top) {
        if (top == arr.length - 1) {
            System.out.println("Overflow");
            return top;
        }
        top++;
        arr[top] = element;
        return top;
    }

    private static void pop(int[] arr, int top) {
        if (top == -1) {
            System.out.println("Underflow");
            return;
        }
        System.out.println(arr[top]);
        top--;
    }

    private static void peek(int[] arr, int top) {
        if (top == -1) {
            System.out.println("Underflow");
            return;
        }
        System.out.println(arr[top]);
    }

    private static void pushList(NodeWrapper wrapper, int data) {
        Node temp = new Node(data);
        temp.next = wrapper.head;
        wrapper.head = temp;
    }

    private void popList(NodeWrapper wrapper) {
        if (wrapper.head == null) {
            System.out.println("Underflow");
            return;
        }
        System.out.println(wrapper.head.data);
        wrapper.head = wrapper.head.next;
    }

    private void peekList(NodeWrapper wrapper) {
        if (wrapper.head == null) {
            System.out.println("Underflow");
            return;
        }
        System.out.println(wrapper.head.data);
    }

    private static void traverseLinkedList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }
    private static boolean validParenthesisBruteForce(String s) {
        boolean found = false;
        do{
            found = false;
            if (s.contains("()")) {
                found = true;
                s = s.replace("()","");
            }
            if (s.contains("{}")) {
                found = true;
                s = s.replace("{}","");
            }
            if (s.contains("[]")) {
                found = true;
                s = s.replace("[]","");
            }
            if (s.isEmpty()) {
                return true;
            }
        } while (found);
        return found;
    }
    private static boolean validParenthesis(String s) {
        Stack<Character> characters = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                characters.push(c);
            } else if (c == ')' && !characters.isEmpty() && characters.peek() == '(') {
                characters.pop();
            } else if (c == ']' && !characters.isEmpty() && characters.peek() == '[') {
                characters.pop();
            } else if (c == '}' && !characters.isEmpty() && characters.peek() == '{') {
                characters.pop();
            } else {
                return false;
            }
        }
        return true;
    }
    private static int[] nextGreaterToRightBrute(int[] arr) {
        int[] ans = new int[arr.length];
        for(int i=0;i<arr.length;i++) {
            for(int j=i+1;j<arr.length;j++) {
                if (arr[j] >= arr[i]) {
                    ans[i] = arr[j];
                    break;
                } else {
                    ans[i] = -1;
                }
            }
        }
        ans[arr.length-1] = -1;
        return ans;
    }
    private static int[] nextGreaterToRight(int [] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i=arr.length-1;i>=0;i--) {
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }
    private static int[] nextSmallerToRight(int [] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i=arr.length-1;i>=0;i--) {
            while (!stack.isEmpty() && arr[i] <= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }
    private static int[] nextGreaterToLeft(int [] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }
    private static int[] nextSmallerToLeft(int [] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i=0;i<arr.length;i++) {
            while (!stack.isEmpty() && arr[i] <= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }
}

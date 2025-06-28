package Queue;

import java.util.*;

public class Queue1 {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(10);
        q.add(20);
        q.add(30);
        queueReversal(q);
        System.out.println(q);
        queueReversalUsingStack(q);
        System.out.println(q);
        postFixWithQueue("23*5+");
        postFixWithStack("23*5+");
        arrayImplementationOfQueue();
        firstNegativeIntegerInWindow();
        interleave();
    }

    private static void queueReversal(Queue<Integer> q) {
        if (q.isEmpty()) {
            return;
        }
        int front = q.poll();
        queueReversal(q);
        q.add(front);
    }
    private static void queueReversalUsingStack(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();
        while (!q.isEmpty()) {
            s.push(q.poll());
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }
    private static void postFixWithQueue(String s) {
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<s.length();i++) {
            char currentValue = s.charAt(i);
            if (Character.isDigit(s.charAt(i))) {
                q.offer(s.charAt(i) - '0');
            } else {
                int op1 = q.poll();
                int op2 = q.poll();
                switch (currentValue) {
                    case '+' :
                        q.add(op1 + op2);
                        break;
                    case '-' :
                        q.add(op1 - op2);
                        break;
                    case '*' :
                        q.add(op1 * op2);
                        break;
                    case '/' :
                        q.add(op1 / op2);
                        break;
                }
            }
        }
        System.out.println(q.peek());
    }
    private static void postFixWithStack(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<s.length();i++) {
            char currentValue = s.charAt(i);
            if (Character.isDigit(s.charAt(i))) {
                stack.push(s.charAt(i) - '0');
            } else {
                int op1 = stack.pop();
                int op2 = stack.pop();
                switch (currentValue) {
                    case '+' :
                        stack.push(op1 + op2);
                        break;
                    case '-' :
                        stack.push(op1 - op2);
                        break;
                    case '*' :
                        stack.push(op1 * op2);
                        break;
                    case '/' :
                        stack.push(op1 / op2);
                        break;
                }
            }
        }
        System.out.println(stack.peek());
    }
    private static void arrayImplementationOfQueue() {
        int[] arr = new int[7];
        int front = 0;
        int rear = -1;
        int size = 0;
        enQueue(arr,rear,size,1);
        enQueue(arr,rear,size,2);
        enQueue(arr,rear,size,3);
        enQueue(arr,rear,size,4);
        deQueue(arr,front,size);
        System.out.println(Arrays.toString(arr));
    }
    private static int[] enQueue(int[] arr, int rear, int size,int data) {
        if (arr.length == size) {
            System.out.println("queue is full");
            return new int[]{rear,size};
        }
        rear = (rear + 1) % arr.length;
        arr[rear] = data;
        return new int[]{rear,size};
    }
    private static int[] deQueue(int[] arr, int front, int size) {
        if (size == 0) {
            System.out.println("queue is empty");
            return new int[]{front,size};
        }
        System.out.print("The top element of queue is : " + arr[front]);
        front = (front + 1) % arr.length;
        size--;
        return new int[]{front,size};
    }
    private static void firstNegativeIntegerInWindow() {
        int[] arr = {-12,-1,-3,2,4,-1,5,6,7};
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        int window = 3;
        int start = 0;
        int end = 0;
        while (end < arr.length) {
            // adding the index of the negative integer
            if(arr[end] < 0) {
                queue.add(end);
            }
            // if the window size is completed
            if (end-start+1 == 3) {
                // checking if queue is not empty and queue.peek() is greater than equal to start
                if (!queue.isEmpty() && queue.peek() >= start) {
                    result.add(arr[queue.peek()]);
                } else {
                    result.add(0);
                }
                start++;
                // empty the extra element from window if any.
                while (!queue.isEmpty() && queue.peek() < start) {
                    queue.poll();
                }
            }
            end++;
        }
        System.out.println(result);
    }

    private static void interleave() {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> l = new ArrayList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        Stack<Integer> s = new Stack<>();
        int halfSize  = q.size()/2;
        // 456321
        for (int i=0;i<halfSize;i++) {
            s.push(q.poll());
        }
        while (!s.isEmpty()){
            q.add(s.pop());
        }
        // 321456
        for (int i=0;i<halfSize;i++) {
            q.add(q.poll());
        }
        // 123 -> in stack 456 in queue
        for (int i=0;i<halfSize;i++) {
            s.push(q.poll());
        }

        while (!s.isEmpty()) {
            l.add(s.pop());
            l.add(q.poll());
        }
        System.out.println(l);
    }
}

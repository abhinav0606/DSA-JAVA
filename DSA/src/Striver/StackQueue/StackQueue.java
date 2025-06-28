package Striver.StackQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueue {
    public static void main(String[] args) {

    }

    // Implementation of Stack using array
    static class ArrayStack {

        public int[] arr;
        public int capacity;
        public int topIndex;

        public ArrayStack(int size) {
            this.capacity = size;
            this.arr = new int[size];
            this.topIndex = -1;
        }

        public ArrayStack() {
            this(1000);
        }

        public void push(int x) {
            if (topIndex >= capacity-1) {
                System.out.println("Stack Overflow");
                return;
            }
            topIndex++;
            this.arr[topIndex] = x;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return this.arr[topIndex--];
        }

        public int top() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return this.arr[topIndex];
        }

        public boolean isEmpty() {
            return this.topIndex == -1;
        }
    }

    // Implement of Queue using Arrays
    static class ArrayQueue {
        public int[] arr;
        public int capacity;
        public int lastIndex;
        public int firstIndex;

        public ArrayQueue(int size) {
            this.arr = new int[size];
            this.capacity = size;
            this.firstIndex = 0;
            this.lastIndex = -1;
        }
        public ArrayQueue() {
            this(1000);
        }

        public void push(int x) {
            if (lastIndex >= this.capacity - 1) {
                System.out.println("Queue OverFlow");
                return;
            }
            lastIndex++;
            this.arr[lastIndex] = x;
        }

        public int pop() {
            if (lastIndex == -1) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return this.arr[firstIndex++];
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return this.arr[firstIndex];
        }

        public boolean isEmpty() {
            return this.lastIndex == -1;
        }
    }

    // Implement Stack using Queue
    static class QueueStack {
        Queue<Integer> q = new LinkedList<>();
        public QueueStack() {

        }

        public void push(int x) {
            int s = q.size();
            q.add(x);
            for (int i=0;i<s;i++) {
                q.add(q.poll());
            }
        }

        public int pop() {
            return q.poll();
        }

        public int top() {
            return q.peek();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }
    }

    // Implement Queue using stack
    static class StackQueue1 {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        public StackQueue1() {

        }

        public void push(int x) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.add(x);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public int pop() {
            return s1.pop();
        }

        public int peek() {
            return s1.peek();
        }

        public boolean isEmpty() {
            return s1.isEmpty();
        }
    }

    static class Node {
        int val;
        Node next;
        Node(int d) {
            val = d;
            next = null;
        }
    }

    static class LinkedListStack {
        Node head;
        int size;
        public LinkedListStack() {
            head = null;
            size = 0;
        }

        public void push(int x) {
            Node element = new Node(x);
            size++;
            element.next = head;
            head = element;

        }

        public int pop() {
            if (head == null) return -1;
            int element = head.val;
            head = head.next;
            size--;
            return element;
        }

        public int top() {
            if (head == null) return -1;
            return head.val;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    static class LinkedListQueue {
        Node head;
        Node end;
        int size;
        public LinkedListQueue() {
            head = null;
            end = null;
            size = 0;
        }

        public void push(int x) {
            Node element  = new Node(x);
            if (head == null) {
                head = element;
                end = element;
                return;
            }
            end.next = element;
            end = element;
            size++;
        }

        public int pop() {
            if (head == null) return -1;
            int value = head.val;
            Node temp = head;
            head = head.next;
            temp = null;
            size--;
            return value;
        }

        public int peek() {
            if (head == null) return -1;
            return head.val;
        }

        public boolean isEmpty() {
            return size==0;
        }
    }

}

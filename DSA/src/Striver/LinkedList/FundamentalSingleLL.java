package Striver.LinkedList;

import java.util.ArrayList;
import java.util.List;


public class FundamentalSingleLL {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int data1) {
            val = data1;
            next = null;
        }
        ListNode(int data1, ListNode next1) {
            val = data1;
            next = next1;
        }
    }
    public static void main(String[] args) {

    }

    public static List<Integer> LLTraversal(ListNode head) {
        ListNode temp = head;
        List<Integer> result = new ArrayList<>();
        while (temp!=null) {
            result.add(temp.val);
            temp = temp.next;
        }
        return result;
    }
    public ListNode deleteHead(ListNode head) {
        if (head == null) return null;
        ListNode temp = head;
        head = temp.next;
        temp = null;
        return head;
    }

    public ListNode deleteTail(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }

    public ListNode deleteKthNode(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k==1) {
            head = head.next;
            return head;
        }
        ListNode temp = head;
        for (int i=0;i<k-2;i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) return null;
        temp.next = temp.next.next;
        return head;
    }

    public ListNode deleteNodeWithValueX(ListNode head, int X) {
        if (head == null) {
            return null;
        }
        if (head.val == X) {
            head = head.next;
            return head;
        }
        ListNode temp = head;
        ListNode prev = null;
        while (temp != null) {
            if (temp.val == X) break;
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) return head;
        prev.next = temp.next;
        temp = null;
        return head;
    }

    public ListNode insertAtHead(ListNode head, int X) {
        ListNode temp = new ListNode(X);
        temp.next = head;
        head = temp;
        return head;
    }
    public ListNode insertAtTail(ListNode head, int X) {
        if (head == null) {
            head = new ListNode(X);
            return head;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new ListNode(X);
        return head;
    }
    public ListNode insertAtKthPosition(ListNode head, int X, int K) {
        if (K==1) {
            ListNode temp = new ListNode(X);
            temp.next = head;
            head = temp;
            return head;
        }
        ListNode newNode = new ListNode(X);
        ListNode temp = head;
        for (int i=0;i<K-2;i++) {
            temp = temp.next;
        }
        if (temp == null) return head;
        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }
    public ListNode insertBeforeX(ListNode head, int X, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == X) {
            ListNode temp = new ListNode(val);
            temp.next = head;
            head = temp;
            return head;
        }
        ListNode temp = head;
        ListNode prev = null;
        while (temp != null) {
            if (temp.val == X) break;
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) return head;
        ListNode newNode = new ListNode(val);
        newNode.next = temp;
        prev.next = newNode;
        return head;
    }
}

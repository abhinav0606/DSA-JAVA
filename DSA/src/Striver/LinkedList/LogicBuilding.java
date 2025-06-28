package Striver.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class LogicBuilding {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
            val = 0;
            next = null;
        }

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
        ListNode n = new ListNode(8);
        n.next = new ListNode(6);
        n.next.next = new ListNode(1);
        n.next.next.next = new ListNode(7);
        n.next.next.next.next = new ListNode(1);
        n.next.next.next.next.next = new ListNode(8);
        n.next.next.next.next.next.next = new ListNode(2);
        n.next.next.next.next.next.next.next = new ListNode(1);
        n.next.next.next.next.next.next.next.next = new ListNode(6);
        n.next.next.next.next.next.next.next.next.next = new ListNode(2);
        n.next.next.next.next.next.next.next.next.next.next = new ListNode(5);
        ListNode n1 = new ListNode(9);
        n1.next = new ListNode(0);
        n1.next.next = new ListNode(2);
        n1.next.next.next = new ListNode(4);
//        ListNode node = addTwoNumbers(n,n1);
//        printList(node);
        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(2);
//        n2.next.next = new ListNode(2);
        n2.next.next = new ListNode(3);
        n2.next.next.next= new ListNode(4);
//        printList(oddEvenList(n1));
//        printList(sortList(n2));

        printList(reverseList(n2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        int carry = 0;
        ListNode temp = head;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = 0;
            if (l1 != null) {
                sum = sum + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum = sum + l2.val;
                l2 = l2.next;
            }
            temp.next = new ListNode(sum%10);
            carry = sum/10;
            temp = temp.next;
        }
        return head.next;
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode tempOdd = head;
        ListNode t2 = head.next;
        ListNode tempEven = head.next;
        while (tempEven != null && tempEven.next != null) {
            tempOdd.next = tempEven.next;
            tempOdd = tempOdd.next;
            tempEven.next = tempOdd.next;
            tempEven = tempEven.next;
        }
        tempOdd.next = t2;
        return head;
    }

    public static ListNode sortList(ListNode head) {
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val == 0) c0++;
            else if (temp.val == 1) c1++;
            else c2++;
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            if (c0 > 0) {
                c0--;
                temp.val = 0;
            } else if (c1 > 0) {
                c1--;
                temp.val = 1;
            } else {
                c2--;
                temp.val = 2;
            }
            temp = temp.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fp = head;
        ListNode sp = head;
        for (int i=0;i<n;i++) {
            fp = fp.next;
        }
        if (fp == null) {
            return head.next;
        }

        while (fp.next != null) {
            fp = fp.next;
            sp = sp.next;
        }
        sp.next = sp.next.next;
        return head;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        ListNode next = head.next;
        while (next != null) {
            current.next = prev;
            prev = current;
            current = next;
            next = current.next;
        }
        current.next = prev;
        head = current;
        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

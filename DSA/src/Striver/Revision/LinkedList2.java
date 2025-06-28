package Striver.Revision;

import java.util.List;

public class LinkedList2 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode child;

        ListNode() {
            val = 0;
            next = null;
            child = null;
        }

        ListNode(int data1) {
            val = data1;
            next = null;
            child = null;
        }

        ListNode(int data1, ListNode next1, ListNode next2) {
            val = data1;
            next = next1;
            child = next2;
        }
    }
    public ListNode flattenLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode nextList = flattenLinkedList(head.next);
        head = merge(head, nextList);
        return head;
    }
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (l1 != null || l2 != null) {
            if (l1.val <= l2.val) {
                temp.child = l1;
                l1 = l1.child;
                temp = l1;
            } else {
                temp.child = l2;
                l2 = l2.child;
                temp = l2;
            }
            temp.next = null;
        }
        if (l1 != null) {
            temp.child = l1;
        }
        if (l2 != null) {
            temp.child = l2;
        }
        if (temp.child != null) {
            temp.child.next = null;
        }
        return dummy.child;
    }
}

package Striver.LinkedList;

public class DLL {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode() {
            val = 0;
            next = null;
            prev = null;
        }

        ListNode(int data1) {
            val = data1;
            next = null;
            prev = null;
        }

        ListNode(int data1, ListNode next1, ListNode prev1) {
            val = data1;
            next = next1;
            prev = prev1;
        }
    }

    public static void main(String[] args) {

    }

    public ListNode deleteAllOccurrences(ListNode head, int target) {
        ListNode temp = head;
        while (temp != null) {
            if (temp.val == target) {
                if (temp == head) {
                    head = temp.next;
                }
                ListNode prev = temp.prev;
                ListNode next = temp.next;

                if (next != null) {
                    next.prev = prev;
                }
                if (prev != null) {
                    prev.next = next;
                }
            }
            temp = temp.next;
        }
        return head;
    }

    public ListNode removeDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            ListNode prev = temp.prev;
            if (next != null) {
                if (temp.val == next.val) {
                    if (temp == head) {
                        head = temp.next;
                    }
                    if (prev != null) {
                        prev.next = next;
                    }
                    next.prev = prev;
                }
            }
            temp = temp.next;
        }
        return head;
    }
}

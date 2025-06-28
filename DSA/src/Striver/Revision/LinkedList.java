package Striver.Revision;

import Striver.BinarySearchTree.FAQs;

import java.awt.*;

public class LinkedList {
    static class ListNodeDll {
        int val;
        ListNodeDll next;
        ListNodeDll prev;

        ListNodeDll() {
            val = 0;
            next = null;
            prev = null;
        }

        ListNodeDll(int data1) {
            val = data1;
            next = null;
            prev = null;
        }

        ListNodeDll(int data1, ListNodeDll next1, ListNodeDll prev1) {
            val = data1;
            next = next1;
            prev = prev1;
        }
    }

    class ListNode {
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

    public static ListNodeDll arrayToLinkedList(int [] nums) {
        if (nums.length == 0) return null;
        ListNodeDll temp = new ListNodeDll(nums[0]);
        ListNodeDll prev = temp;
        for (int i=1;i<nums.length;i++) {
            ListNodeDll currentNode = new ListNodeDll(nums[i]);
            prev.next = currentNode;
            currentNode.prev = prev;
            prev = currentNode;
        }
        prev.next = null;
        return temp;
    }

    public ListNode deleteNodeWithValueX(ListNode head, int X) {
        if (head == null) return null;
        if (head.val == X) {
            head = head.next;
            return head;
        }
        ListNode temp = head;
        ListNode prev = null;
        while (temp != null) {
            if (temp.val == X) {
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) return head;
        prev.next = temp.next;
        temp = null;
        return head;
    }

    public ListNodeDll deleteTail(ListNodeDll head) {
        if (head == null) return null;
        ListNodeDll temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.prev.next = null;
        temp.prev = null;
        return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        int carry = 0;
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
            sum = sum + carry;
            temp.next = new ListNode(sum%10);
            carry = sum/10;
            temp = temp.next;
        }
        return head.next;
    }
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode tOdd = head;
        ListNode tEven = head.next;
        ListNode temp = head.next;
        while (tOdd != null && tEven.next != null) {
            tOdd.next = tEven.next;
            tOdd = tOdd.next;
            tEven.next = tOdd.next;
            tEven = tEven.next;
        }
        tOdd.next = temp;
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fp = head;
        ListNode sp = head;
        for (int i=0;i<n;i++) {
            fp = fp.next;
        }
        if (fp == null) return head.next;
        while (fp.next != null) {
            fp = fp.next;
            sp = sp.next;
        }
        sp.next = sp.next.next;
        return head;
    }

    // 3 pointer
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
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

    public ListNode addOne(ListNode head) {
        int carry = carryHelper(head);
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        return head;
    }

    private int carryHelper(ListNode node) {
        if (node == null) return 1;
        int carry = carryHelper(node.next);
        node.val = node.val + carry;
        if (node.val < 10) {
            return 0;
        }
        node.val = 0;
        return 1;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp1 = reverseList(slow.next);
        ListNode temp2 = head;
        while (temp1 != null) {
            if (temp1.val != temp2.val) return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return true;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode d1 = headA;
        ListNode d2 = headB;
        while (d1 != d2) {
            d1 = d1.next;
            d2 = d2.next;
            if (d1 == d2) return d1;
            if (d1 == null) d1 = headB;
            if (d2 == null) d2 = headA;
        }
        return d1;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fp = head;
        ListNode sp = head;
        while (fp != null && fp.next != null) {
            fp = fp.next.next;
            sp = sp.next;
            if (fp == sp) return true;
        }
        return false;
    }

    public ListNode findStartingPoint(ListNode head) {
        ListNode fp = head;
        ListNode sp = head;
        boolean isCycle = false;
        while (fp != null && fp.next != null) {
            fp = fp.next.next;
            sp = sp.next;
            if (fp == sp) {
                isCycle = true;
                break;
            };
        }
        if (isCycle) {
            sp = head;
            while (sp != fp) {
                sp = sp.next;
                fp = fp.next;
            }
            return sp;
        }
        return null;
    }
    public ListNode middleOfLinkedList(ListNode head) {
        ListNode sp = head;
        ListNode fp = head;
        while (fp.next != null && fp.next.next != null) {
            fp = fp.next.next;
            sp = sp.next;
        }
        if (fp.next == null) return sp;
        else return sp.next;
    }

    public int findLengthOfLoop(ListNode head) {
        ListNode fp = head;
        ListNode sp = head;
        boolean isCycle = false;
        while (fp != null && fp.next != null) {
            fp = fp.next.next;
            sp = sp.next;
            if (fp == sp) {
                isCycle = true;
                break;
            };
        }
        if (isCycle) {
            int counter = 1;
            sp = sp.next;
            while (sp != fp) {
                sp = sp.next;
                counter++;
            }
            return counter;
        }
        return 0;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prev = null;
        ListNode next = null;
        ListNode kNode = null;
        while (temp != null) {
            kNode = kthNode(temp, k);
            if (kNode == null) {
                prev.next = temp;
                break;
            }
            next = kNode.next;
            kNode.next = null;
            ListNode temp1 = temp;
            reverseList(temp);
            if (temp1 == head) {
                head = kNode;
            } else {
                prev.next = kNode;
            }
            prev = temp1;
            temp = next;
        }
        return head;
    }
    private ListNode kthNode(ListNode head, int k) {
        ListNode temp = head;
        int counter = 1;
        while (temp != null) {
            temp = temp.next;
            counter++;
            if (counter == k) return temp;
        }
        return null;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int length = 1;
        ListNode oldTail = head;
        ListNode oldHead = head;
        while (oldTail.next != null) {
            length++;
            oldTail = oldTail.next;
        }
        k = k % length;
        if (k > 0) {
            ListNode newTail = head;
            for (int i=0;i<=length-k-2;i++) {
                newTail = newTail.next;
            }
            ListNode newHead = newTail.next;
            oldTail.next = oldHead;
            newTail.next = null;
            head = newHead;
            return head;
        } else {
            return head;
        }
     }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
        }
        while (list1 != null) {
            temp.next = list1;
            list1 = list1.next;
        }
        while (list2 != null) {
            temp.next = list2;
            list2 = list2.next;
        }
        return dummy.next;
    }
}

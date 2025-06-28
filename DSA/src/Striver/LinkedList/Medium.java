package Striver.LinkedList;

public class Medium {
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

    }

    // One approach is to reverse add the number like we have added using carry method
    // then reverse it back that will also take O(n)
    // Another approach is to use back tracking method to figure out the carry
    // Create a recursive method
    // Create a carry helper
    // if node == null  return 1
    // add it to the node.data + 1
    // if node.data < 10 return the carry as 0 else 1 and make the node.data = 0 as 9+1 will be max 10 so we have to keep 0 and return 1
    // like this we need to do till null
    // now for the head if you felt that we need to add another list
    // at the head if carry is 1 then create another node with carry data and make it head
    public ListNode addOne(ListNode head) {
        int carry = carryHelper(head);
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        return head;
    }

    private static int carryHelper(ListNode node) {
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
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = reverseList(slow.next);
        ListNode temp1 = head;
        ListNode temp2 = newHead;
        while (temp2 != null) {
            if (temp1.val != temp2.val) return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return true;
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

    // Taking two pointers and we will check if they both meet each other
    // If one pointer is reaching null just pointing that out to other ll head and vice versa
    // Once they match return the intersection node
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

    // Using same approach taking slow and fast and checking if they are meeting or not
    // once they have met we can reset slow to head and move both one by one
    public ListNode findStartingPoint(ListNode head) {
        ListNode fp = head;
        ListNode sp = head;
        boolean cycle = false;
        while (fp != null && fp.next != null) {
            fp = fp.next.next;
            sp = sp.next;
            if (fp == sp) {
                cycle = true;
                break;
            }
        }
        if (cycle) {
            sp = head;
            while (fp != sp) {
                fp = fp.next;
                sp = sp.next;
            }
            return sp;
        }
        return null;
    }

    public ListNode middleOfLinkedList(ListNode head) {
        ListNode fp = head;
        ListNode sp = head;
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
        boolean cycle = false;
        while (fp != null && fp.next != null) {
            fp = fp.next.next;
            sp = sp.next;
            if (fp == sp) {
                cycle = true;
                break;
            }
        }
        if (cycle) {
            int counter = 1;
            sp = sp.next;
            while (fp != sp) {
                sp = sp.next;
                counter++;
            }
            return counter;
        }
        return 0;
    }

    public ListNode deleteMiddle(ListNode head) {
        ListNode fp = head;
        ListNode sp = head;
        ListNode prev = sp;
        while (fp.next != null && fp.next.next != null) {
            fp = fp.next.next;
            prev = sp;
            sp = sp.next;
        }
        if (fp.next == null) {
            prev.next = sp.next;
        } else {
            sp.next = sp.next.next;
        }
        return head;
    }
}

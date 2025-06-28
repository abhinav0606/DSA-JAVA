package Striver.LinkedList;

public class Hard {

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

    public static class ListNode2 {
        int val;
        ListNode2 next;
        ListNode2 child;

        ListNode2() {
            val = 0;
            next = null;
            child = null;
        }

        ListNode2(int data1) {
            val = data1;
            next = null;
            child = null;
        }

        ListNode2(int data1, ListNode2 next1, ListNode2 next2) {
            val = data1;
            next = next1;
            child = next2;
        }
    }

    public static void main(String[] args) {

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prev = null;
        ListNode nextNode = null;
        ListNode kNode = null;
        while (temp != null) {
            kNode = findKthNode(temp,k);
            // Breaking conditions
            if (kNode == null) {
                if (prev != null) prev.next = temp;
                break;
            }
            nextNode = kNode.next;
            kNode.next = null;
            reverseLL(temp);
            if (temp == head) {
                head = kNode;
            } else {
                prev.next = kNode;
            }
            prev = temp;
            temp = nextNode;
        }
        return head;
    }
    private ListNode findKthNode(ListNode temp,int k) {
        int counter = 1;
        while (temp != null) {
            temp = temp.next;
            counter++;
            if (counter == k) return temp;
        }
        return null;
    }
    private static void reverseLL(ListNode head) {
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
    }

    public ListNode rotateRight(ListNode head, int k) {
        int length = 1;
        ListNode oldTail = head;
        ListNode oldHead = head;
        while (oldTail.next != null) {
            length++;
            oldTail = oldTail.next;
        }
        k = k%length;
        if (k > 0) {
            ListNode newTail = head;
            for (int i = 0; i <= length - k - 2; i++) {
                newTail = newTail.next;
            }
            ListNode newHead = newTail.next;
            oldTail.next = oldHead;
            newTail.next = null;
            head = newHead;
            return head;
        } else return head;
    }



    // In this question we will use backtracking
    // we can use same function to backtrack head.next head.next and base will be if head == null or head.next == null return head
    // then after that using a merger merge both head and head.next
    // in the merger it will be the same code as merging two sorted linked list as child is already sorted
    // and instead of .next we will use .child
    public ListNode2 flattenLinkedList(ListNode2 head) {
        if (head == null || head.next == null) return head;
        ListNode2 mergedHead = flattenLinkedList(head.next);
        head = merge(head,mergedHead);
        return head;
    }
    private ListNode2 merge(ListNode2 list1, ListNode2 list2) {
        ListNode2 dummy = new ListNode2(0);
        ListNode2 temp = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.child = list1;
                temp = list1;
                list1 = list1.child;
            } else {
                temp.child = list2;
                temp = list2;
                list2 = list2.child;
            }
            temp.next = null;
        }
        if (list1 != null) {
            temp.child = list1;
        } else {
            temp.child = list2;
        }
        if (temp.child != null) {
            temp.child.next = null;
        }
        return dummy.child;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode middle = middleOfLinkedList(head);
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;
        left = sortList(left);
        right = sortList(right);
        head = mergeTwoLists(left,right);
        return head;
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp = list1;
                list1 = list1.next;
            } else {
                temp = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 != null) {
            temp.next = list1;
        } else {
            temp.next = list2;
        }
        return dummy.next;
    }
}

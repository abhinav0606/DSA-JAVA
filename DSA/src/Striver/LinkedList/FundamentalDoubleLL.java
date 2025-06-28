package Striver.LinkedList;

public class FundamentalDoubleLL {
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
    public ListNode arrayToLinkedList(int [] nums) {
        if (nums.length == 0) return null;
        ListNode newNode = new ListNode(nums[0]);
        ListNode prevNode = newNode;
        for (int i=1;i<nums.length;i++) {
            ListNode temp = new ListNode(nums[i]);
            prevNode.next = temp;
            temp.prev = prevNode;
            prevNode = temp;
        }
        prevNode.next = null;
        return newNode;
    }

    public ListNode deleteHead(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return null;
        ListNode temp = head;
        temp = temp.next;
        temp.prev = null;
        head = temp;
        return head;
    }
    public ListNode deleteTail(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return null;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.prev.next = null;
        temp.prev = null;
        return head;
    }

    public ListNode deleteKthElement(ListNode head, int k) {
        if (head == null) return null;
        int nodeCount = 0;
        ListNode temp = head;
        while (temp != null) {
            nodeCount++;
            if (nodeCount == k) break;
            temp = temp.next;
        }
        if (temp.prev == null && temp.next == null) return null;
        if (temp.prev == null) {
            temp = temp.next;
            temp.prev = null;
            head = temp;
            return head;
        }
        if (temp.next == null) {
            temp.prev.next = null;
            temp.prev = null;
            return head;
        }
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        temp.next = null;
        temp.prev = null;
        return head;
    }
    public void deleteGivenNode(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        if (prev == null && next == null) node = null;
        if (prev == null) {
            node.next = null;
            next.prev = null;
            node = next;
            return;
        }
        if (next == null) {
            prev.next = null;
            node.prev = null;
            return;
        }

        prev.next = next;
        next.prev = prev;
        node.next = null;
        node.prev = null;
    }

    public ListNode insertBeforeHead(ListNode head, int X) {
        ListNode node = new ListNode(X);
        if (head == null) {
            head = node;
            return head;
        }
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
        return head;
    }
    public ListNode insertBeforeTail(ListNode head, int X) {
        ListNode node = new ListNode(X);
        if (head == null) {
            head = node;
            return head;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        if (temp.prev != null) {
            node.next = temp;
            node.prev = temp.prev;
            temp.prev.next = node;
            temp.prev = node;
        } else {
            node.prev = null;
            node.next = temp;
            temp.prev = node;
            head = node;
        }
        return head;
    }

    public void insertBeforeGivenNode(ListNode node, int X) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        ListNode newNode = new ListNode(X);
        if (prev == null && next == null) {
            newNode.next = node;
            node.prev = newNode;
            node = newNode;
            return;
        } else if (prev == null) {
            newNode.next = node;
            node.prev = newNode;
            node = newNode;
            return;
        } else if (next == null) {
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
        } else {
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
        }
    }
    public ListNode insertBeforeKthPosition(ListNode head, int X, int K) {
        if (head == null) return null;
        int kCount = 0;
        ListNode temp = head;
        while (temp != null) {
            if (kCount == K) break;
            kCount++;
            temp = temp.next;
        }
        if (temp == null) return head;
        ListNode newNode = new ListNode(X);
        temp.prev.next = newNode;
        newNode.next = temp;
        newNode.prev = temp.prev;
        temp.prev = newNode;
        return head;
    }
}

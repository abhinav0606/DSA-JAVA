package LinkedList;

public class LinkedList2 {
    public static class Node {
        int data;
        Node next;
        Node (int data) {
            this.data = data;
            this.next = null;
        }
    }
    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        node.next.next.next.next.next = new Node(6);
        oddEvenList(node);
        System.out.println("--------------");
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        node1.next.next.next = new Node(4);
        node1.next.next.next.next = new Node(5);
        node1.next.next.next.next.next = new Node(6);
        node1.next.next.next.next.next.next = node1.next;
        System.out.println(middleOfLinkedList(node));
        deleteTheMiddle(node);
        traverseLinkedList(node);
        System.out.println(detectLoop(node1));
        startingPointOfLoop(node1);
        removeLoop(node1);
        intersectionOfLinkedLists();
        intersectionWithOutLength();
        reverseListIterative();
        System.out.println("------------");
        Node node3 = new Node(1);
        node3.next = new Node(2);
        node3.next.next = new Node(3);
        node3.next.next.next = new Node(4);
        node3.next.next.next.next = new Node(5);
        traverseLinkedList(reverseListRecursion(node3));
        System.out.println("--------------**********");
        subReverseList();
        System.out.println("---------------");
        reOrderList();
        System.out.println("--------------------*********");
        rotateList();
    }
    private static int middleOfLinkedList(Node node) {
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }
    private static void deleteTheMiddle(Node node) {
        Node fast = node;
        Node slow = node;
        Node nodeBeforeSlow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            nodeBeforeSlow = slow;
            slow = slow.next;
        }
        nodeBeforeSlow.next = slow.next;
        slow.next = null;
    }
    private static void traverseLinkedList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
    }
    private static boolean detectLoop(Node node) {
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    private static void startingPointOfLoop(Node node) {
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        Node start = node;
        Node meetingPoint = fast;
        while (start != meetingPoint) {
            start = start.next;
            meetingPoint = meetingPoint.next;
        }
        System.out.println(meetingPoint.data);
    }
    private static void removeLoop(Node node) {
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        Node start = node;

        while (start.next != fast.next) {
            start = start.next;
            fast = fast.next;
        }
        fast.next = null;
        traverseLinkedList(node);
    }
    private static void oddEvenList(Node node) {
        Node evenStart = node.next;
        Node odd = node;
        Node even = node.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenStart;
        traverseLinkedList(node);
    }
    private static void intersectionOfLinkedLists() {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        node1.next.next.next = new Node(4);
        node1.next.next.next.next = new Node(5);

        Node node2 = new Node(8);
        node2.next = new Node(6);
        node2.next.next = node1.next.next.next;

        int l1 = findLength(node1);
        int l2 = findLength(node2);
        int difference = Math.abs(l1-l2);
        if (l1>l2) {
            while (difference!=0) {
                node1 = node1.next;
                difference--;
            }
        } else {
            while (difference!=0) {
                node2 = node2.next;
                difference--;
            }
        }
        while (node1 != node2) {
            node2 = node2.next;
            node1 = node1.next;
        }
        System.out.println("---------");
        System.out.println(node2.data);
    }
    private static int findLength(Node node) {
        Node temp = node;
        int count = 0;
        while (temp!=null) {
            count ++;
            temp = temp.next;
        }
        return count;
    }
    private static void intersectionWithOutLength() {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        node1.next.next.next = new Node(4);
        node1.next.next.next.next = new Node(5);

        Node node2 = new Node(8);
        node2.next = new Node(6);
        node2.next.next = node1.next.next.next;

        if (node1 == null || node2 == null) {
            System.out.println("-----");
        }
        Node temp1 = node1;
        Node temp2 = node2;
        while (temp1 != temp2) {
            temp1 = (temp1 == null) ? node2 : temp1.next;
            temp2 = (temp2 == null) ? node1 : temp2.next;
        }
        System.out.println("---------");
        System.out.println(temp1.data);
    }
    private static void reverseListIterative() {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        node1.next.next.next = new Node(4);
        node1.next.next.next.next = new Node(5);

        Node cNode = node1;
        Node prev = null;
        while (cNode != null) {
            Node nextNode = cNode.next;
            cNode.next = prev;
            prev = cNode;
            cNode = nextNode;
        }
        System.out.println("-------");
        traverseLinkedList(prev);
    }
    private static Node reverseListRecursion(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node r = reverseListRecursion(node.next);
        node.next.next = node;
        node.next = null;
        return r;
    }

    private static void subReverseList() {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        int start = 2;
        int end = 4;
        Node startNode = node;
        Node endNode = node;
        Node nodeBeforeStart = null;
        Node nodeAfterEnd = node;

        for (int i=0;i<start-1;i++) {
            nodeBeforeStart = startNode;
            startNode = startNode.next;
        }
        Node startNodeCopy = startNode;
        for (int i=0;i<end-1;i++) {
            endNode = endNode.next;
        }
        nodeAfterEnd = endNode.next;

        Node cNode = startNode;
        Node prev = null;
        while (cNode != nodeAfterEnd) {
            Node nextNode = cNode.next;
            cNode.next = prev;
            prev = cNode;
            cNode = nextNode;
        }
        if (nodeBeforeStart != null) {
            nodeBeforeStart.next = prev;
        } else {
            node = prev;
        }
        startNodeCopy.next = nodeAfterEnd;
        traverseLinkedList(node);
    }

    private static void reOrderList() {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        // Find middle with slow and fast and reverse from slow
        Node fast = node;
        Node slow = node;
        while (fast!= null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node cNode = slow;
        Node prev = null;
        while (cNode != null) {
            Node nextNode = cNode.next;
            cNode.next = prev;
            prev = cNode;
            cNode = nextNode;
        }
        Node firstNode = node;
        Node secondNode = prev;
        while (secondNode.next != null) {
            Node temp1 = firstNode.next;
            Node temp2= secondNode.next;
            firstNode.next = secondNode;
            secondNode.next = temp1;
            firstNode = temp1;
            secondNode = temp2;
        }
        traverseLinkedList(node);
    }

    private static void rotateList() {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);

        int len  = findLength(node);
        int k = 1;
        int difference = k%len;
        Node fast = node;
        Node slow = node;
        for (int i=0;i<difference;i++) {
            fast = fast.next;
        }
        while (fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }
        Node ans = slow.next;
        slow.next = null;
        fast.next = node;
        traverseLinkedList(ans);
    }
}

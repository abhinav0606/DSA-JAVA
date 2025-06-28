package LinkedList;

public class LinkedList1 {
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
        traverseLinkedList(node);
        System.out.println("----------");
        insertAtStart(node);
        System.out.println("----------");
        insertAtPosition(node);
        System.out.println("----------");
        insertAtLast(node);
        System.out.println("-----------");
        deleteNodeAtStart(node);
        System.out.println("------------");
        deleteNodeAtPosition(node);
        System.out.println("------------");
        deleteNodeAtLast(node);
    }
    private static void traverseLinkedList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
    }
    private static void insertAtStart(Node node) {
        Node newNode = new Node(99);
        newNode.next = node;
        traverseLinkedList(newNode);
    }
    private static void insertAtPosition(Node node) {
        int position = 3;
        int data = 100;
        Node newNode = new Node(data);
        Node temp = node;
        for (int i=0;i<position-2;i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        traverseLinkedList(node);
    }
    private static void insertAtLast(Node node) {
        Node temp = node;
        Node newNode = new Node(101);
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = null;
        traverseLinkedList(node);
    }
    private static void deleteNodeAtStart(Node node) {
        node = node.next;
        traverseLinkedList(node);
    }
    private static void deleteNodeAtPosition(Node node) {
        int position = 3;
        Node temp = node;
        for (int i=0;i<position-2;i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        traverseLinkedList(node);
    }
    private static void deleteNodeAtLast(Node node) {
        Node temp = node;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        traverseLinkedList(node);
    }
}

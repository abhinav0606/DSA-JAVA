package LinkedList;

public class Hackerrank {
    public static class Node {
        int data;
        Node next;
        Node (int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(3);
        node.next = new Node(2);
        node.next.next = new Node(4);

        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(2);

        traverseLinkedList(removeDuplicates(node));
        System.out.println("-------------------");
        addNumbers(node,node1);
    }

    public static Node removeDuplicates(Node llist) {
        Node temp1 = llist;
        Node temp2 = llist.next;
        while (temp1.next != null) {
            if (temp2.data == temp1.data) {
                temp1.next = temp2.next;
                temp2 = temp2.next;
            } else {
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        return llist;
    }
    private static void traverseLinkedList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
    }

    public static void addNumbers(Node start_one, Node start_two) {
        int sum = start_one.data + start_two.data;
        int tensPlace = sum % 10;
        int carry = sum / 10;
        Node newNode = new Node(tensPlace);
        Node temp  = newNode;
        start_one = start_one.next;
        start_two = start_two.next;
        while (start_one != null || start_two != null) {
            sum = (start_one == null ? 0 : start_one.data) + (start_two == null ? 0 : start_two.data) + carry;
            tensPlace = sum % 10;
            carry = sum / 10;
            temp.next = new Node(tensPlace);
            temp = temp.next;
            start_one = start_one == null ? null : start_one.next;
            start_two = start_two == null ? null : start_two.next;
        }
        traverseLinkedList(newNode);
    }
}

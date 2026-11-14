
import java.util.Scanner;

public class A1DeliveryRouteLoopDetector {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static String detectLoop(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int length = 1;
                Node current = slow.next;

                while (current != slow) {
                    length++;
                    current = current.next;
                }

                Node first = head;
                while (first != slow) {
                    first = first.next;
                    slow = slow.next;
                }

                return "Loop: yes, starts at stop "
                        + first.data + ", length " + length;
            }
        }

        return "Loop: no";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Node head = null;
        Node tail = null;
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(sc.nextInt());

            if (head == null) {
                head = nodes[i];
            } else {
                tail.next = nodes[i];
            }

            tail = nodes[i];
        }

        int pos = sc.nextInt();

        if (pos >= 0) {
            tail.next = nodes[pos];
        }

        System.out.println(detectLoop(head));
        sc.close();
    }
}

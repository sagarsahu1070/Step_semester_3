
import java.util.Scanner;

public class A2ExamScriptBatches {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node reverseInGroups(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node groupPrevious = dummy;

        while (true) {
            Node kth = groupPrevious;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            if (kth == null) {
                break;
            }

            Node groupNext = kth.next;
            Node previous = groupNext;
            Node current = groupPrevious.next;

            while (current != groupNext) {
                Node next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            Node oldGroupHead = groupPrevious.next;
            groupPrevious.next = kth;
            groupPrevious = oldGroupHead;
        }

        return dummy.next;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {
            Node node = new Node(sc.nextInt());

            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }

            tail = node;
        }

        int k = sc.nextInt();
        head = reverseInGroups(head, k);

        printList(head);
        sc.close();
    }
}

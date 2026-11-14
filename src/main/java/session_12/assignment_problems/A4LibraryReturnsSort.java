
import java.util.Scanner;

public class A4LibraryReturnsSort {
    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
        }
    }

    public static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node right = slow.next;
        slow.next = null;

        Node leftSorted = sortByDueDay(head);
        Node rightSorted = sortByDueDay(right);

        return merge(leftSorted, rightSorted);
    }

    private static Node merge(Node left, Node right) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (left != null && right != null) {
            if (left.dueDay <= right.dueDay) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }

            tail = tail.next;
        }

        tail.next = left != null ? left : right;
        return dummy.next;
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.title + ":" + head.dueDay + " -> ");
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
            String entry = sc.next();
            int separator = entry.lastIndexOf(':');
            String title = entry.substring(0, separator);
            int dueDay = Integer.parseInt(entry.substring(separator + 1));

            Node node = new Node(title, dueDay);

            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }

            tail = node;
        }

        head = sortByDueDay(head);
        printList(head);
        sc.close();
    }
}

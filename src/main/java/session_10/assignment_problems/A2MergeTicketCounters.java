public class A2MergeTicketCounters {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        tail.next = (a != null) ? a : b;

        return dummy.next;
    }

    static Node createList(int[] values) {
        Node dummy = new Node(0);
        Node tail = dummy;

        for (int value : values) {
            tail.next = new Node(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null)
                System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node a = createList(new int[]{3, 8, 15});
        Node b = createList(new int[]{5, 8, 12, 20});

        Node result = mergeSorted(a, b);
        printList(result);

        Node empty = null;
        Node single = createList(new int[]{4});
        printList(mergeSorted(empty, single));
    }
}
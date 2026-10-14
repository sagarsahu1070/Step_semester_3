public class A10PalindromeTrainCode {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;

        Node slow = head;
        Node fast = head;

        // Find the end of the first half.
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half.
        Node secondHalf = reverse(slow.next);
        Node first = head;
        Node second = secondHalf;
        boolean palindrome = true;

        while (second != null) {
            if (first.data != second.data) {
                palindrome = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Restore the original list.
        slow.next = reverse(secondHalf);

        return palindrome;
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
        Node a = createList(new int[]{1, 2, 3, 2, 1});
        System.out.println(isPalindrome(a));
        printList(a);

        Node b = createList(new int[]{1, 2, 3, 2});
        System.out.println(isPalindrome(b));
        printList(b);

        Node c = createList(new int[]{1, 2, 2, 1});
        System.out.println(isPalindrome(c));
        printList(c);
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class A3RecentPagesCache {
    static class Node {
        int key;
        String page;
        Node prev;
        Node next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    static class RecentCache {
        private final int capacity;
        private final Map<Integer, Node> map = new HashMap<>();
        private final Node head = new Node(0, "");
        private final Node tail = new Node(0, "");

        RecentCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public String get(int key) {
            Node node = map.get(key);

            if (node == null) {
                return null;
            }

            remove(node);
            addFirst(node);
            return node.page;
        }

        public Integer put(int key, String page) {
            Node existing = map.get(key);

            if (existing != null) {
                existing.page = page;
                remove(existing);
                addFirst(existing);
                return null;
            }

            Integer evicted = null;

            if (map.size() == capacity) {
                Node leastRecent = tail.prev;
                evicted = leastRecent.key;
                remove(leastRecent);
                map.remove(leastRecent.key);
            }

            Node node = new Node(key, page);
            addFirst(node);
            map.put(key, node);

            return evicted;
        }

        public String snapshot() {
            StringBuilder result = new StringBuilder();
            Node current = head.next;

            while (current != tail) {
                if (result.length() > 0) {
                    result.append(", ");
                }

                result.append(current.key).append("=")
                        .append(current.page);
                current = current.next;
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacity = sc.nextInt();
        int operations = sc.nextInt();

        RecentCache cache = new RecentCache(capacity);

        for (int i = 0; i < operations; i++) {
            String command = sc.next();

            if (command.equalsIgnoreCase("put")) {
                int key = sc.nextInt();
                String page = sc.next();

                boolean updating = cache.getWithoutChanging(key) != null;
                Integer evicted = cache.put(key, page);

                if (updating) {
                    System.out.println("put(" + key + "," + page + ") updated");
                } else if (evicted != null) {
                    System.out.println("put(" + key + "," + page
                            + ") evicted " + evicted);
                } else {
                    System.out.println("put(" + key + "," + page + ")");
                }
            } else if (command.equalsIgnoreCase("get")) {
                int key = sc.nextInt();
                String page = cache.get(key);

                System.out.println("get(" + key + ") -> "
                        + (page == null ? "-1" : page));
            }
        }

        System.out.println("Cache (most recent first): "
                + cache.snapshot());
        sc.close();
    }
}

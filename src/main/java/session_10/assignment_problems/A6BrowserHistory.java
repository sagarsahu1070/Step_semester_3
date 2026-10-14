public class A6BrowserHistory {

    static class Node {
        String url;
        Node prev, next;

        Node(String url) {
            this.url = url;
        }
    }

    static class BrowserHistory {
        private Node current;

        BrowserHistory(String home) {
            current = new Node(home);
        }

        String visit(String url) {
            // Discard all forward history.
            current.next = null;

            Node newPage = new Node(url);
            newPage.prev = current;
            current.next = newPage;
            current = newPage;

            return current.url;
        }

        String back(int steps) {
            while (steps > 0 && current.prev != null) {
                current = current.prev;
                steps--;
            }
            return current.url;
        }

        String forward(int steps) {
            while (steps > 0 && current.next != null) {
                current = current.next;
                steps--;
            }
            return current.url;
        }
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}
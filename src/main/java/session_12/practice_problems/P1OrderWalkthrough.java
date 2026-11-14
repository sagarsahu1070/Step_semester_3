
import java.util.Scanner;

public class P1OrderWalkthrough {
    static class Order {
        static class LineItem {
            String productName;
            int quantity;
            double unitPrice;

            LineItem(String productName, int quantity, double unitPrice) {
                this.productName = productName;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
            }

            double total() {
                return quantity * unitPrice;
            }
        }

        private final LineItem[] items;
        private int count;

        Order(int capacity) {
            items = new LineItem[capacity];
        }

        void addItem(LineItem item) {
            items[count++] = item;
        }

        class Cursor {
            private int index = 0;

            boolean hasNext() {
                return index < count;
            }

            LineItem next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more items");
                }
                return items[index++];
            }

            void reset() {
                index = 0;
            }
        }

        Cursor createCursor() {
            return new Cursor();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Order order = new Order(n);

        for (int i = 0; i < n; i++) {
            String product = sc.next();
            int quantity = sc.nextInt();
            double price = sc.nextDouble();

            order.addItem(new Order.LineItem(product, quantity, price));
        }

        Order.Cursor cursor = order.createCursor();
        double grandTotal = 0;

        while (cursor.hasNext()) {
            Order.LineItem item = cursor.next();
            System.out.println(item.productName + " "
                    + format(item.total()));
            grandTotal += item.total();
        }

        System.out.println("Total " + format(grandTotal));
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
        sc.close();
    }

    private static String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}

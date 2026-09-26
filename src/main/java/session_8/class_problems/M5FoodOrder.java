import java.util.*;

interface IPaymentMethod {

    boolean pay(double amount);

    String getName();
}

class CreditCardPayment implements IPaymentMethod {

    public boolean pay(double amount) {

        System.out.println(
            "Payment via Credit Card successful."
        );

        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {

    public boolean pay(double amount) {

        System.out.println(
            "Payment via Digital Wallet failed."
        );

        return false;
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class CashOnDelivery implements IPaymentMethod {

    public boolean pay(double amount) {

        System.out.println(
            "Payment via Cash on Delivery selected."
        );

        return true;
    }

    public String getName() {
        return "Cash on Delivery";
    }
}

class FoodItem {

    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class LineItem {

    private FoodItem item;
    private int quantity;

    public LineItem(
        FoodItem item,
        int quantity
    ) {

        this.item = item;
        this.quantity = quantity;
    }

    public double getTotal() {
        return item.getPrice() * quantity;
    }

    public String getItemName() {
        return item.getName();
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }
}

class Order {

    private int orderId;

    private List<LineItem> items =
        new ArrayList<>();

    private String status =
        "Pending Payment";

    public Order(int orderId) {

        this.orderId = orderId;

        System.out.println("Order created.");
    }

    public void addItem(
        FoodItem item,
        int quantity
    ) {

        items.add(
            new LineItem(item, quantity)
        );

        System.out.println(
            "Added " +
            item.getName() +
            " (Qty " +
            quantity +
            ")"
        );
    }

    public double calculateTotal() {

        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void placeOrder(
        IPaymentMethod paymentMethod
    ) {

        if (items.isEmpty()) {

            System.out.println(
                "Cannot place order: " +
                "Order must contain at least one item."
            );

            return;
        }

        System.out.println(
            "Order placed successfully."
        );

        boolean success =
            paymentMethod.pay(
                calculateTotal()
            );

        if (success) {

            status = "Paid";

            System.out.println(
                "Order status: Paid."
            );

            System.out.println(
                "Notification: Order #" +
                orderId +
                " placed and paid."
            );

        } else {

            System.out.println(
                "Order status: Pending Payment."
            );

            System.out.println(
                "Notification: Order #" +
                orderId +
                " placed, awaiting payment."
            );
        }
    }
}

public class M5FoodOrder {

    public static void main(String[] args) {

        FoodItem pizza =
            new FoodItem("Pizza", 200);

        FoodItem soda =
            new FoodItem("Soda", 50);

        FoodItem burger =
            new FoodItem("Burger", 150);

        Order order1 =
            new Order(123);

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        Order emptyOrder =
            new Order(125);

        emptyOrder.placeOrder(
            new CreditCardPayment()
        );

        order1.placeOrder(
            new CreditCardPayment()
        );

        Order order2 =
            new Order(124);

        order2.addItem(burger, 1);

        order2.placeOrder(
            new DigitalWalletPayment()
        );
    }
}
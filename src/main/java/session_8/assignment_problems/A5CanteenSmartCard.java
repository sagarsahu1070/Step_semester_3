import java.util.*;

interface PricingPlan {
    double getPrice(double originalPrice);
    String getName();
}

class DayScholarPlan implements PricingPlan {

    public double getPrice(double originalPrice) {
        return originalPrice;
    }

    public String getName() {
        return "Day Scholar";
    }
}

class HostellerPlan implements PricingPlan {

    public double getPrice(double originalPrice) {
        return originalPrice * 0.90;
    }

    public String getName() {
        return "Hosteller";
    }
}

class StaffPlan implements PricingPlan {

    public double getPrice(double originalPrice) {
        return originalPrice * 0.80;
    }

    public String getName() {
        return "Staff";
    }
}

class Transaction {

    private double amount;
    private String description;

    public Transaction(
        double amount,
        String description
    ) {

        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

class FoodItem {

    private String name;
    private double price;

    public FoodItem(
        String name,
        double price
    ) {

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

class SmartCard {

    private String cardNumber;
    private PricingPlan plan;
    private double balance = 0;

    private boolean blocked = false;

    private List<Transaction> transactions =
        new ArrayList<>();

    private Set<String> refundedPurchases =
        new HashSet<>();

    public SmartCard(
        String cardNumber,
        PricingPlan plan
    ) {

        this.cardNumber = cardNumber;
        this.plan = plan;
    }

    public void topUp(double amount) {

        if (blocked) {
            System.out.println(
                "Top-up rejected: Card is blocked."
            );
            return;
        }

        if (amount < 100) {
            System.out.println(
                "Top-up failed: Minimum top-up is ₹100."
            );
            return;
        }

        if (balance + amount > 5000) {
            System.out.println(
                "Top-up failed: Maximum balance is ₹5000."
            );
            return;
        }

        balance += amount;

        transactions.add(
            new Transaction(
                amount,
                "Top-up"
            )
        );

        System.out.printf(
            "%s topped up with ₹%.2f. Balance: ₹%.2f%n",
            cardNumber,
            amount,
            balance
        );
    }

    public void purchase(FoodItem item) {

        if (blocked) {
            System.out.println(
                "Purchase failed: Card is blocked."
            );
            return;
        }

        double chargedPrice =
            plan.getPrice(item.getPrice());

        if (chargedPrice > balance) {

            System.out.printf(
                "Purchase failed: Insufficient balance " +
                "(required ₹%.2f, available ₹%.2f).%n",
                chargedPrice,
                balance
            );

            return;
        }

        balance -= chargedPrice;

        transactions.add(
            new Transaction(
                -chargedPrice,
                item.getName()
            )
        );

        System.out.printf(
            "%s purchased for ₹%.2f. Balance: ₹%.2f%n",
            item.getName(),
            chargedPrice,
            balance
        );
    }

    public void refund(
        FoodItem item
    ) {

        String key = item.getName();

        if (refundedPurchases.contains(key)) {

            System.out.println(
                "Refund rejected: " +
                item.getName() +
                " has already been refunded."
            );

            return;
        }

        double refundAmount =
            plan.getPrice(item.getPrice());

        balance += refundAmount;

        transactions.add(
            new Transaction(
                refundAmount,
                "Refund " + item.getName()
            )
        );

        refundedPurchases.add(key);

        System.out.printf(
            "Refund of ₹%.2f for %s processed. " +
            "Balance: ₹%.2f%n",
            refundAmount,
            item.getName(),
            balance
        );
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }

    public void miniStatement() {

        System.out.print(
            "Mini-statement for " +
            cardNumber + ": "
        );

        double total = 0;

        for (int i = 0; i < transactions.size(); i++) {

            double amount =
                transactions.get(i).getAmount();

            total += amount;

            if (amount >= 0) {
                System.out.printf(
                    "+%.2f",
                    amount
                );
            } else {
                System.out.printf(
                    "%.2f",
                    amount
                );
            }

            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(
            " = ₹%.2f%n",
            total
        );
    }
}

public class A5CanteenSmartCard {

    public static void main(String[] args) {

        SmartCard card =
            new SmartCard(
                "C-2045",
                new HostellerPlan()
            );

        FoodItem thali =
            new FoodItem(
                "Veg Thali",
                120
            );

        FoodItem coffee =
            new FoodItem(
                "Cold Coffee",
                60
            );

        FoodItem expensive =
            new FoodItem(
                "Special Meal",
                400
            );

        card.topUp(500);

        card.purchase(thali);

        card.purchase(coffee);

        card.purchase(expensive);

        card.refund(thali);

        card.refund(thali);

        card.miniStatement();
    }
}
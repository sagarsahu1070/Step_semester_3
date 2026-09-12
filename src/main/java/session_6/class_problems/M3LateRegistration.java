class EventTicket {

    protected double balanceDue;

    private double[] lateFeeHistory;
    private int feeCount;

    public EventTicket(double basePrice) {

        balanceDue = basePrice;
        lateFeeHistory = new double[10];
        feeCount = 0;
    }

    public void pay(double amount) {

        if (amount > 0) {
            balanceDue -= amount;

            if (balanceDue < 0)
                balanceDue = 0;
        }
    }

    protected void applyLateFee(double amount) {

        balanceDue += amount;

        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public double[] getLateFeeHistory() {

        double[] result =
            new double[feeCount];

        for (int i = 0; i < feeCount; i++) {
            result[i] = lateFeeHistory[i];
        }

        return result;
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}

public class M3LateRegistration {

    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
            w.getBalanceDue());

        double[] history =
            w.getLateFeeHistory();

        history[0] = 999;

        double[] actual =
            w.getLateFeeHistory();

        System.out.println(
            actual[0]);
    }
}
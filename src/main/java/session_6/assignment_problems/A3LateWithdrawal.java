class RaceEntry {

    protected double balanceDue;

    private double[] lateFeeHistory;
    private int feeCount;

    public RaceEntry(double entryFee) {

        balanceDue = entryFee;
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

        double[] copy =
            new double[feeCount];

        for (int i = 0; i < feeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }
}

class RunnerEntry extends RaceEntry {

    public RunnerEntry(String bibNumber,
                       double entryFee,
                       String category) {

        super(entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}

public class A3LateWithdrawal {

    public static void main(String[] args) {

        RunnerEntry r =
            new RunnerEntry(
                "BIB2001",
                80,
                "Open 10K");

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(
            r.getBalanceDue());

        double[] history =
            r.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
            r.getLateFeeHistory()[0]);
    }
}
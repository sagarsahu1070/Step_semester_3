package constructors.assigment_problems;

public class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    private static double defaultMinimumSurgePercent;

    // One-time class-level initialization
    static {
        defaultMinimumSurgePercent = 1.0;
    }

    public DeliveryAccount(String studentId,
                           double orderValue) {

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay cannot be negative");
        }

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                    "Order value cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int first = Math.min(delayMinutes, 5);
        fee += first * orderValue * 0.005;

        if (delayMinutes > 5) {

            int second =
                    Math.min(delayMinutes, 15) - 5;

            fee += second * orderValue * 0.01;
        }

        if (delayMinutes > 15) {

            int third = delayMinutes - 15;

            fee += third * orderValue * 0.02;
        }

        double minimumFee =
                orderValue *
                defaultMinimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            System.out.println("Null account skipped.");
            return;
        }

        double surgeFee =
                account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumDeliveryAccount) {

            // Assumption: premium members receive a 20%
            // discount on the calculated surge fee.
            surgeFee = surgeFee * 0.80;

            System.out.println(
                    account.studentId
                    + " Premium | Amount: Rs "
                    + amount
                    + " | Surge Fee: Rs "
                    + surgeFee);

        } else {

            System.out.println(
                    account.studentId
                    + " Regular | Amount: Rs "
                    + amount
                    + " | Surge Fee: Rs "
                    + surgeFee);
        }
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if (accounts == null
                || amounts == null
                || delayMinutesArray == null) {

            throw new IllegalArgumentException(
                    "Arrays cannot be null");
        }

        /*
         * We reject different array lengths instead of processing
         * a partial batch. Otherwise an amount or delay could be
         * accidentally applied to the wrong student.
         */
        if (accounts.length != amounts.length
                || accounts.length != delayMinutesArray.length) {

            throw new IllegalArgumentException(
                    "All arrays must have the same length");
        }

        int processed = 0;
        int skipped = 0;
        int premium = 0;
        int regular = 0;

        double grandTotalSurge = 0.0;

        for (int i = 0; i < accounts.length; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                skipped++;
                continue;
            }

            double surgeFee =
                    account.calculateSurgeFee(
                            delayMinutesArray[i]);

            if (account instanceof PremiumDeliveryAccount) {

                // Assumption: 20% premium discount.
                surgeFee = surgeFee * 0.80;
                premium++;

            } else {
                regular++;
            }

            grandTotalSurge += surgeFee;
            processed++;

            account.processAccount(
                    account,
                    amounts[i],
                    delayMinutesArray[i]);
        }

        System.out.println();
        System.out.println(
                processed + " processed | "
                + skipped + " null skipped | "
                + premium + " premium | "
                + regular + " regular | "
                + "grand total surge fees = Rs "
                + grandTotalSurge);
    }

    public static class PremiumDeliveryAccount
            extends DeliveryAccount {

        public PremiumDeliveryAccount(
                String studentId,
                double orderValue) {

            super(studentId, orderValue);
        }

        public PremiumDeliveryAccount(String studentId) {
            super(studentId);
        }
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
                new PremiumDeliveryAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
                500,
                400,
                300
        };

        int[] delays = {
                10,
                5,
                0
        };

        processBatch(accounts, amounts, delays);
    }
}
package constructors.assigment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {

        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException(
                    "Minimum surge percent cannot be negative");
        }

        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                    "Order value cannot be negative");
        }

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay minutes cannot be negative");
        }

        // No delay means no surge fee at all.
        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        // First 5 minutes: 0.5% per minute
        int firstBracket = Math.min(delayMinutes, 5);
        fee += firstBracket *
                orderValue * 0.005;

        // Minutes 6-15: 1% per minute
        if (delayMinutes > 5) {

            int secondBracket =
                    Math.min(delayMinutes, 15) - 5;

            fee += secondBracket *
                    orderValue * 0.01;
        }

        // Minutes 16 onward: 2% per minute
        if (delayMinutes > 15) {

            int thirdBracket =
                    delayMinutes - 15;

            fee += thirdBracket *
                    orderValue * 0.02;
        }

        // Minimum surge floor applies only when delayed.
        double minimumFee =
                orderValue * minimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(
                "Delay 0: Rs "
                + calculator.calculateSurgeFee(500, 0));

        System.out.println(
                "Delay 1: Rs "
                + calculator.calculateSurgeFee(500, 1));

        System.out.println(
                "Delay 16: Rs "
                + calculator.calculateSurgeFee(500, 16));
    }
}
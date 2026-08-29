package constructors.class_problems;

import java.util.Arrays;

public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Full constructor
    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException(
                    "Fare cannot be negative"
            );
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException(
                    "Passenger count must be positive"
            );
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Constructor chaining
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    // Constructor chaining
    public FareSplitter(String tripId) {
        this(tripId, 0.0);
    }

    public double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        if (totalFare == 0) {
            return result;
        }

        long totalCents = Math.round(totalFare * 100);

        long baseCents = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;

        for (int i = 0; i < passengerCount; i++) {

            long share = baseCents;

            // Extra paisa goes to the last shares.
            if (i >= passengerCount - remainder) {
                share++;
            }

            result[i] = share / 100.0;
        }

        return result;
    }

    public boolean isConfirmationOverdue(
            int confirmed,
            int expected) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter splitter =
                new FareSplitter("TRIP001", 100000, 3);

        System.out.println(
                Arrays.toString(splitter.fareBreakdown())
        );

        FareSplitter provisional =
                new FareSplitter("TRIP003");

        System.out.println(
                Arrays.toString(provisional.fareBreakdown())
        );

        System.out.println(
                "Confirmation overdue: "
                        + splitter.isConfirmationOverdue(2, 3)
        );
    }
}
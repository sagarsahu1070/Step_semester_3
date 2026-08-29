package constructors.class_problems;

public class NightlyFleetReconciliation {

    static class BusTicketAccount {

        protected String bookingId;
        protected double ticketFare;

        protected static double minimumPenaltyPercent;

        // Static block executes once when the class loads.
        static {
            minimumPenaltyPercent = 1.0;
        }

        public BusTicketAccount(
                String bookingId,
                double ticketFare) {

            if (bookingId == null ||
                    bookingId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Invalid booking ID"
                );
            }

            if (ticketFare < 0) {
                throw new IllegalArgumentException(
                        "Ticket fare cannot be negative"
                );
            }

            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        // Constructor chaining
        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public final double calculatePenalty(
                int minutesLate) {

            if (minutesLate < 0) {
                throw new IllegalArgumentException(
                        "Minutes late cannot be negative"
                );
            }

            if (minutesLate == 0) {
                return 0.0;
            }

            double penalty = 0.0;

            int firstTier =
                    Math.min(minutesLate, 5);

            penalty +=
                    firstTier * ticketFare * 0.005;

            if (minutesLate > 5) {

                int secondTier =
                        Math.min(minutesLate, 15) - 5;

                penalty +=
                        secondTier * ticketFare * 0.01;
            }

            if (minutesLate > 15) {

                int thirdTier =
                        minutesLate - 15;

                penalty +=
                        thirdTier * ticketFare * 0.02;
            }

            double minimumPenalty =
                    ticketFare
                            * minimumPenaltyPercent
                            / 100.0;

            return Math.max(
                    penalty,
                    minimumPenalty
            );
        }

        public double settle(double amount) {
            return amount;
        }
    }

    static class Sleeper extends BusTicketAccount {

        public Sleeper(
                String bookingId,
                double ticketFare) {

            super(bookingId, ticketFare);
        }

        public double settle(double amount) {

            // Sleeper accounts get a 10% settlement reduction.
            return amount * 0.90;
        }
    }

    public static void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null) {
            return;
        }

        double settledAmount;

        if (account instanceof Sleeper) {

            settledAmount =
                    ((Sleeper) account).settle(amount);

            System.out.printf(
                    "%s | Sleeper | Settled: Rs %.2f | Penalty: Rs %.2f%n",
                    account.bookingId,
                    settledAmount,
                    account.calculatePenalty(minutesLate)
            );

        } else {

            settledAmount =
                    account.settle(amount);

            System.out.printf(
                    "%s | Regular | Settled: Rs %.2f | Penalty: Rs %.2f%n",
                    account.bookingId,
                    settledAmount,
                    account.calculatePenalty(minutesLate)
            );
        }
    }

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts == null ||
                amounts == null ||
                minutesLateArray == null) {

            System.out.println(
                    "Invalid batch: null array"
            );
            return;
        }

        // Process only the common length.
        // This prevents mismatched arrays from causing
        // ArrayIndexOutOfBoundsException.
        int limit = Math.min(
                accounts.length,
                Math.min(
                        amounts.length,
                        minutesLateArray.length
                )
        );

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double grandTotalPenalty = 0.0;

        for (int i = 0; i < limit; i++) {

            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (account instanceof Sleeper) {
                sleeperCount++;
            } else {
                regularCount++;
            }

            double penalty =
                    account.calculatePenalty(
                            minutesLateArray[i]
                    );

            grandTotalPenalty += penalty;

            processAccount(
                    account,
                    amounts[i],
                    minutesLateArray[i]
            );
        }

        System.out.println();
        System.out.println(
                processed
                        + " processed | "
                        + nullSkipped
                        + " null skipped | "
                        + sleeperCount
                        + " sleeper | "
                        + regularCount
                        + " regular"
        );

        System.out.printf(
                "Grand total penalties = Rs %.2f%n",
                grandTotalPenalty
        );

        if (limit < accounts.length ||
                limit < amounts.length ||
                limit < minutesLateArray.length) {

            System.out.println(
                    "Note: arrays had different lengths; "
                            + "only the common portion was processed."
            );
        }
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {

                new Sleeper(
                        "BK001",
                        2000
                ),

                null,

                new BusTicketAccount(
                        "BK002",
                        1200
                )
        };

        double[] amounts = {
                1200,
                900,
                700
        };

        int[] minutesLate = {
                10,
                5,
                0
        };

        processBatch(
                accounts,
                amounts,
                minutesLate
        );
    }
}
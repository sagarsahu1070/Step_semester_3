package constructors.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(
            double minimumPenaltyPercent) {

        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException(
                    "Minimum penalty cannot be negative"
            );
        }

        this.minimumPenaltyPercent =
                minimumPenaltyPercent;
    }

    public final double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException(
                    "Ticket fare cannot be negative"
            );
        }

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
            );
        }

        // No penalty when passenger is on time.
        if (minutesLate == 0) {
            return 0.0;
        }

        double tieredPenalty = 0.0;

        // Minutes 1-5: 0.5%
        int firstTier = Math.min(minutesLate, 5);

        tieredPenalty +=
                firstTier * ticketFare * 0.005;

        // Minutes 6-15: 1%
        if (minutesLate > 5) {

            int secondTier =
                    Math.min(minutesLate, 15) - 5;

            tieredPenalty +=
                    secondTier * ticketFare * 0.01;
        }

        // Minutes 16+: 2%
        if (minutesLate > 15) {

            int thirdTier = minutesLate - 15;

            tieredPenalty +=
                    thirdTier * ticketFare * 0.02;
        }

        // Minimum flat-fee floor
        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(
                tieredPenalty,
                minimumPenalty
        );
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(
                "0 minutes: Rs "
                        + calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
                "1 minute: Rs "
                        + calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
                "16 minutes: Rs "
                        + calculator.calculatePenalty(1000, 16)
        );
    }
}
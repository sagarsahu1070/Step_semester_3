
import java.util.Scanner;

public class P2RideFareEngine {
    abstract static class Ride {
        protected double distance;

        Ride(double distance) {
            this.distance = distance;
        }

        abstract double calculateFare();

        abstract String getType();
    }

    static class Auto extends Ride {
        Auto(double distance) {
            super(distance);
        }

        double calculateFare() {
            return 30 + 15 * distance;
        }

        String getType() {
            return "Auto";
        }
    }

    static class Cab extends Ride {
        private double surge;

        Cab(double distance, double surge) {
            super(distance);
            this.surge = surge;
        }

        double calculateFare() {
            return (50 + 20 * distance) * surge;
        }

        String getType() {
            return "Cab";
        }
    }

    static class Bike extends Ride {
        private int waitingMinutes;

        Bike(double distance, int waitingMinutes) {
            super(distance);
            this.waitingMinutes = waitingMinutes;
        }

        double calculateFare() {
            return 20 + 10 * distance + 2 * waitingMinutes;
        }

        String getType() {
            return "Bike";
        }
    }

    private static String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Ride[] rides = new Ride[n];

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double distance = sc.nextDouble();

            switch (type.toLowerCase()) {
                case "auto":
                    rides[i] = new Auto(distance);
                    break;
                case "cab":
                    double surge = sc.nextDouble();
                    rides[i] = new Cab(distance, surge);
                    break;
                case "bike":
                    int waiting = sc.nextInt();
                    rides[i] = new Bike(distance, waiting);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown ride: " + type);
            }
        }

        double grandTotal = 0;

        for (Ride ride : rides) {
            double fare = ride.calculateFare();
            System.out.println(ride.getType() + ": " + format(fare));
            grandTotal += fare;
        }

        System.out.println("Total: " + format(grandTotal));
        sc.close();
    }
}

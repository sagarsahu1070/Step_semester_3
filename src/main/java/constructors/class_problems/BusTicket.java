package constructors.class_problems;

public class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    // Only parameterized constructor is provided.
    public BusTicket(String passengerName, String destination) {

        if (!isValidName(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (!isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private boolean isValidName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {
                return false;
            }
        }

        return true;
    }

    private boolean isValidDestination(String destination) {

        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < destination.length(); i++) {
            char ch = destination.charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {
                return false;
            }
        }

        return true;
    }

    public void markCheckedIn() {

        if (checkedIn) {
            throw new IllegalStateException(
                    "Ticket is already checked in"
            );
        }

        checkedIn = true;
    }

    private String bookingKey() {
        return passengerName.toLowerCase()
                + "|" + destination.toLowerCase();
    }

    public static void processBatch(String[][] rawBookings) {

        String[] acceptedKeys = new String[rawBookings.length];

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {

            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {

                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                String key = ticket.bookingKey();

                boolean duplicate = false;

                for (int i = 0; i < valid; i++) {
                    if (acceptedKeys[i].equals(key)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    duplicates++;
                } else {
                    acceptedKeys[valid] = key;
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
        System.out.println("Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(bookings);
    }
}
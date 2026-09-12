class RaceEntry {

    private static int bibCounter = 0;

    private final String entryCode;

    protected double balanceDue;

    public RaceEntry(double entryFee) {

        bibCounter++;

        entryCode =
            "BIB-" + (1000 + bibCounter);

        balanceDue = entryFee;
    }

    public void pay(double amount) {

        if (amount > 0) {

            balanceDue -= amount;

            if (balanceDue < 0)
                balanceDue = 0;
        }
    }

    public void pay(double amount,
                    String mode) {

        System.out.println(
            "Paying via " + mode);

        pay(amount);
    }

    public static boolean isValidDiscountCode(
            String code) {

        if (code == null ||
            code.length() != 5) {

            return false;
        }

        if (code.charAt(0) != 'M')
            return false;

        if (!Character.isDigit(code.charAt(1)))
            return false;

        if (!Character.isDigit(code.charAt(2)))
            return false;

        if (!Character.isDigit(code.charAt(3)))
            return false;

        if (!Character.isUpperCase(code.charAt(4)))
            return false;

        return true;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public double getBalanceDue() {
        return balanceDue;
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(double entryFee,
                          int teamSize) {

        super(entryFee);
        this.teamSize = teamSize;
    }
}

public class A5RaceSettlement {

    static String settleNight(
            RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            }
            else {
                individual++;
            }
        }

        return processed
            + " processed | "
            + nullSkipped
            + " null skipped | "
            + relay
            + " relay | "
            + individual
            + " individual";
    }

    public static void main(String[] args) {

        RaceEntry r =
            new RaceEntry(50);

        System.out.println(
            r.getEntryCode());

        System.out.println(
            RaceEntry.isValidDiscountCode(
                "M123A"));

        System.out.println(
            RaceEntry.isValidDiscountCode(
                "M12A"));

        System.out.println(
            RaceEntry.isValidDiscountCode(
                "X123A"));

        r.pay(10, "UPI");

        RaceEntry[] entries = {

            new RaceEntry(80),

            null,

            new RelayTeamEntry(
                300,
                4)
        };

        System.out.println(
            settleNight(entries));

        System.out.println(
            RaceEntry.getBibCounter());
    }
}
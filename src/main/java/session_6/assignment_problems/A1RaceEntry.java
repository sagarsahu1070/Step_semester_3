class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.length() < 4) {

            throw new IllegalArgumentException(
                "Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {

        if (amount > 0) {
            balanceDue -= amount;

            if (balanceDue < 0) {
                balanceDue = 0;
            }
        }
    }

    public double getBalanceDue() {
        return balanceDue;
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(String bibNumber,
                       double entryFee,
                       String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }
}

public class A1RaceEntry {

    static String registerBatch(
            String[] bibNumbers,
            double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {

            try {
                new RaceEntry(bib, entryFee);
                registered++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        RunnerEntry r =
            new RunnerEntry(
                "BIB2001",
                80,
                "Open 10K");

        r.pay(30);

        System.out.println(
            r.getBalanceDue());

        String[] bibs = {
            "BIB1",
            "B1",
            "BIB2"
        };

        System.out.println(
            registerBatch(bibs, 80));
    }
}
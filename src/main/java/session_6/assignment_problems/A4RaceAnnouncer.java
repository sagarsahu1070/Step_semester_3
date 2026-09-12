class RaceEntry {

    protected String bibNumber;
    protected double balanceDue;

    public RaceEntry(String bibNumber,
                     double entryFee) {

        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;
    }

    public void announce(StringBuilder sb) {

        sb.append(
            "Race Entry | Bib: ")
          .append(bibNumber)
          .append(" | Balance: ")
          .append(balanceDue);
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

    @Override
    public void announce(StringBuilder sb) {

        sb.append(
            "Runner Entry | Bib: ")
          .append(bibNumber)
          .append(" | Category: ")
          .append(category)
          .append(" | Balance: ")
          .append(balanceDue);
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(String bibNumber,
                          double entryFee,
                          int teamSize) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public void announce(StringBuilder sb) {

        sb.append(
            "Relay Team | Bib: ")
          .append(bibNumber)
          .append(" | Team Size: ")
          .append(teamSize)
          .append(" | Balance: ")
          .append(balanceDue);
    }
}

public class A4RaceAnnouncer {

    static String announceAll(
            RaceEntry[] entries) {

        StringBuilder sb =
            new StringBuilder();

        for (RaceEntry entry : entries) {

            entry.announce(sb);

            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                    (RelayTeamEntry) entry;

                sb.append(
                    " [Team size via downcast: ")
                  .append(relay.getTeamSize())
                  .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        RaceEntry[] fleet = {

            new RunnerEntry(
                "BIB2001",
                90,
                "Open 10K"),

            new RelayTeamEntry(
                "BIB4001",
                300,
                4)
        };

        System.out.println(
            announceAll(fleet));
    }
}
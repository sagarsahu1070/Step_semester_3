class EventTicket {

    protected double balanceDue;

    public EventTicket(double basePrice) {
        balanceDue = basePrice;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void printTicket(StringBuilder sb) {

        sb.append("Standard | Balance: ")
          .append(balanceDue);
    }
}

class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(
            double basePrice,
            String track) {

        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public void printTicket(StringBuilder sb) {

        sb.append("Workshop | Track: ")
          .append(track)
          .append(" | Balance: ")
          .append(balanceDue);
    }
}

public class M4TicketAnnouncer {

    static String batchPrint(
            EventTicket[] tickets) {

        StringBuilder sb =
            new StringBuilder();

        for (EventTicket ticket : tickets) {

            ticket.printTicket(sb);

            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket w =
                    (WorkshopTicket) ticket;

                sb.append(" [Track via downcast: ")
                  .append(w.getTrack())
                  .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        EventTicket[] tickets = {

            new EventTicket(500),

            new WorkshopTicket(
                1200,
                "AI/ML")
        };

        System.out.println(
            batchPrint(tickets));
    }
}
import java.util.*;

enum ParcelStatus {
    BOOKED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED
}

interface ShippingType {
    double calculateCharge(double weight);
    String getName();
}

class StandardShipping implements ShippingType {

    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }

    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingType {

    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }

    public String getName() {
        return "Express";
    }
}

class FragileShipping implements ShippingType {

    public double calculateCharge(double weight) {
        return new StandardShipping()
            .calculateCharge(weight) + 50;
    }

    public String getName() {
        return "Fragile";
    }
}

interface NotificationChannel {
    void notify(String parcelId, ParcelStatus status);
}

class SmsChannel implements NotificationChannel {

    public void notify(String parcelId, ParcelStatus status) {
        System.out.println(
            "[SMS] " + parcelId +
            " is now " + status + "."
        );
    }
}

class EmailChannel implements NotificationChannel {

    public void notify(String parcelId, ParcelStatus status) {
        System.out.println(
            "[Email] " + parcelId +
            " is now " + status + "."
        );
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}

class Parcel {

    private String id;
    private double weight;
    private ShippingType shippingType;
    private ParcelStatus status;
    private List<NotificationChannel> channels =
        new ArrayList<>();

    public Parcel(
        String id,
        double weight,
        ShippingType shippingType
    ) {

        this.id = id;
        this.weight = weight;
        this.shippingType = shippingType;
        this.status = ParcelStatus.BOOKED;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public double getCharge() {
        return shippingType.calculateCharge(weight);
    }

    private void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }

    public void changeStatus(ParcelStatus newStatus) {

        if (!isValidTransition(newStatus)) {

            System.out.println(
                "Invalid transition: " +
                status + " → " + newStatus +
                " is not allowed."
            );

            return;
        }

        status = newStatus;

        notifyChannels();
    }

    private boolean isValidTransition(ParcelStatus next) {

        if (status == ParcelStatus.BOOKED)
            return next == ParcelStatus.PICKED_UP;

        if (status == ParcelStatus.PICKED_UP)
            return next == ParcelStatus.IN_TRANSIT;

        if (status == ParcelStatus.IN_TRANSIT)
            return next == ParcelStatus.OUT_FOR_DELIVERY;

        if (status == ParcelStatus.OUT_FOR_DELIVERY)
            return next == ParcelStatus.DELIVERED;

        return false;
    }

    public void cancel() {

        if (status != ParcelStatus.BOOKED) {

            System.out.println(
                "Cancellation failed: " +
                id +
                " can be cancelled only while BOOKED."
            );

            return;
        }

        System.out.println(
            id + " cancelled successfully."
        );
    }
}

class ParcelService {

    public Parcel bookParcel(
        String id,
        double weight,
        ShippingType type
    ) {

        Parcel parcel =
            new Parcel(id, weight, type);

        System.out.printf(
            "Parcel %s booked (%s, %.0f kg).%n",
            id,
            type.getName(),
            weight
        );

        System.out.printf(
            "Charge: ₹%.2f%n",
            parcel.getCharge()
        );

        return parcel;
    }
}

public class A2SwiftShipParcel {

    public static void main(String[] args) {

        ParcelService service =
            new ParcelService();

        Parcel parcel =
            service.bookParcel(
                "P101",
                2,
                new ExpressShipping()
            );

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        parcel.changeStatus(
            ParcelStatus.BOOKED
        );

        parcel.changeStatus(
            ParcelStatus.PICKED_UP
        );

        parcel.cancel();

        parcel.changeStatus(
            ParcelStatus.IN_TRANSIT
        );

        parcel.changeStatus(
            ParcelStatus.DELIVERED
        );
    }
}
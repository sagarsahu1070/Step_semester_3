import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Room {

    private String name;
    private List<Reservation> reservations =
        new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePrice(long days);

    public boolean isAvailable(
        LocalDate start,
        LocalDate end
    ) {

        for (Reservation r : reservations) {

            if (r.isActive()
                && start.isBefore(r.getEndDate())
                && end.isAfter(r.getStartDate())) {

                return false;
            }
        }

        return true;
    }

    public void addReservation(Reservation r) {
        reservations.add(r);
    }
}

class StandardRoom extends Room {

    public StandardRoom(String name) {
        super(name);
    }

    public double calculatePrice(long days) {
        return days * 150;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String name) {
        super(name);
    }

    public double calculatePrice(long days) {
        return days * 200;
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }
}

class Reservation {

    private Room room;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active = true;

    public Reservation(
        Room room,
        Customer customer,
        LocalDate startDate,
        LocalDate endDate
    ) {

        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return active;
    }

    public double getPrice() {

        long days =
            ChronoUnit.DAYS.between(
                startDate,
                endDate
            );

        return room.calculatePrice(days);
    }

    public void cancel() {

        if (!active) {
            System.out.println("Reservation already cancelled.");
            return;
        }

        active = false;

        System.out.println(
            "Reservation for " +
            room.getName() +
            " cancelled successfully."
        );
    }
}

class BookingManager {

    public Reservation bookRoom(
        Customer customer,
        Room room,
        LocalDate start,
        LocalDate end
    ) {

        if (!room.isAvailable(start, end)) {

            System.out.println(
                "Booking failed: " +
                room.getName() +
                " is not available for " +
                start + " to " + end
            );

            return null;
        }

        Reservation reservation =
            new Reservation(
                room,
                customer,
                start,
                end
            );

        room.addReservation(reservation);

        System.out.printf(
            "%s booked from %s to %s. Total price: $%.2f%n",
            room.getName(),
            start,
            end,
            reservation.getPrice()
        );

        return reservation;
    }
}

public class M3HotelBooking {

    public static void main(String[] args) {

        Customer customer =
            new Customer("John");

        Room deluxe =
            new DeluxeRoom("Deluxe Room 101");

        Room standard =
            new StandardRoom("Standard Room 205");

        BookingManager manager =
            new BookingManager();

        Reservation r1 =
            manager.bookRoom(
                customer,
                deluxe,
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5)
            );

        Reservation r2 =
            manager.bookRoom(
                customer,
                standard,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
            );

        Reservation r3 =
            manager.bookRoom(
                customer,
                deluxe,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
            );

        if (r1 != null) {
            r1.cancel();
        }
    }
}
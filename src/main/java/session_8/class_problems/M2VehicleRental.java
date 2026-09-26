abstract class Vehicle {

    private String name;
    private boolean available = true;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {

    public StandardCar(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {

    public LuxuryCar(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Rental {

    private Vehicle vehicle;
    private int days;

    public Rental(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
    }

    public double getTotalCharge() {
        return vehicle.calculateCharge(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class RentalService {

    public Rental rent(
        Customer customer,
        Vehicle vehicle,
        int days
    ) {

        if (!vehicle.isAvailable()) {
            System.out.println(
                vehicle.getName() + " is not available."
            );
            return null;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(vehicle, days);

        System.out.printf(
            "%s rented for %d days. Total charge: $%.2f%n",
            vehicle.getName(),
            days,
            rental.getTotalCharge()
        );

        return rental;
    }

    public void returnVehicle(Rental rental) {

        Vehicle vehicle = rental.getVehicle();

        vehicle.setAvailable(true);

        System.out.println(
            vehicle.getName() + " returned. Now available."
        );
    }
}

public class M2VehicleRental {

    public static void main(String[] args) {

        Customer customer = new Customer("John");

        Vehicle luxury =
            new LuxuryCar("Luxury Car A");

        Vehicle standard =
            new StandardCar("Standard Car B");

        RentalService service =
            new RentalService();

        Rental r1 =
            service.rent(customer, luxury, 3);

        Rental r2 =
            service.rent(customer, standard, 5);

        service.returnVehicle(r1);
    }
}
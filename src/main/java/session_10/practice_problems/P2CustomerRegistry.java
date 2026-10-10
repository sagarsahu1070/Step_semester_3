import java.util.*;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Customer))
            return false;

        Customer c = (Customer) obj;
        return id == c.id && name.equals(c.name);
    }

    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class P2CustomerRegistry {
    public static void main(String[] args) {
        Set<Customer> registry = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(registry.add(c1));
        System.out.println(registry.add(c2)
                ? "true" : "false (duplicate rejected)");
        System.out.println(registry.add(c3));
        System.out.println("unique count " + registry.size());
        System.out.println("contains: " + registry.contains(c1));
    }
}
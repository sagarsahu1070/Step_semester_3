import java.util.*;

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void showEnergy();
}

class Device {
    String id;
    boolean on = false;

    Device(String id) {
        this.id = id;
    }

    void powerOn() {
        on = true;
        System.out.println(id + " is ON");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    double energy;

    Plug(String id, double energy) {
        super(id);
        this.energy = energy;
    }

    public void showEnergy() {
        System.out.println(id + " energy " + energy + " kWh");
    }
}

public class P1SmartHome {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();

        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        command(devices, "ON", "L1", "");
        command(devices, "DIM", "L1", "40");
        command(devices, "DIM", "F1", "30");
        command(devices, "SCHEDULE", "F1", "22:00");
        command(devices, "ENERGY", "P1", "");
        command(devices, "ENERGY", "L1", "");
    }

    static void command(Map<String, Device> devices,
                        String cmd, String id, String value) {
        Device d = devices.get(id);

        if (d == null) {
            System.out.println(id + " rejected: device not found");
            return;
        }

        switch (cmd) {
            case "ON":
                d.powerOn();
                break;

            case "DIM":
                if (d instanceof Dimmable)
                    ((Dimmable) d).dim(Integer.parseInt(value));
                else
                    System.out.println(id + " rejected: DIM unsupported");
                break;

            case "SCHEDULE":
                if (d instanceof Schedulable)
                    ((Schedulable) d).schedule(value);
                else
                    System.out.println(id + " rejected: SCHEDULE unsupported");
                break;

            case "ENERGY":
                if (d instanceof EnergyMonitor)
                    ((EnergyMonitor) d).showEnergy();
                else
                    System.out.println(id + " rejected: ENERGY unsupported");
                break;
        }
    }
}
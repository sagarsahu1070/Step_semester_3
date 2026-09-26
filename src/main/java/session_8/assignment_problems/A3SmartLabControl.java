import java.util.*;

interface Capability {
    String getName();
    void apply(String deviceName, double value);
}

class PowerCapability implements Capability {

    private boolean on = false;

    public String getName() {
        return "Power";
    }

    public void apply(String deviceName, double value) {

        if (value != 0 && value != 1) {
            System.out.println(
                "Rejected: Power must be 0 or 1."
            );
            return;
        }

        on = value == 1;

        System.out.println(
            deviceName + ": " +
            (on ? "ON" : "OFF")
        );
    }
}

class BrightnessCapability implements Capability {

    public String getName() {
        return "Brightness";
    }

    public void apply(String deviceName, double value) {

        if (value < 0 || value > 100) {
            System.out.println(
                "Rejected: " +
                deviceName +
                " brightness must be between 0% and 100%."
            );
            return;
        }

        System.out.println(
            deviceName +
            ": brightness set to " +
            (int) value + "%"
        );
    }
}

class TemperatureCapability implements Capability {

    public String getName() {
        return "Temperature";
    }

    public void apply(String deviceName, double value) {

        if (value < 16 || value > 30) {
            System.out.println(
                "Rejected: " +
                deviceName +
                " temperature must be between 16°C and 30°C."
            );
            return;
        }

        System.out.println(
            deviceName +
            ": temperature set to " +
            (int) value + "°C"
        );
    }
}

class Device {

    private String name;
    private Map<String, Capability> capabilities =
        new HashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {

        capabilities.put(
            capability.getName(),
            capability
        );

        System.out.println(
            name + ": " +
            capability.getName() +
            " capability added."
        );
    }

    public boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }

    public void apply(
        String capabilityName,
        double value
    ) {

        Capability capability =
            capabilities.get(capabilityName);

        if (capability != null) {
            capability.apply(name, value);
        }
    }
}

class SceneStep {

    private String capabilityName;
    private double value;

    public SceneStep(
        String capabilityName,
        double value
    ) {

        this.capabilityName = capabilityName;
        this.value = value;
    }

    public int apply(List<Device> devices) {

        int count = 0;

        for (Device device : devices) {

            if (device.hasCapability(capabilityName)) {

                device.apply(
                    capabilityName,
                    value
                );

                count++;
            }
        }

        return count;
    }
}

class Scene {

    private String name;
    private List<SceneStep> steps =
        new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {

        System.out.println(
            "Scene '" + name + "' started."
        );

        int actions = 0;

        for (SceneStep step : steps) {
            actions += step.apply(devices);
        }

        System.out.println(
            "Scene '" + name +
            "' completed: " +
            actions +
            " actions applied."
        );
    }
}

public class A3SmartLabControl {

    public static void main(String[] args) {

        Device ac =
            new Device("Lab AC");

        Device lights =
            new Device("Ceiling Lights");

        Device projector =
            new Device("Projector");

        ac.addCapability(
            new PowerCapability()
        );

        ac.addCapability(
            new TemperatureCapability()
        );

        lights.addCapability(
            new PowerCapability()
        );

        lights.addCapability(
            new BrightnessCapability()
        );

        projector.addCapability(
            new PowerCapability()
        );

        List<Device> devices =
            Arrays.asList(
                ac,
                lights,
                projector
            );

        Scene lectureMode =
            new Scene("Lecture Mode");

        lectureMode.addStep(
            new SceneStep("Power", 1)
        );

        lectureMode.addStep(
            new SceneStep("Brightness", 40)
        );

        lectureMode.addStep(
            new SceneStep("Temperature", 24)
        );

        lectureMode.execute(devices);

        ac.apply(
            "Temperature",
            12
        );

        projector.addCapability(
            new BrightnessCapability()
        );

        projector.apply(
            "Brightness",
            70
        );
    }
}
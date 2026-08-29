package constructors.class_problems;

public class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode,
                    String routeName,
                    int priority) {

        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    // Default priority = 5
    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {

        // First priority
        if (this.priority != other.priority) {
            return Integer.compare(
                    this.priority,
                    other.priority
            );
        }

        // Second route code, case-insensitive
        int codeResult =
                this.routeCode.compareToIgnoreCase(
                        other.routeCode
                );

        if (codeResult != 0) {
            return codeResult;
        }

        // Third route name, case-insensitive
        return this.routeName.compareToIgnoreCase(
                other.routeName
        );
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = routes.clone();

        // Stable bubble sort
        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0; j < result.length - 1 - i; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getPriority() {
        return priority;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
                new BusRoute(
                        "RT205L",
                        "Airport Express",
                        3
                ),

                new BusRoute(
                        "rt201j",
                        "City Central",
                        4
                ),

                new BusRoute(
                        "RT299T",
                        "Night Service"
                )
        };

        BusRoute[] ranked = rankRoutes(routes);

        System.out.println("Ranked Routes:");

        for (BusRoute route : ranked) {
            System.out.println(
                    route.getRouteCode()
                            + " | "
                            + route.getRouteName()
                            + " | Priority: "
                            + route.getPriority()
            );
        }
    }
}
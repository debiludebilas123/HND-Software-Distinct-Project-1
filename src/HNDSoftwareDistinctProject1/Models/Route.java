package HNDSoftwareDistinctProject1.Models;

import java.util.UUID;

public class Route {
    private String routeID;
    private String routeName;

    public Route(String routeID,  String routeName) {
        this.routeID = (routeID != null) ? routeID : "ROU-" + UUID.randomUUID().toString().substring(0, 10);
        this.routeName = routeName;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getRouteID() {
        return routeID;
    }
}

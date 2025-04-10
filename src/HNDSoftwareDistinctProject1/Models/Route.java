package HNDSoftwareDistinctProject1.Models;

import java.util.UUID;

public class Route {
    private String routeID;
    private String routeName;
    private String midStopOne;
    private String midStopTwo;

    public Route(String routeID,  String routeName, String midStopOne, String midStopTwo) {
        this.routeID = (routeID != null) ? routeID : "ROU-" + UUID.randomUUID().toString().substring(0, 10);
        this.routeName = routeName;
        this.midStopOne = midStopOne;
        this.midStopTwo = midStopTwo;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getRouteID() {
        return routeID;
    }

    public String getMidStopOne() {
        return midStopOne;
    }

    public String getMidStopTwo() {
        return midStopTwo;
    }
}

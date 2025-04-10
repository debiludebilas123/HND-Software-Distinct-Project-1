package HNDSoftwareDistinctProject1.Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Flight {
    private String flightID;
    private int flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int capacity;

    public Flight(String flightID, int flightNumber, String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int capacity) {
        this.flightID = (flightID != null) ? flightID : "FLI-" + UUID.randomUUID().toString().substring(0, 10);
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.capacity = capacity;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public String getFlightID() {
        return flightID;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }
}


package HNDSoftwareDistinctProject1.Models;

import java.time.LocalDate;

public class Booking {
    private String bookingID;
    private LocalDate bookingDate;
    private int adultTicket;
    private int childTicket;
    private int concessionTicket;
    private String customerID;
    private String flightID;

    public Booking(String bookingID, LocalDate bookingDate, int adultTicket, int childTicket, int concessionTicket, String customerID, String flightID) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.adultTicket = adultTicket;
        this.childTicket = childTicket;
        this.concessionTicket = concessionTicket;
        this.customerID = customerID;
        this.flightID = flightID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public int getAdultTicket() {
        return adultTicket;
    }

    public int getChildTicket() {
        return childTicket;
    }

    public int getConcessionTicket() {
        return concessionTicket;
    }

    public String getFlightID() {
        return flightID;
    }

    public String getCustomerID() {
        return customerID;
    }
}

package HNDSoftwareDistinctProject1.Models;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private String bookingID;
    private LocalDate bookingDate;
    private int adultTicket;
    private int childTicket;
    private int concessionTicket;

    public Booking(String bookingID, LocalDate bookingDate, int adultTicket, int childTicket, int concessionTicket) {
        this.bookingID = (bookingID != null) ? bookingID : "BOO-" + UUID.randomUUID().toString().substring(0, 10);
        this.bookingDate = bookingDate;
        this.adultTicket = adultTicket;
        this.childTicket = childTicket;
        this.concessionTicket = concessionTicket;
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
}

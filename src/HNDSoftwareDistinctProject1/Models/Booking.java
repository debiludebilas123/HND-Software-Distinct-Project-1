package HNDSoftwareDistinctProject1.Models;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private String bookingID;
    private LocalDate bookingDate;

    public Booking(String bookingID, LocalDate bookingDate) {
        this.bookingID = (bookingID != null) ? bookingID : "BOO-" + UUID.randomUUID().toString().substring(0, 10);
        this.bookingDate = bookingDate;
    }

    public String getBookingID() {
        return bookingID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }
}

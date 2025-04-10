package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.BaseManagementPanel;
import HNDSoftwareDistinctProject1.Models.Booking;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingManagement extends BaseManagementPanel {
    private JButton addBookingButton;
    private JPanel bookingManagementPanel;
    private JTable bookingManagementTable;
    private JTextField bookingDateInput;
    private JButton clearBookingsButton;
    private JButton backToMenuButton;
    private JTextField adultTicketInput;
    private JTextField childTicketInput;
    private JTextField concessionTicketInput;
    final private List<Booking> bookingList = new ArrayList<>();

    public BookingManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = bookingManagementPanel;
        this.table = bookingManagementTable;

        String[] bookingTableColumns = {"Booking ID", "Booking Date", "Adult Tickets", "Child Tickets", "Concession Tickets"};
        bookingManagementTable.setModel(ManagementController.createModel(bookingTableColumns));

        clearTable();
        backToMenuButton.addActionListener(e -> switchToMainPanel());
        addInsurance();
    }

    private void addInsurance() {
        addBookingButton.addActionListener(e -> {
            if (!validateInputs()) {
                return;
            }

            // Create booking ID
            String bookingID = "BOO-" + UUID.randomUUID().toString().substring(0, 10);
            Booking booking = new Booking(
                    bookingID,
                    LocalDate.parse(bookingDateInput.getText()),
                    Integer.parseInt(adultTicketInput.getText()),
                    Integer.parseInt(childTicketInput.getText()),
                    Integer.parseInt(concessionTicketInput.getText())
            );

            bookingList.add(booking);

            // add booking
            DefaultTableModel model = (DefaultTableModel) bookingManagementTable.getModel();
            model.addRow(new Object[]{booking.getBookingID(), booking.getBookingDate(), booking.getAdultTicket(), booking.getChildTicket(), booking.getConcessionTicket()});
            bookingDateInput.setText("");
            adultTicketInput.setText("");
            childTicketInput.setText("");
            concessionTicketInput.setText("");
            showSuccess("Booking successfully added!");
        });
    }

    private void clearTable() {
        clearBookingsButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) bookingManagementTable.getModel();
            model.setRowCount(0);
            showSuccess("Table successfully cleared!");
        });
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getInsuranceInputValues();

        // Check if any inputs are empty
        if (values[0].toString().isEmpty() || values[1].toString().isEmpty() || values[2].toString().isEmpty() || values[3].toString().isEmpty()) {
            showError("Please fill all the fields!");
            return false;
        }

        if (!isObjectLocalDate(values[0].toString())) {
            showError("Please enter a valid date for the booking!");
            return false;
        }

        if (!isObjectInteger(values[1]) || !isObjectInteger(values[2]) || !isObjectInteger(values[3])) {
            showError("Please enter a number for the tickets!");
            return false;
        }

        return true;
    }

    private Object[] getInsuranceInputValues() {
        String bookingDate = bookingDateInput.getText();
        String adultTicket = adultTicketInput.getText();
        String childTicket = childTicketInput.getText();
        String concessionTicket = concessionTicketInput.getText();

        return new Object[]{bookingDate, adultTicket, childTicket, concessionTicket};
    }

    public JPanel getBookingManagementPanel() {
        return bookingManagementPanel;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }
}

package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.BaseManagementPanel;
import HNDSoftwareDistinctProject1.Models.Booking;
import HNDSoftwareDistinctProject1.Models.Customer;
import HNDSoftwareDistinctProject1.Models.Flight;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.HierarchyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private JComboBox customerIDComboBox;
    private JComboBox flightIDComboBox;
    final private List<Booking> bookingList = new ArrayList<>();
    private final CustomerManagement customerManagement;
    private final FlightManagement flightManagement;

    public BookingManagement(JFrame frame, CustomerManagement customerManagement, FlightManagement flightManagement) {
        super(frame, null, null);
        this.panel = bookingManagementPanel;
        this.table = bookingManagementTable;
        this.customerManagement = customerManagement;
        this.flightManagement = flightManagement;

        refreshComboBoxes();

        String[] bookingTableColumns = {"Booking ID", "Booking Date", "Adult Tickets", "Child Tickets", "Concession Tickets", "Customer ID", "Flight ID"};
        bookingManagementTable.setModel(ManagementController.createModel(bookingTableColumns));

        clearTable();
        backToMenuButton.addActionListener(e -> switchToMainPanel());
        addInsurance();
    }

    public void refreshComboBoxes() {
        panel.addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                if (panel.isShowing()) {
                    populateComboBoxes();
                }
            }
        });
    }

    public void populateComboBoxes() {
        customerIDComboBox.removeAllItems();
        flightIDComboBox.removeAllItems();

        for (Customer customer : customerManagement.getCustomerList()) {
            customerIDComboBox.addItem(customer.getCustomerID());
        }

        for (Flight flight : flightManagement.getFlightList()) {
            flightIDComboBox.addItem(flight.getFlightID());
        }
    }

    private void addInsurance() {
        addBookingButton.addActionListener(e -> {
            if (!validateInputs()) {
                return;
            }

            // Create booking ID
            String bookingID = "BOOK-" + getBookingList().size() + 1;
            Booking booking = new Booking(
                    bookingID,
                    LocalDate.parse(bookingDateInput.getText()),
                    Integer.parseInt(adultTicketInput.getText()),
                    Integer.parseInt(childTicketInput.getText()),
                    Integer.parseInt(concessionTicketInput.getText()),
                    customerIDComboBox.getSelectedItem().toString(),
                    flightIDComboBox.getSelectedItem().toString()
            );

            bookingList.add(booking);

            // add booking
            DefaultTableModel model = (DefaultTableModel) bookingManagementTable.getModel();
            model.addRow(new Object[]{booking.getBookingID(), booking.getBookingDate(), booking.getAdultTicket(), booking.getChildTicket(),
                    booking.getConcessionTicket(), booking.getCustomerID(), booking.getFlightID()});
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

        if (customerIDComboBox.getSelectedItem() == null) {
            showError("Please create a customer record to get a customer ID!");
            return false;
        }

        if (flightIDComboBox.getSelectedItem() == null) {
            showError("Please create a flight record to get a flight ID!");
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

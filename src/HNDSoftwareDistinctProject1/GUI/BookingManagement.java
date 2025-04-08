package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.BaseManagementPanel;
import HNDSoftwareDistinctProject1.Models.Booking;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import java.time.LocalDate;
import java.util.UUID;

public class BookingManagement extends BaseManagementPanel {
    private JButton addBookingButton;
    private JPanel bookingManagementPanel;
    private JTable bookingManagementTable;
    private JTextField bookingDateInput;
    private JButton clearBookingsButton;
    private JButton backToMenuButton;

    public BookingManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = bookingManagementPanel;
        this.table = bookingManagementTable;

        String[] bookingTableColumns = {"bookingID", "bookingDate"};
        bookingManagementTable.setModel(ManagementController.createModel(bookingTableColumns));

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
                    LocalDate.parse(bookingDateInput.getText())
            );
            // add booking

            showSuccess("You have successfully added a booking to the table.");
        });
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getInsuranceInputValues();

        // Check if any inputs are empty
        if (values[0].toString().isEmpty() || !isObjectLocalDate(values[0].toString())) {
            showError("Please fill all the fields and make sure the date follows correct formatting.");
            return false;
        }
        return true;
    }

    private Object[] getInsuranceInputValues() {
        String bookingDate = bookingDateInput.getText();

        return new Object[]{bookingDate};
    }

    public JPanel getBookingManagementPanel() {
        return bookingManagementPanel;
    }
}

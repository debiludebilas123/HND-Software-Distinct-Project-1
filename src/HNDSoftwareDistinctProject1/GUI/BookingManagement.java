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
    final private List<Booking> bookingList = new ArrayList<>();

    public BookingManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = bookingManagementPanel;
        this.table = bookingManagementTable;

        String[] bookingTableColumns = {"bookingID", "bookingDate"};
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
                    LocalDate.parse(bookingDateInput.getText())
            );

            bookingList.add(booking);

            // add booking
            DefaultTableModel model = (DefaultTableModel) bookingManagementTable.getModel();
            model.addRow(new Object[]{booking.getBookingID(), booking.getBookingDate()});
            bookingDateInput.setText("");
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

    public List<Booking> getBookingList() {
        return bookingList;
    }
}

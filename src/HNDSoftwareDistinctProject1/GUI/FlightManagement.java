package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.*;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FlightManagement extends BaseManagementPanel {
    private JPanel flightManagementPanel;
    private JButton addFlightButton;
    private JTable flightManagementTable;
    private JTextField departureDateTimeInput;
    private JTextField arrivalDateTimeInput;
    private JTextField flightNumberInput;
    private JButton clearFlightsButton;
    private JButton backToMenuButton;
    private JComboBox departureAirportComboBox;
    private JComboBox arrivalAirportComboBox;
    final private String[] airportList = {""};

    public FlightManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = flightManagementPanel;
        this.table = flightManagementTable;

        populateComboBoxes();

        String[] flightTableColumns = {"flightID", "flightNumber", "departureAirport", "arrivalAirport", "departureDateTime", "arrivalDateTime"};
        flightManagementTable.setModel(ManagementController.createModel(flightTableColumns));

        backToMenuButton.addActionListener(e -> switchToMainPanel());
        addPrescription();

    }

    private void addPrescription() {
        addFlightButton.addActionListener(e -> {
            if (!validateInputs()) {
                return;
            }

            // Create a flight ID to input
            String flightID = "FLI-" + UUID.randomUUID().toString().substring(0, 10);

            Flight flight = new Flight(
                    flightID,
                    flightNumberInput.getText(),
                    Objects.requireNonNull(departureAirportComboBox.getSelectedItem()).toString(),
                    Objects.requireNonNull(arrivalAirportComboBox.getSelectedItem()).toString(),
                    LocalDateTime.parse(departureDateTimeInput.getText()),
                    LocalDateTime.parse(arrivalDateTimeInput.getText())
            );
            // add flight to table
            showSuccess("You have successfully added a flight record to the table.");

        });
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getFlightInputValues();

        // Check if any inputs are empty
        if (values[0].toString().isEmpty() || values[1].toString().isEmpty() || values[2].toString().isEmpty() || values[3].toString().isEmpty() || values[4].toString().isEmpty()) {
            showError("Please fill all the fields.");
            return false;
        }

        if (!isObjectLocalDateTime(values[3]) || (!isObjectLocalDateTime(values[4]))) {
            showError("Please follow the correct formatting for departure and arrival dates and times.");
        }

        return true;
    }

    private void populateComboBoxes() {
        for (String airport : airportList) {
            departureAirportComboBox.addItem(airport);
            arrivalAirportComboBox.addItem(airport);
        }
    }

    private Object[] getFlightInputValues() {
        String flightNumber = flightNumberInput.getText();
        String departureAirport = Objects.requireNonNull(departureAirportComboBox.getSelectedItem()).toString();
        String arrivalAirport = Objects.requireNonNull(arrivalAirportComboBox.getSelectedItem()).toString();
        LocalDateTime departureDateTime = LocalDateTime.parse(departureDateTimeInput.getText());
        LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalDateTimeInput.getText());

        return new Object[]{flightNumber, departureAirport, arrivalAirport, departureDateTime, arrivalDateTime};
    }

    public JPanel getFlightManagementPanel() {
        return flightManagementPanel;
    }
}

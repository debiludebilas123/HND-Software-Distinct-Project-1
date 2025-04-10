package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.*;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
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
    private JTextField capacityInput;
    final private String[] airportList = {"IBZ", "GLA", "MMX", "VNO", "SAW", "NAP", "LPL", "ATH", "MAN", "STN", "BFS", "LGW", "BHX", "HEL", "ABZ", "SVQ", "EDI",
            "BRS", "LHR", "ARN", "FRA", "CHQ", "BCN", "LAX", "MUC", "FCO", "BUD", "LSI", "LIS", "DUB"};
    final private List<Flight> flightList = new ArrayList<>();

    public FlightManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = flightManagementPanel;
        this.table = flightManagementTable;

        populateComboBoxes();

        String[] flightTableColumns = {"Flight ID", "Flight Number", "Departure Airport", "Arrival Airport", "Departure Date Time", "Arrival Date Time", "Capacity"};
        flightManagementTable.setModel(ManagementController.createModel(flightTableColumns));

        clearTable();
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            Flight flight = new Flight(
                    flightID,
                    Integer.parseInt(flightNumberInput.getText()),
                    Objects.requireNonNull(departureAirportComboBox.getSelectedItem()).toString(),
                    Objects.requireNonNull(arrivalAirportComboBox.getSelectedItem()).toString(),
                    LocalDateTime.parse(departureDateTimeInput.getText(), formatter),
                    LocalDateTime.parse(arrivalDateTimeInput.getText(), formatter),
                    Integer.parseInt(capacityInput.getText())
            );

            flightList.add(flight);

            // add flight to table
            DefaultTableModel model = (DefaultTableModel) flightManagementTable.getModel();
            model.addRow(new Object[]{flight.getFlightID(), flight.getFlightNumber(), flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDateTime(), flight.getArrivalDateTime(), flight.getCapacity()});
            flightNumberInput.setText("");
            departureDateTimeInput.setText("");
            arrivalDateTimeInput.setText("");
            capacityInput.setText("");
            showSuccess("Flight successfully added!");
        });
    }

    private void clearTable() {
        clearFlightsButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) flightManagementTable.getModel();
            model.setRowCount(0);
            showSuccess("Table successfully cleared!");
        });
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getFlightInputValues();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Check if any inputs are empty
        if (values[0].toString().isEmpty() || values[1].toString().isEmpty() || values[2].toString().isEmpty() || values[3].toString().isEmpty() || values[4].toString().isEmpty()) {
            showError("Please fill all the fields.");
            return false;
        }

        if (!isObjectInteger(values[0])) {
            showError("Please enter a valid flight number!");
            return false;
        }

        try {
            // Parse departure time
            LocalDateTime departure = LocalDateTime.parse(values[3].toString(), formatter);
            // Parse arrival time
            LocalDateTime arrival = LocalDateTime.parse(values[4].toString(), formatter);

            // Additional validation: arrival after departure
            if (arrival.isBefore(departure)) {
                showError("Arrival time must be after departure time");
                return false;
            }

        } catch (DateTimeParseException e) {
            showError("Date/time must be in YYYY-MM-DD HH:MM format\nExample: 2023-12-31 14:30");
            return false;
        }

        if (!isObjectInteger(values[5])) {
            showError("Please enter a number for the capacity!");
            return false;
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
        String departureDateTime = departureDateTimeInput.getText();
        String arrivalDateTime = arrivalDateTimeInput.getText();
        String capacity = capacityInput.getText();

        return new Object[]{flightNumber, departureAirport, arrivalAirport, departureDateTime, arrivalDateTime, capacity};
    }

    public JPanel getFlightManagementPanel() {
        return flightManagementPanel;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }
}

package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.Customer;
import HNDSoftwareDistinctProject1.Services.CSVExport;
import HNDSoftwareDistinctProject1.Services.PanelSwitcher;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class MainMenu {

    private JButton customerManagementButton;
    private JButton bookingManagementButton;
    private JButton flightManagementButton;
    private JButton routeManagementButton;
    private JPanel mainMenuPanel;
    private JButton exitSystemButton;
    private JButton exportToCSVButton;
    private JFrame frame;
    private final CustomerManagement customerManagement;
    private final BookingManagement bookingManagement;
    private final FlightManagement flightManagement;
    private final RouteManagement routeManagement;

    public MainMenu(JFrame frame, CustomerManagement customerManagement, BookingManagement bookingManagement, FlightManagement flightManagement, RouteManagement routeManagement) {
        this.frame = frame;
        this.customerManagement = customerManagement;
        this.bookingManagement = bookingManagement;
        this.flightManagement = flightManagement;
        this.routeManagement = routeManagement;

        setupButtonFunction();
    }

    public void setupButtonFunction() {
        // Triggers for when buttons are pressed
        // Switching panels
        customerManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "CustomerManagement", frame, 750, 600));
        bookingManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "BookingManagement", frame, 620, 450));
        flightManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "FlightManagement", frame, 850, 600));
        routeManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "RouteManagement", frame, 500, 500));

        // Export button
        exportToCSVButton.addActionListener(e -> {
             try {
                 CSVExport.exportToCSV(bookingManagement.getBookingList(), "outputs/bookings.csv");
                 CSVExport.exportToCSV(flightManagement.getFlightList(), "outputs/flights.csv");
                 CSVExport.exportToCSV(routeManagement.getRouteList(), "outputs/routes.csv");
                 CSVExport.exportToCSV(customerManagement.getCustomerList(),"outputs/customers.csv");
             } catch (IOException e1) {
                 JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             }
        });

        // Exit the program
        exitSystemButton.addActionListener(e -> System.exit(0));
    }

    public JPanel getMainMenuPanel() {
        return mainMenuPanel;
    }
}

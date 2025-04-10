package HNDSoftwareDistinctProject1;

import HNDSoftwareDistinctProject1.GUI.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::initializeGUI);
    }

    public static void initializeGUI() {
        JFrame frame = new JFrame("Airline Online Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);

        // Creating class instances for display
        RouteManagement routeManagement = new RouteManagement(frame);
        CustomerManagement customerManagement = new CustomerManagement(frame);
        FlightManagement flightManagement = new FlightManagement(frame, routeManagement);
        BookingManagement bookingManagement = new BookingManagement(frame, customerManagement, flightManagement);
        MainMenu mainMenu = new MainMenu(frame, customerManagement, bookingManagement, flightManagement, routeManagement);

        // Adding panels to the main panel which is connected to the card layout, so I can easily switch between each panel
        panel.add(mainMenu.getMainMenuPanel(), "MainMenu");
        panel.add(customerManagement.getCustomerManagementPanel(), "CustomerManagement");
        panel.add(routeManagement.getRouteManagementPanel(), "RouteManagement");
        panel.add(bookingManagement.getBookingManagementPanel(), "BookingManagement");
        panel.add(flightManagement.getFlightManagementPanel(), "FlightManagement");

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}

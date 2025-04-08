package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Services.PanelSwitcher;

import javax.swing.*;

public class MainMenu {

    private JButton customerManagementButton;
    private JButton bookingManagementButton;
    private JButton flightManagementButton;
    private JButton routeManagementButton;
    private JPanel mainMenuPanel;
    private JButton exitSystemButton;
    private JButton exportToCSVButton;
    private JFrame frame;

    public MainMenu(JFrame frame) {
        this.frame = frame;

        setupButtonFunction();
    }

    public void setupButtonFunction() {
        // Triggers for when buttons are pressed
        // Switching panels
        customerManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "CustomerManagement", frame, 750, 600));
        bookingManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "BookingManagement", frame, 450, 450));
        flightManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "FlightManagement", frame, 570, 600));
        routeManagementButton.addActionListener(e -> PanelSwitcher.switchPanel(mainMenuPanel, "RouteManagement", frame, 500, 500));

        // Exit the program
        exitSystemButton.addActionListener(e -> System.exit(0));
    }

    public JPanel getMainMenuPanel() {
        return mainMenuPanel;
    }
}

package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.*;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import java.util.UUID;

public class RouteManagement extends BaseManagementPanel {
    private JTextField routeNameInput;
    private JButton addRouteButton;
    private JPanel routeManagementPanel;
    private JTable routeManagementTable;
    private JButton clearRoutesButton;
    private JButton backToMenuButton;
    private JButton exportRoutesButton;

    public RouteManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = routeManagementPanel;
        this.table = routeManagementTable;

        String[] routeTableColumns = {"routeID", "routeName"};
        routeManagementTable.setModel(ManagementController.createModel(routeTableColumns));

        backToMenuButton.addActionListener(e -> switchToMainPanel());
        addRoute();
    }

    private void addRoute() {
        addRouteButton.addActionListener(e -> {
            if (!validateInputs()) {
                return;
            }

            // Generate a routeID
            String routeID = "ROU-" + UUID.randomUUID().toString().substring(0, 10);

            // Create a route object to insert into the database
            Route route = new Route(
                    routeID,
                    routeNameInput.getText()
            );
            // add route
            showSuccess("You have successfully added a route to the table.");

        });
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getVisitInputValues();

        if (values[0].toString().isEmpty()) {
            showError("Please fill all the fields.");
            return false;
        }

        return true;
    }

    private Object[] getVisitInputValues() {
        String routeName = routeNameInput.getText();

        return new Object[]{routeName};
    }

    public JPanel getRouteManagementPanel() {
        return routeManagementPanel;
    }
}

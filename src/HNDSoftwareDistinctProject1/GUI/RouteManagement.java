package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.*;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RouteManagement extends BaseManagementPanel {
    private JTextField routeNameInput;
    private JButton addRouteButton;
    private JPanel routeManagementPanel;
    private JTable routeManagementTable;
    private JButton clearRoutesButton;
    private JButton backToMenuButton;
    final private List<Route> routeList = new ArrayList<>();

    public RouteManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = routeManagementPanel;
        this.table = routeManagementTable;

        String[] routeTableColumns = {"routeID", "routeName"};
        routeManagementTable.setModel(ManagementController.createModel(routeTableColumns));

        clearTable();
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

            routeList.add(route);

            // add route
            DefaultTableModel model = (DefaultTableModel) routeManagementTable.getModel();
            model.addRow(new Object[]{route.getRouteID(), route.getRouteName()});
            routeNameInput.setText("");
            showSuccess("Route successfully added!");
        });
    }

    private void clearTable() {
        clearRoutesButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) routeManagementTable.getModel();
            model.setRowCount(0);
            showSuccess("Table successfully cleared!");
        });
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getVisitInputValues();
        values[0] = values[0].toString().toUpperCase().trim();

        if (values[0].toString().isEmpty()) {
            showError("Please fill all the fields.");
            return false;
        }

        if (values[0].toString().length() < 7 || values[0].toString().length() > 7) {
            showError("Please enter a valid route name.");
            return false;
        }

        String firstPart = values[0].toString().substring(0,2);
        String secondPart = values[0].toString().substring(3);
        String thirdPart = values[0].toString().substring(4,6);

        for (int i = 0; i < firstPart.length(); i++) {
            if (firstPart.charAt(i) >= 'A' && firstPart.charAt(i) <= 'Z') {
                continue;
            } else {
                showError("Please follow the specified format!");
                return false;
            }
        }

        if (secondPart.charAt(0) != '-') {
            showError("Please follow the specified format!");
            return false;
        }

        for (int i = 0; i < thirdPart.length(); i++) {
            if (thirdPart.charAt(i) >= 'A' && thirdPart.charAt(i) <= 'Z') {
                continue;
            } else {
                showError("Please follow the specified format!");
                return false;
            }
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

    public List<Route> getRouteList() {
        return routeList;
    }
}

package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.BaseManagementPanel;
import HNDSoftwareDistinctProject1.Models.Customer;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import java.util.UUID;

public class CustomerManagement extends BaseManagementPanel {
    private JButton addCustomerButton;
    private JPanel customerManagementPanel;
    private JTable customerManagementTable;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField emailInput;
    private JButton clearCustomersButton;
    private JButton backToMenuButton;
    private JTextField phoneInput;
    private JTextField addressInput;

    public CustomerManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = customerManagementPanel;
        this.table = customerManagementTable;

        String[] customerTableColumns = {"customerID", "firstName", "lastName", "email", "phone", "address"};
        customerManagementTable.setModel(ManagementController.createModel(customerTableColumns));

        backToMenuButton.addActionListener(e -> switchToMainPanel());
        addCustomerRecord();
    }

    private void addCustomerRecord() {
        addCustomerButton.addActionListener(e -> {
            String customerID = UUID.randomUUID().toString().substring(0, 10);
            if (!validateInputs()) {
                return;
            }

            Customer customer = new Customer(
                    customerID,
                    firstNameInput.getText(),
                    lastNameInput.getText(),
                    emailInput.getText(),
                    phoneInput.getText(),
                    addressInput.getText()
            );
            // Add to table

            showSuccess("Customer added successfully!");
        });
    }

    protected void clearDataTable() {
        customerManagementTable.removeAll();
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getCustomerInputValues();

        // Check if any inputs are empty
        if (values[0].toString().isEmpty() || values[1].toString().isEmpty() || values[2].toString().isEmpty() || values[3].toString().isEmpty() || values[4].toString().isEmpty()) {
            showError("Please fill all the fields.");
            return false;
        }

        if (!isValidEmail(values[2].toString())) {
            showError("Please enter a valid email address.");
            return false;
        }

        if (!isValidPhoneNum(values[3].toString())) {
            showError("Please enter a valid phone number.");
            return false;
        }

        return true;
    }

    private Object[] getCustomerInputValues() {
        // Return the inputs as an object
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        String email = emailInput.getText();
        String phone = phoneInput.getText();
        String address = addressInput.getText();

        return new Object[]{firstName, lastName, email, phone, address};
    }

    public JPanel getCustomerManagementPanel() {
        return customerManagementPanel;
    }
}

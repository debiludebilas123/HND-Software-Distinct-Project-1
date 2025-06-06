package HNDSoftwareDistinctProject1.GUI;

import HNDSoftwareDistinctProject1.Models.BaseManagementPanel;
import HNDSoftwareDistinctProject1.Models.Customer;
import HNDSoftwareDistinctProject1.Services.ManagementController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

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
    private JTextField streetInput;
    private JTextField townInput;
    private JTextField postcodeInput;
    private final List<Customer> customers = new ArrayList<>();

    public CustomerManagement(JFrame frame) {
        super(frame, null, null);
        this.panel = customerManagementPanel;
        this.table = customerManagementTable;

        String[] customerTableColumns = {"Customer ID", "First Name", "Last Name", "Email", "Phone", "Street", "Town", "Postcode"};
        customerManagementTable.setModel(ManagementController.createModel(customerTableColumns));

        clearTable();
        backToMenuButton.addActionListener(e -> switchToMainPanel());
        addCustomerRecord();
    }

    private void addCustomerRecord() {
        addCustomerButton.addActionListener(e -> {
            String customerID = "CUST-"+getCustomerList().size()+1;
            if (!validateInputs()) {
                return;
            }

            Customer customer = new Customer(
                    customerID,
                    firstNameInput.getText().substring(0,1).toUpperCase() + firstNameInput.getText().substring(1),
                    lastNameInput.getText().substring(0,1).toUpperCase() + lastNameInput.getText().substring(1),
                    emailInput.getText(),
                    phoneInput.getText(),
                    streetInput.getText(),
                    townInput.getText(),
                    postcodeInput.getText()
            );

            // Add to table
            DefaultTableModel model = (DefaultTableModel) customerManagementTable.getModel();
            model.addRow(new Object[]{customer.getCustomerID(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhone(), customer.getStreet(), customer.getTown(), customer.getPostcode()});
            firstNameInput.setText("");
            lastNameInput.setText("");
            emailInput.setText("");
            phoneInput.setText("");
            streetInput.setText("");
            townInput.setText("");
            postcodeInput.setText("");
            showSuccess("Customer added successfully!");

            String phoneNum = customer.getPhone().replaceAll(" ", "");
            customer.setPhone(phoneNum);
            customers.add(customer);
        });
    }

    private void clearTable() {
        clearCustomersButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) customerManagementTable.getModel();
            model.setRowCount(0);
            showSuccess("Table successfully cleared!");
        });
    }

    @Override
    protected boolean validateInputs() {
        Object[] values = getCustomerInputValues();

        // Check if any inputs are empty
        if (values[0].toString().isEmpty() || values[1].toString().isEmpty() || values[2].toString().isEmpty() || values[3].toString().isEmpty() || values[4].toString().isEmpty() || values[5].toString().isEmpty() || values[6].toString().isEmpty()) {
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
        String street = streetInput.getText();
        String town = townInput.getText();
        String postcode = postcodeInput.getText();

        return new Object[]{firstName, lastName, email, phone, street, town, postcode};
    }

    public JPanel getCustomerManagementPanel() {
        return customerManagementPanel;
    }

    public List<Customer> getCustomerList() {
        return this.customers;
    }
}

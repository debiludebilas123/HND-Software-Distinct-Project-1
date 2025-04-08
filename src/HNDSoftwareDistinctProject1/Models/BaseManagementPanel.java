package HNDSoftwareDistinctProject1.Models;

import HNDSoftwareDistinctProject1.Services.PanelSwitcher;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseManagementPanel {
    protected JTable table;
    protected JFrame parentFrame;
    protected JPanel panel;

    public BaseManagementPanel(JFrame parentFrame, JPanel panel, JTable table) {
        this.parentFrame = parentFrame;
        this.panel = panel;
        this.table = table;
    }

    // Mandatory method for all management panels to validate inputs
    protected abstract boolean validateInputs();

    protected void clearDataTable() {

    }

    protected boolean isObjectInteger(Object o) {
        // Tries parsing the object o as an int and if it pops an error out that means it isn't and vice versa
        try {
            Integer.parseInt(o.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean isObjectLocalDate(Object o) {
        // Tries parsing the object o as a local date and if it pops an error out that means it isn't and vice versa
        try {
            LocalDate.parse(o.toString());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    protected boolean isObjectLocalDateTime(Object o) {
        // Tries parsing the object o as a LocalDateTime and if it pops an error out that means it isn't and vice versa
        try {
            LocalDateTime.parse(o.toString());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    protected boolean isValidPhoneNum(String phoneNumber) {
        // Uses a regex to compare the given phone number to the correct format (+441234567890)
        String regex = "^\\+44\\s?\\d{4}\\s?\\d{6}$";  // Regex for UK phone numbers
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    protected boolean isValidEmail(String email) {
        // Uses a regex to compare the given email to the correct format (thingy@gmail.com)
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }

    protected void switchToMainPanel() {
        PanelSwitcher.switchPanel(panel, "MainMenu", parentFrame, 500, 370);
    }

    protected void showError(String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    protected void showSuccess(String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void showInfo(String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

}
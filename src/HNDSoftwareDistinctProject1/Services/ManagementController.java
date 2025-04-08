package HNDSoftwareDistinctProject1.Services;

import javax.swing.table.DefaultTableModel;

public class ManagementController {
    public static DefaultTableModel createModel(String[] columns) {
        // Non-editable model with predefined columns
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing cells
            }
        };
    }

}

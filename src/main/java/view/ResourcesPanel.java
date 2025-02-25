package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ResourcesPanel {
    private JTable dataTable;
    private JScrollPane dataScroll;
    private Object[][] data;
    private DefaultTableModel tableModel;

    /**
     * Constructor que inicializa la tabla de recursos y su modelo de datos.
     */
    public ResourcesPanel() {
        String[] columnNames = {
                "Resource ID", "Current QTY", "Min QTY", "Max QTY",
                "Producers", "Consumers", "Underflow Count", "Overflow Count"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        dataScroll = new JScrollPane(dataTable);
    }

    /**
     * Actualiza la tabla con la información de los recursos.
     *
     * @param resourceData Lista de datos de los recursos a mostrar en la tabla.
     */
    public void updateTable(List<Integer[]> resourceData) {
        tableModel.setRowCount(0);
        for (Integer[] data : resourceData) {
            tableModel.addRow(data);
        }
    }


    public JScrollPane getDataScroll() {
        return dataScroll;
    }

}

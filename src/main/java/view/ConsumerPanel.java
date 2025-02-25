package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ConsumerPanel {
    private JTable dataTable;
    private JScrollPane dataScroll;
    private Object[][] data;
    private DefaultTableModel tableModel;

    /**
     * Constructor que inicializa la tabla de consumidores y su modelo de datos.
     */
    public ConsumerPanel() {
        String[] columnNames = {
                "Thread ID", "Bound Resource", "Start Delay", "Delay", "Times",
                "Processing Time", "Start Time", "End Time"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        dataScroll = new JScrollPane(dataTable);
    }

    /**
     * Actualiza la tabla con la información de los consumidores.
     *
     * @param consumerData Lista de datos de los consumidores a mostrar en la tabla.
     */
    public void updateTable(List<Integer[]> consumerData) {
        tableModel.setRowCount(0);
        for (Integer[] data : consumerData) {
            tableModel.addRow(data);
        }
    }

    public JScrollPane getDataScroll() {
        return dataScroll;
    }

}

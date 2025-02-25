package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProducerPanel {
    private JTable dataTable;
    private JScrollPane dataScroll;
    private Object[][] data;
    private DefaultTableModel tableModel;

    /**
     * Constructor que inicializa la tabla de productores y su modelo de datos.
     */
    public ProducerPanel() {
        String[] columnNames = {
                "Thread ID", "Bound Resource", "Start Delay", "Delay", "Times",
                "Processing Time", "Start Time", "End Time"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        dataScroll = new JScrollPane(dataTable);
    }

    /**
     * Actualiza la tabla con la información de los productores.
     *
     * @param producerData Lista de datos de los productores a mostrar en la tabla.
     */
    public void updateTable(List<Integer[]> producerData) {
        tableModel.setRowCount(0);
        for (Integer[] data : producerData) {
            tableModel.addRow(data);
        }
    }

    public JScrollPane getDataScroll() {
        return dataScroll;
    }

}

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ResourcesPanel {
    private JTable dataTable;
    private JScrollPane dataScroll;
    private Object[][] data;
    private DefaultTableModel tableModel;

    public ResourcesPanel() {
        // Definir las columnas de la tabla
        String[] columnNames = {
                "Resource ID", "Current QTY", "Min QTY", "Max QTY",
                "Producers", "Consumers", "Underflow Count", "Overflow Count"
        };

        // Inicializar el modelo de la tabla con 0 filas iniciales
        tableModel = new DefaultTableModel(columnNames, 0);

        // Configurar la JTable con el modelo
        dataTable = new JTable(tableModel);

        // Agregar la tabla a un JScrollPane
        dataScroll = new JScrollPane(dataTable);
    }

    public void updateTable(List<Integer[]> resourceData) {
        tableModel.setRowCount(0); // Limpiar la tabla antes de actualizarla
        for (Integer[] data : resourceData) {
            tableModel.addRow(data); // Agregar cada recurso como una fila
        }
    }

    public JTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(JTable dataTable) {
        this.dataTable = dataTable;
    }

    public JScrollPane getDataScroll() {
        return dataScroll;
    }

    public void setDataScroll(JScrollPane dataScroll) {
        this.dataScroll = dataScroll;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
}

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConsumerPanel {
    private JTable dataTable;
    private JScrollPane dataScroll;
    private Object[][] data;
    private DefaultTableModel tableModel;

    public ConsumerPanel() {
        String[] columnNames = {"Atributo","Valor"};

        // Inicializar el modelo con las columnas y datos iniciales
        tableModel = new DefaultTableModel(columnNames, 0); // 0 filas iniciales

        // Configurar la JTable con el modelo
        dataTable = new JTable(tableModel);

        // Agregar la tabla a un JScrollPane
        dataScroll = new JScrollPane(dataTable);
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

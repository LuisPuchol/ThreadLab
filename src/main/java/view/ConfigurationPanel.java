package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConfigurationPanel {
    private JTable configurationTable;
    private JScrollPane configurationScroll;
    private Object[][] data;
    private DefaultTableModel tableModel;

    public ConfigurationPanel() {
        String[] columnNames = {"Atributo","Valor"};

        // Inicializar el modelo con las columnas y datos iniciales
        tableModel = new DefaultTableModel(columnNames, 0); // 0 filas iniciales
        tableModel.addRow(new Object[]{"X"}); // Agrega la fila inicial

        // Configurar la JTable con el modelo
        configurationTable = new JTable(tableModel);

        // Agregar la tabla a un JScrollPane
        configurationScroll = new JScrollPane(configurationTable);

    }

    public JTable getConfigurationTable() {
        return configurationTable;
    }

    public void setConfigurationTable(JTable configurationTable) {
        this.configurationTable = configurationTable;
    }

    public JScrollPane getConfigurationScroll() {
        return configurationScroll;
    }

    public void setConfigurationScroll(JScrollPane configurationScroll) {
        this.configurationScroll = configurationScroll;
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

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public DataPanel() {
        setLayout(new BorderLayout());

        // Columnas de la tabla
        String[] columnNames = {"Data", "Value"};

        // Datos iniciales
        Object[][] data = {
                {"Total Resources", 0},
                {"Total Producers", 0},
                {"Total Consumers", 0},
                {"Total resource quantity", 0},
                {"Active Threads", 0},
                {"Total Processing time ...", 0},
                {"IDLE Threads", 0}
        };

        // Modelo de la tabla
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Ninguna celda es editable
            }
        };

        // Crear la JTable
        table = new JTable(model);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        // Estilo de cabecera
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateData(int row, Object value) {
        model.setValueAt(value, row, 1);
    }
    public JScrollPane getDataScroll() {
        return (JScrollPane) this.getComponent(0);
    }

    public void updateValues(Object[] newValues) {
        if (newValues.length != model.getRowCount()) {
            throw new IllegalArgumentException("El tamaño de los datos no coincide con las filas de la tabla.");
        }

        for (int i = 0; i < newValues.length; i++) {
            model.setValueAt(newValues[i], i, 1);
        }
    }


}

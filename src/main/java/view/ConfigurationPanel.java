package view;

import model.ConfigurationPropertiesDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JScrollPane scrollPane;
    private JTable jTable;
    private DefaultTableModel model;

    public ConfigurationPanel() {
        setLayout(new BorderLayout());

        // Datos iniciales de la tabla
        Object[][] data = {
                {".Resource Type Settings", "---"},
                {"Total Resources", 5},
                {"Max General Resources", 1000},
                {"Min General Resources", 100},
                {".Producer/Consumer Count", "---"},
                {"Number of Producers", 5},
                {"Number of Consumers", 5},
                {".Start Delay Settings", "---"},
                {"Start Delay Min (ms)", 100},
                {"Start Delay Max (ms)", 200},
                {".Producer Timing", "---"},
                {"Producer Delay Min", 10},
                {"Producer Delay Max", 100},
                {".Consumer Timing", "---"},
                {"Consumer Delay Min", 10},
                {"Consumer Delay Max", 100}
        };

        // Columnas de la tabla
        String[] columnNames = {"Parameter", "Value"};

        // Modelo de la tabla
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Solo la columna "Value" es editable
            }
        };

        // Crear la JTable
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        // Agregar la tabla a un JScrollPane
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JScrollPane getConfigurationScroll() {
        return scrollPane;
    }

    public ConfigurationPropertiesDTO toConfigurationPropertiesDTO() {
        ConfigurationPropertiesDTO configDTO = new ConfigurationPropertiesDTO();

        for (int i = 0; i < model.getRowCount(); i++) {
            String key = (String) model.getValueAt(i, 0); // Nombre del parámetro
            Object valueObj = model.getValueAt(i, 1); // Puede ser Integer o String

            // Omitir encabezados que no son configuraciones reales
            if (key.startsWith(".")) {
                continue;
            }

            if (valueObj == null) {
                continue; // Evita valores nulos
            }

            String valueStr = valueObj.toString().trim(); // Convertir a String seguro

            if (valueStr.isEmpty()) {
                continue; // Evita valores vacíos
            }

            int value;
            try {
                value = Integer.parseInt(valueStr);
            } catch (NumberFormatException e) {
                System.err.println("Error: Valor no numérico en " + key);
                continue; // Ignora valores inválidos
            }

            // Asignamos valores al DTO según la clave
            switch (key) {
                case "Total Resources":
                    configDTO.setTotalResources(value);
                    break;
                case "Max General Resources":
                    configDTO.setMaxGeneralResources(value);
                    break;
                case "Min General Resources":
                    configDTO.setMinGeneralResources(value);
                    break;
                case "Number of Producers":
                    configDTO.setNumberOfProducers(value);
                    break;
                case "Number of Consumers":
                    configDTO.setNumberOfConsumers(value);
                    break;
                case "Start Delay Min (ms)":
                    configDTO.setStartDelayMin(value);
                    break;
                case "Start Delay Max (ms)":
                    configDTO.setStartDelayMax(value);
                    break;
                case "Producer Delay Min":
                    configDTO.setProducerDelayMin(value);
                    break;
                case "Producer Delay Max":
                    configDTO.setProducerDelayMax(value);
                    break;
                case "Consumer Delay Min":
                    configDTO.setConsumerDelayMin(value);
                    break;
                case "Consumer Delay Max":
                    configDTO.setConsumerDelayMax(value);
                    break;
            }
        }

        return configDTO;
    }


}


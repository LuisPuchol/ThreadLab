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
        initializeTable();
        configureTable();
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Inicializa la tabla de configuración con los parámetros predeterminados.
     */
    private void initializeTable() {
        Object[][] data = {
                {".Resource Type Settings", "---"},
                {"Total Resources", 5},
                {"Min General Resources", 100},
                {"Max General Resources", 1000},
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

        String[] columnNames = {"Parameter", "Value"};

        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; //Editable solo la columna de la izquierda
            }
        };
    }

    /**
     * Configura la tabla y su presentación visual dentro de un JScrollPane.
     */
    private void configureTable() {
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        scrollPane = new JScrollPane(table);
    }

    public JScrollPane getConfigurationScroll() {
        return scrollPane;
    }

    /**
     * Convierte los valores de la tabla en un objeto {@link ConfigurationPropertiesDTO}.
     * Solo se consideran las filas que representan configuraciones numéricas válidas.
     *
     * @return Un objeto {@link ConfigurationPropertiesDTO} con los valores extraídos de la tabla.
     */
    public ConfigurationPropertiesDTO toConfigurationPropertiesDTO() {
        ConfigurationPropertiesDTO configDTO = new ConfigurationPropertiesDTO();

        for (int i = 0; i < model.getRowCount(); i++) {
            String key = getParameterName(i);
            Integer value = getParameterValue(i);

            if (key == null || value == null) continue;

            mapValueToDTO(configDTO, key, value);
        }

        return configDTO;
    }

    /**
     * Obtiene el nombre del parámetro de la fila especificada.
     *
     * @param row Índice de la fila en la tabla.
     * @return Nombre del parámetro o null si es un encabezado.
     */
    private String getParameterName(int row) {
        String key = (String) model.getValueAt(row, 0);
        return (key != null && !key.startsWith(".")) ? key : null;
    }

    /**
     * Obtiene el valor numérico de la fila especificada.
     *
     * @param row Índice de la fila en la tabla.
     * @return El valor convertido a Integer o null si es inválido.
     */
    private Integer getParameterValue(int row) {
        Object valueObj = model.getValueAt(row, 1);
        if (valueObj == null) return null;

        String valueStr = valueObj.toString().trim();
        if (valueStr.isEmpty()) return null;

        try {
            return Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            System.err.println("Error: Valor no numérico en " + valueStr);
            return null;
        }
    }

    /**
     * Asigna un valor extraído de la tabla a la propiedad correspondiente en {@link ConfigurationPropertiesDTO}.
     *
     * @param configDTO Objeto de configuración donde se almacenarán los valores.
     * @param key Nombre del parámetro.
     * @param value Valor numérico asociado al parámetro.
     */
    private void mapValueToDTO(ConfigurationPropertiesDTO configDTO, String key, int value) {
        switch (key) {
            case "Total Resources" -> configDTO.setTotalResources(value);
            case "Min General Resources" -> configDTO.setMinGeneralResources(value);
            case "Max General Resources" -> configDTO.setMaxGeneralResources(value);
            case "Number of Producers" -> configDTO.setNumberOfProducers(value);
            case "Number of Consumers" -> configDTO.setNumberOfConsumers(value);
            case "Start Delay Min (ms)" -> configDTO.setStartDelayMin(value);
            case "Start Delay Max (ms)" -> configDTO.setStartDelayMax(value);
            case "Producer Delay Min" -> configDTO.setProducerDelayMin(value);
            case "Producer Delay Max" -> configDTO.setProducerDelayMax(value);
            case "Consumer Delay Min" -> configDTO.setConsumerDelayMin(value);
            case "Consumer Delay Max" -> configDTO.setConsumerDelayMax(value);
        }
    }

    /*
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
                case "Min General Resources":
                    configDTO.setMinGeneralResources(value);
                    break;
                case "Max General Resources":
                    configDTO.setMaxGeneralResources(value);
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
     */


}


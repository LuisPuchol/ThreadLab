package view;

import controller.Controller;

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.*;

public class View extends JFrame implements ActionListener, Runnable {
    private ControlPanel controlPanel;
    private Controller controller;
    private DataPanel dataPanel;
    private ConfigurationPanel configurationPanel;
    private ProducerPanel producerPanel;
    private ConsumerPanel consumerPanel;
    private ResourcesPanel resourcesPanel;
    private Thread updateThread;

    // Colores para la UI
    private static final Color EVEN_ROW_COLOR = Color.WHITE;
    private static final Color ODD_ROW_COLOR = new Color(240, 240, 245);
    private static final Color HEADER_BG_COLOR = new Color(66, 232, 79);
    private static final Color HEADER_FG_COLOR = Color.WHITE;

    public View(Controller controller) {
        System.out.println("view.MyView creado");
        this.controller = controller;

        createFrame();
        setupListeners();

        updateThread = new Thread(this);
        updateThread.start();
    }

    private void createFrame() {
        setTitle("Resource Producer-Consumer Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        initializePanels();
        setupTableStyles();
        setupLayout();
        addControlButtons();

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializePanels() {
        controlPanel = new ControlPanel();
        dataPanel = new DataPanel();
        configurationPanel = new ConfigurationPanel();
        producerPanel = new ProducerPanel();
        consumerPanel = new ConsumerPanel();
        resourcesPanel = new ResourcesPanel();
    }

    private void setupTableStyles() {
        // Configuración global de la UI para tablas
        try {
            UIManager.put("Table.alternateRowColor", ODD_ROW_COLOR);
            UIManager.put("Table.showGrid", true);
            UIManager.put("Table.gridColor", new Color(220, 220, 220));

            // Aplicar renderizadores a todas las tablas
            applyTableStyles(dataPanel.getDataScroll());
            applyTableStyles(configurationPanel.getConfigurationScroll());
            applyTableStyles(producerPanel.getDataScroll());
            applyTableStyles(resourcesPanel.getDataScroll());
            applyTableStyles(consumerPanel.getDataScroll());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyTableStyles(JScrollPane scrollPane) {
        Component view = scrollPane.getViewport().getView();

        JTable table = (JTable) view;

        headerStyle(table);
        laneSwapStyle(table);

        table.setShowGrid(true);
        table.setGridColor(new Color(69, 182, 44));
    }

    private static void laneSwapStyle(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? EVEN_ROW_COLOR : ODD_ROW_COLOR);
                }
                return c;
            }
        });
    }

    private static void headerStyle(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(HEADER_BG_COLOR);
        header.setForeground(HEADER_FG_COLOR);
        header.setFont(header.getFont().deriveFont(Font.BOLD));
    }

    private void setupLayout() {
        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.weighty = 1.0;

        constraints.gridx = 0;
        constraints.weightx = 0.4;
        add(leftPanel, constraints);

        constraints.gridx = 1;
        constraints.weightx = 0.6;
        add(rightPanel, constraints);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        addSectionWithTitle(panel, "Datos", dataPanel.getDataScroll(), 0, 0, 0.5);
        addSectionWithTitle(panel, "Configuración", configurationPanel.getConfigurationScroll(), 1, 0, 0.5);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        addSectionWithTitle(panel, "Productor", producerPanel.getDataScroll(), 0, 0, 1.0);
        addSectionWithTitle(panel, "Recursos", resourcesPanel.getDataScroll(), 0, 2, 1.0);
        addSectionWithTitle(panel, "Consumidor", consumerPanel.getDataScroll(), 0, 4, 1.0);

        return panel;
    }

    private void addSectionWithTitle(JPanel panel, String title, JComponent component,
                                     int gridX, int gridY, double weightX) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = gridX;
        c.weightx = weightX;

        //titulo primero
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        c.gridy = gridY;
        c.weighty = 0.0; //no se expande
        panel.add(titleLabel, c);

        //sumamos uno para que se ponga abajo
        c.gridy = gridY + 1;
        c.weighty = 0.33; //se expande
        panel.add(component, c);
    }


    private void addControlButtons() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 0;
        c.insets = new Insets(5, 5, 5, 5);

        add(controlPanel.getButtonPanel(), c);
    }

    public void setupListeners() {
        controlPanel.getButtonPlay().addActionListener(this);
        controlPanel.getButtonStop().addActionListener(this);
        controlPanel.getButtonPause().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "play":
                controller.handlePlayButton();
                break;
            case "stop":
                controller.handleStopButton();
                break;
            case "pause":
                controller.handlePauseButton();
                break;
            default:
                throw new IllegalArgumentException("Comando no reconocido: " + command);
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                //Recibir info
                Object[] updatedData = controller.getCurrentData();
                List<Integer[]> resourcesData = controller.getResourcesData();
                List<Integer[]> consumerData = controller.getConsumerData();
                List<Integer[]> producerData = controller.getProducerData();

                SwingUtilities.invokeLater(() -> {
                    dataPanel.updateValues(updatedData);
                    resourcesPanel.updateTable(resourcesData);
                    consumerPanel.updateTable(consumerData);
                    producerPanel.updateTable(producerData);
                });

                System.out.println("bucle en marcha");

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public ConfigurationPanel getConfigurationPanel() {
        return configurationPanel;
    }

}

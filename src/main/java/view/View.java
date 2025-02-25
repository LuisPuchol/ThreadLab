package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class View extends JFrame implements ActionListener, Runnable {
    private ControlPanel controlPanel;
    private Controller controller;
    private DataPanel dataPanel;
    private ConfigurationPanel configurationPanel;
    private ProducerPanel producerPanel;
    private ConsumerPanel consumerPanel;
    private ResourcesPanel resourcesPanel;
    private Thread updateThread;

    public View(Controller controller) {
        System.out.println("view.MyView creado");
        this.controller = controller;

        createFrame();
        setupListeners();

        updateThread = new Thread(this);
        updateThread.start();
    }

    /**
     * Crea y configura el marco principal de la aplicación con un diseño GridBagLayout.
     * Se incluyen los paneles de datos, configuración, productores, consumidores y recursos,
     * junto con los controles de la simulación.
     */
    private void createFrame() {
        JFrame frame = new JFrame("My Task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        initializePanels();
        addTitles(frame);
        addTables(frame);
        addControlButtons(frame);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Inicializa los paneles de datos, configuración, productores, consumidores y recursos.
     */
    private void initializePanels() {
        controlPanel = new ControlPanel();
        dataPanel = new DataPanel();
        configurationPanel = new ConfigurationPanel();
        producerPanel = new ProducerPanel();
        consumerPanel = new ConsumerPanel();
        resourcesPanel = new ResourcesPanel();
    }

    /**
     * Agrega los títulos de las secciones en la parte superior del marco.
     */
    private void addTitles(JFrame frame) {
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridy = 0;
        titleConstraints.gridwidth = 1;
        titleConstraints.gridheight = 1;
        titleConstraints.fill = GridBagConstraints.NONE;
        titleConstraints.weightx = 0.2;
        titleConstraints.weighty = 0;

        String[] titles = {"Datos", "Configuración", "Productor", "Recursos", "Consumidor"};
        for (int i = 0; i < titles.length; i++) {
            titleConstraints.gridx = i;
            frame.add(new JLabel(titles[i]), titleConstraints);
        }
    }

    /**
     * Agrega las tablas de los paneles de datos, configuración, productores, consumidores y recursos.
     */
    private void addTables(JFrame frame) {
        GridBagConstraints tableConstraints = new GridBagConstraints();
        tableConstraints.gridy = 1;
        tableConstraints.gridwidth = 1;
        tableConstraints.gridheight = 1;
        tableConstraints.fill = GridBagConstraints.BOTH;
        tableConstraints.weightx = 0.2;
        tableConstraints.weighty = 1;

        JScrollPane[] panels = {
                dataPanel.getDataScroll(),
                configurationPanel.getConfigurationScroll(),
                producerPanel.getDataScroll(),
                resourcesPanel.getDataScroll(),
                consumerPanel.getDataScroll()
        };

        for (int i = 0; i < panels.length; i++) {
            tableConstraints.gridx = i;
            frame.add(panels[i], tableConstraints);
        }
    }

    /**
     * Agrega los botones de control de la simulación en la parte inferior del marco.
     */
    private void addControlButtons(JFrame frame) {
        GridBagConstraints buttonsConstraints = new GridBagConstraints();
        buttonsConstraints.gridx = 0;
        buttonsConstraints.gridy = 3;
        buttonsConstraints.gridwidth = 2;
        buttonsConstraints.gridheight = 1;
        buttonsConstraints.fill = GridBagConstraints.NONE;
        buttonsConstraints.weightx = 0;
        buttonsConstraints.weighty = 0;

        frame.add(controlPanel.getButtonPanel(), buttonsConstraints);
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
                Object[] updatedData  = controller.getCurrentData();
                List<Integer[]> resourcesData = controller.getResourcesData();
                List<Integer[]> consumerData = controller.getConsumerData();
                List<Integer[]> producerData = controller.getProducerData();

                //if (updatedData != null) {
                    SwingUtilities.invokeLater(() -> {
                        dataPanel.updateValues(updatedData);
                        resourcesPanel.updateTable(resourcesData);
                        consumerPanel.updateTable(consumerData);
                        producerPanel.updateTable(producerData);
                    });
                //}

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

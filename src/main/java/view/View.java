package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener, Runnable {
    private ControlPanel controlPanel;
    //private Viewer viewers;
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

        //Thread en bucle para el estado de la QTT del Resource
        updateThread = new Thread(this);
        updateThread.start();
    }

    private void createFrame() {
        controlPanel = new ControlPanel();
        dataPanel = new DataPanel();
        configurationPanel = new ConfigurationPanel();
        producerPanel = new ProducerPanel();
        consumerPanel = new ConsumerPanel();
        resourcesPanel = new ResourcesPanel();


        JFrame frame = new JFrame("My Task");
        GridBagConstraints tableConstraints = new GridBagConstraints();
        GridBagConstraints titleConstraints = new GridBagConstraints();
        GridBagConstraints buttonsConstraints = new GridBagConstraints();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JLabel titleData = new JLabel("Datos");
        JLabel titleConfiguration = new JLabel("Configuración");
        JLabel titleProducer = new JLabel("Productor");
        JLabel titleResources = new JLabel("Recursos");
        JLabel titleConsumer = new JLabel("Consumidor");

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.gridwidth = 1;
        titleConstraints.gridheight = 1;
        titleConstraints.fill = GridBagConstraints.NONE;  // Expande horizontal y verticalmente
        titleConstraints.weightx = 0.2;  // Asigna un 20% del espacio horizontal
        titleConstraints.weighty = 0;
        frame.add(titleData,titleConstraints);

        titleConstraints.gridx = 1;
        frame.add(titleConfiguration,titleConstraints);

        titleConstraints.gridx = 4;
        frame.add(titleProducer, titleConstraints);

        titleConstraints.gridx = 3;
        frame.add(titleResources, titleConstraints);

        titleConstraints.gridx = 2;
        frame.add(titleConsumer, titleConstraints);


        tableConstraints.gridx = 0;
        tableConstraints.gridy = 1;
        tableConstraints.gridwidth = 1;
        tableConstraints.gridheight = 1;  // Ocupa dos filas
        tableConstraints.fill = GridBagConstraints.BOTH;  // Expande horizontal y verticalmente
        tableConstraints.weightx = 0.2;  // Asigna un 20% del espacio horizontal
        tableConstraints.weighty = 1;  // Asigna el 100% del espacio vertical
        frame.add(dataPanel.getDataScroll(), tableConstraints);

        tableConstraints.gridx = 1;
        frame.add(configurationPanel.getConfigurationScroll(), tableConstraints);

        tableConstraints.gridx = 2;
        frame.add(producerPanel.getDataScroll(), tableConstraints);

        tableConstraints.gridx = 3;
        frame.add(resourcesPanel.getDataScroll(), tableConstraints);

        tableConstraints.gridx = 4;
        frame.add(consumerPanel.getDataScroll(), tableConstraints);

        buttonsConstraints.gridx = 0;  // Comienza en la columna 0
        buttonsConstraints.gridy = 3;  // Coloca los botones en la fila 1
        buttonsConstraints.gridwidth = 2;  // Los botones ocupan 1 columnas
        buttonsConstraints.gridheight = 1;  // Los botones ocupan una sola fila
        buttonsConstraints.fill = GridBagConstraints.NONE;  // Los botones no deben expandirse
        buttonsConstraints.weightx = 0;  // No hay espacio adicional horizontal
        buttonsConstraints.weighty = 0;  // No hay espacio adicional vertical
        frame.add(controlPanel.getButtonPanel(), buttonsConstraints);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }

    public void setupListeners() {
        controlPanel.getButtonOK().addActionListener(this);
        controlPanel.getButtonCancel().addActionListener(this);
        controlPanel.getButtonCanva().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "play":
                controller.handleCounterButton();
                break;
            case "stop":
                controller.handleCancelButton();
                break;
            case "canva":
                break;
            default:
                throw new IllegalArgumentException("Comando no reconocido: " + command);
        }
    }

    public ControlPanel getControls() {
        return controlPanel;
    }

    public void setControls(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    @Override
    public void run() {
        while (true) {  // Bucle infinito
            try {
                // Obtener la información actualizada
                Object[] newData = controller.getUpdatedData();  // Método ficticio que obtiene los datos

                // Actualizar la tabla en el hilo de la interfaz gráfica
                if (newData != null) {
                    SwingUtilities.invokeLater(() -> {
                        dataPanel.addRow(newData);  // Añadir una nueva fila a la tabla
                    });
                }


                System.out.println("bucle en marcha");

                // Esperar 1 segundo antes de la siguiente actualización
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;  // Salir del bucle si el hilo es interrumpido
            }
        }
    }
}

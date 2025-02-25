package model;

import controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private Controller controller;
    private List<Producer> producers;
    private List<Consumer> consumers;
    private List<ResourceType> resourceTypes;

    private int totalResources;
    private int maxGeneralResources;
    private int minGeneralResources;
    private int numberOfProducers;
    private int numberOfConsumers;
    private int startDelayMin;
    private int startDelayMax;
    private int producerDelayMin;
    private int producerDelayMax;
    private int consumerDelayMin;
    private int consumerDelayMax;

    private Random random = new Random();

    public Model(Controller controller) {
        this.controller = controller;
        producers = new ArrayList<>();
        consumers = new ArrayList<>();
        resourceTypes = new ArrayList<>();
    }

    /**
     * Configura el modelo basándose en los valores del DTO.
     */
    public void applyConfiguration(ConfigurationPropertiesDTO configDTO) {
        this.totalResources = configDTO.getTotalResources();
        this.maxGeneralResources = configDTO.getMaxGeneralResources();
        this.minGeneralResources = configDTO.getMinGeneralResources();
        this.numberOfProducers = configDTO.getNumberOfProducers();
        this.numberOfConsumers = configDTO.getNumberOfConsumers();
        this.startDelayMin = configDTO.getStartDelayMin();
        this.startDelayMax = configDTO.getStartDelayMax();
        this.producerDelayMin = configDTO.getProducerDelayMin();
        this.producerDelayMax = configDTO.getProducerDelayMax();
        this.consumerDelayMin = configDTO.getConsumerDelayMin();
        this.consumerDelayMax = configDTO.getConsumerDelayMax();
    }

    /**
     * Inicializa los recursos compartidos y crea los hilos de productores y consumidores.
     */
    public void play() {
        resourceTypes = new ArrayList<>();
        ResourceType.resetIdCounter();
        producers = new ArrayList<>();
        consumers = new ArrayList<>();


        // Crear los ResourceType según totalResources
        for (int i = 0; i < totalResources; i++) {
            resourceTypes.add(new ResourceType(minGeneralResources, maxGeneralResources));
        }

        // Calcular delays promediados al crear cada productor y consumidor
        int avgProducerDelay = (producerDelayMin + producerDelayMax) / 2;
        int avgConsumerDelay = (consumerDelayMin + consumerDelayMax) / 2;

        // Crear y lanzar productores con un delay inicial aleatorio
        for (int i = 0; i < numberOfProducers; i++) {
            ResourceType assignedResource = resourceTypes.get(random.nextInt(resourceTypes.size()));
            int startDelay = startDelayMin + random.nextInt(startDelayMax - startDelayMin + 1);
            Producer producer = new Producer(this, assignedResource, avgProducerDelay, startDelay);
            assignedResource.addProducer();
            producers.add(producer);
            producer.start();
        }

        // Crear y lanzar consumidores con un delay inicial aleatorio
        for (int i = 0; i < numberOfConsumers; i++) {
            ResourceType assignedResource = resourceTypes.get(random.nextInt(resourceTypes.size()));
            int startDelay = startDelayMin + random.nextInt(startDelayMax - startDelayMin + 1);
            Consumer consumer = new Consumer(this, assignedResource, avgConsumerDelay, startDelay);
            consumers.add(consumer);
            assignedResource.addConsumer();
            consumer.start();
        }
    }

    /**
     * Devuelve el estado actual del modelo para la vista.
     */
    public Object[] getCurrentData() {
        return new Object[]{
                totalResources,
                numberOfProducers,
                numberOfConsumers,
                getTotalResourceQuantity(),
                getActiveThreads(),
                getProcessingTime(),
                getIdleThreads()
        };
    }

    public void stop() {
        System.out.println("Deteniendo todos los procesos...");

        // Interrumpir todos los productores
        for (Producer producer : producers) {
            producer.interrupt();
        }

        // Interrumpir todos los consumidores
        for (Consumer consumer : consumers) {
            consumer.interrupt();
        }

        System.out.println("Todos los procesos han sido detenidos.");
    }


    /**
     * Métodos auxiliares para obtener estadísticas.
     */
    public int getTotalResourceQuantity() {
        return resourceTypes.stream().mapToInt(ResourceType::getCurrentQuantity).sum();
    }

    public List<Integer[]> getResourceTypeInfo() {
        List<Integer[]> resourceInfo = new ArrayList<>();
        for (ResourceType resource : resourceTypes) {
            resourceInfo.add(resource.getResourceInfo());
        }
        return resourceInfo;
    }


    public int getActiveThreads() {
        return (int) (Thread.activeCount() - 4); // -4, 3 del Swing y 1 del propio main
    }

    public int getProcessingTime() {
        // cuando se de a stop calcular tiempo
        return 0;
    }

    public int getIdleThreads() {
        return numberOfProducers + numberOfConsumers - getActiveThreads();
    }
}

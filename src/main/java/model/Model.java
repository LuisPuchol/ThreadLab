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
        // Resetear las ArrayList
        resourceTypes = new ArrayList<>();
        ResourceType.resetIdCounter();
        producers = new ArrayList<>();
        consumers = new ArrayList<>();


        // ResourceTypes
        for (int i = 0; i < totalResources; i++) {
            resourceTypes.add(new ResourceType(minGeneralResources, maxGeneralResources));
        }


        // Producers
        for (int i = 0; i < numberOfProducers; i++) {
            ResourceType assignedResource = resourceTypes.get(random.nextInt(resourceTypes.size()));
            int startDelay = startDelayMin + random.nextInt(startDelayMax - startDelayMin + 1);
            int produceDelay = producerDelayMin + random.nextInt(producerDelayMax - producerDelayMin + 1);
            Producer producer = new Producer(this, assignedResource, produceDelay, startDelay);
            assignedResource.addProducer();
            producers.add(producer);
            producer.start();
        }

        // Consumers
        for (int i = 0; i < numberOfConsumers; i++) {
            ResourceType assignedResource = resourceTypes.get(random.nextInt(resourceTypes.size()));
            int startDelay = startDelayMin + random.nextInt(startDelayMax - startDelayMin + 1);
            int consumeDelay = consumerDelayMin + random.nextInt(consumerDelayMax - consumerDelayMin + 1);
            Consumer consumer = new Consumer(this, assignedResource, consumeDelay, startDelay);
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

    /**
     * Si estan running los pausa, si estan en pausa los reanuda
     */
    public void pause() {
        if (!producers.isEmpty() && producers.get(0).getThreadState() == ThreadState.RUNNING) {
            for (Producer producer : producers) {
                producer.setState(ThreadState.PAUSED);
            }
            for (Consumer consumer : consumers) {
                consumer.setState(ThreadState.PAUSED);
            }
            System.out.println("Simulación pausada.");
        } else {
            for (Producer producer : producers) {
                producer.setState(ThreadState.RUNNING);
            }
            for (Consumer consumer : consumers) {
                consumer.setState(ThreadState.RUNNING);
            }
            System.out.println("Simulación reanudada.");
        }
    }

    /**
     * Pausa la simulación, pero no la reinicia, eso se hace al principio del start
     */
    public void stop() {
        for (Producer producer : producers) {
            producer.setState(ThreadState.STOPPED);
        }
        for (Consumer consumer : consumers) {
            consumer.setState(ThreadState.STOPPED);
        }
    }


    /**
     * Métodos auxiliares para obtener estadísticas.
     */
    public int getTotalResourceQuantity() {
        return resourceTypes.stream().mapToInt(ResourceType::getCurrentQuantity).sum();
    }

    public List<Integer[]> getResourceTypeData() {
        List<Integer[]> resourceInfo = new ArrayList<>();
        for (ResourceType resource : resourceTypes) {
            resourceInfo.add(resource.getResourceInfo());
        }
        return resourceInfo;
    }

    public List<Integer[]> getProducerData() {
        List<Integer[]> producerData = new ArrayList<>();
        for (Producer producer : producers) {
            producerData.add(producer.getProducerInfo());
        }
        return producerData;
    }

    public List<Integer[]> getConsumerData() {
        List<Integer[]> consumerData = new ArrayList<>();
        for (Consumer consumer : consumers) {
            consumerData.add(consumer.getConsumerInfo());
        }
        return consumerData;
    }

    /**
     * -4, 3 del Swing y 1 del propio main
     */
    public int getActiveThreads() {
        return (int) (Thread.activeCount() - 4);
    }

    public int getProcessingTime() {
        // cuando se de a stop calcular tiempo
        return 0;
    }

    public int getIdleThreads() {
        return numberOfProducers + numberOfConsumers - getActiveThreads();
    }
}

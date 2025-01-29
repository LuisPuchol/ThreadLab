package model;

import controller.Controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Model {
    private Producer producer;
    private Consumer consumer;
    private ResourceType resourceType;
    private Enum state; //play ended stopped
    private Integer counter;
    private Thread producerThread; // Hilo para el Producer
    private Thread consumerThread; // Hilo para el Consumer
    private ArrayList<Consumer> consumersList;
    private ArrayList<Producer> producersList;
    private ArrayList<ResourceType> resourceTypeList;
    private int consumerDelayMin;
    private int consumerDelayMax;
    private int producerDelayMin;
    private int producerDelayMax;
    private int startProducerDelayMin;
    private int startProducerDelayMax;
    private int startConsumerDelayMin;
    private int startConsumerDelayMax;

    public Model(Controller controller) {
        System.out.println("MyModel creado");
        //counter = 0;
        consumerDelayMin = 1;
        consumerDelayMax = 10;
        producerDelayMin = 0;
        producerDelayMax = 10;
        startProducerDelayMin = 1;
        startProducerDelayMax = 10;
        startConsumerDelayMin = 1;
        startConsumerDelayMax = 10;

        this.resourceTypeList = new ArrayList<>();
        this.producersList = new ArrayList<>();
        this.consumersList = new ArrayList<>();

    }

    public void getConsumerInfo() {

    }

    public void getProducerInfo() {

    }

    public void getResourceInfo() {

    }

    public void play() {
        this.resourceType = new ResourceType();
        this.producer = new Producer(this, resourceType);
        this.consumer = new Consumer(this, resourceType);

        resourceTypeList.add(resourceType);
        producersList.add(producer);
        consumersList.add(consumer);

        producerThread = new Thread(producer);
        consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error al esperar los hilos: " + e.getMessage());
        }

        // Print confirmando que finalizan
        System.out.println("Producer y Consumer han terminado");
        System.out.println(resourceType.getQuantity());
    }

    public void stop() {
        consumer.stop();
        producer.stop();
    }


    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    Random random = new Random();

    public Color generateRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}

package model;

public class ConfigurationPropertiesDTO {
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

    // Getters y Setters

    public int getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(int totalResources) {
        this.totalResources = totalResources;
    }

    public int getMaxGeneralResources() {
        return maxGeneralResources;
    }

    public void setMaxGeneralResources(int maxGeneralResources) {
        this.maxGeneralResources = maxGeneralResources;
    }

    public int getMinGeneralResources() {
        return minGeneralResources;
    }

    public void setMinGeneralResources(int minGeneralResources) {
        this.minGeneralResources = minGeneralResources;
    }

    public int getNumberOfProducers() {
        return numberOfProducers;
    }

    public void setNumberOfProducers(int numberOfProducers) {
        this.numberOfProducers = numberOfProducers;
    }

    public int getNumberOfConsumers() {
        return numberOfConsumers;
    }

    public void setNumberOfConsumers(int numberOfConsumers) {
        this.numberOfConsumers = numberOfConsumers;
    }

    public int getStartDelayMin() {
        return startDelayMin;
    }

    public void setStartDelayMin(int startDelayMin) {
        this.startDelayMin = startDelayMin;
    }

    public int getStartDelayMax() {
        return startDelayMax;
    }

    public void setStartDelayMax(int startDelayMax) {
        this.startDelayMax = startDelayMax;
    }

    public int getProducerDelayMin() {
        return producerDelayMin;
    }

    public void setProducerDelayMin(int producerDelayMin) {
        this.producerDelayMin = producerDelayMin;
    }

    public int getProducerDelayMax() {
        return producerDelayMax;
    }

    public void setProducerDelayMax(int producerDelayMax) {
        this.producerDelayMax = producerDelayMax;
    }

    public int getConsumerDelayMin() {
        return consumerDelayMin;
    }

    public void setConsumerDelayMin(int consumerDelayMin) {
        this.consumerDelayMin = consumerDelayMin;
    }

    public int getConsumerDelayMax() {
        return consumerDelayMax;
    }

    public void setConsumerDelayMax(int consumerDelayMax) {
        this.consumerDelayMax = consumerDelayMax;
    }

    // Método opcional para depuración
    @Override
    public String toString() {
        return "ConfigurationPropertiesDTO{" +
                "totalResources=" + totalResources +
                ", maxGeneralResources=" + maxGeneralResources +
                ", minGeneralResources=" + minGeneralResources +
                ", numberOfProducers=" + numberOfProducers +
                ", numberOfConsumers=" + numberOfConsumers +
                ", startDelayMin=" + startDelayMin +
                ", startDelayMax=" + startDelayMax +
                ", producerDelayMin=" + producerDelayMin +
                ", producerDelayMax=" + producerDelayMax +
                ", consumerDelayMin=" + consumerDelayMin +
                ", consumerDelayMax=" + consumerDelayMax +
                '}';
    }
}


package model;

public class ResourceType {
    private int quantity;
    private static int idCounter = 1; // Contador estático para IDs únicos
    private int resourceID;
    private int currentQuantity;
    private int minQuantity;
    private int maxQuantity;
    private int producerNum;
    private int consumerNum;
    private int overFlowQTT;
    private int underFlowQTT;
    private ResourceState state; // Estado del recurso (ACTIVE, STOPPED, etc.)

    public ResourceType(int minQuantity, int maxQuantity) {
        this.resourceID = idCounter++;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
        this.currentQuantity = 0; // Inicialmente vacío
        this.producerNum = 0; // Sin productores asociados inicialmente
        this.consumerNum = 0; // Sin consumidores asociados inicialmente
        this.overFlowQTT = 0;
        this.underFlowQTT = 0;
        this.state = ResourceState.ACTIVE; // Estado inicial del recurso\
    }

    public synchronized void addResource() {
        if (currentQuantity + 1 > maxQuantity) {
            overFlowQTT++;
            return;
        }
        currentQuantity++;
        notifyAll(); // Notifica a los consumidores
    }

    public synchronized void consumeResource() {
        if (currentQuantity - 1 < minQuantity) {
            underFlowQTT++;
            return;
        }
        currentQuantity--;
        notifyAll(); // Notifica a los productores
    }

    // Métodos para manejar productores y consumidores
    public void addProducer() {
        producerNum++;
    }

    public  void addConsumer() {
        consumerNum++;
    }

    // Método para obtener información en texto
    public Integer[] getResourceInfo() {
        return new Integer[]{
                resourceID,
                currentQuantity,
                minQuantity,
                maxQuantity,
                producerNum,
                consumerNum,
                underFlowQTT,
                overFlowQTT,
        };
    }

    // Getters

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public int getProducerNum() {
        return producerNum;
    }

    public int getConsumerNum() {
        return consumerNum;
    }

    public ResourceState getState() {
        return state;
    }

    public static void resetIdCounter() {
        idCounter = 1;
    }
}


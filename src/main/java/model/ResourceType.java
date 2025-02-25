package model;

public class ResourceType {
    private static int idCounter = 1;
    private int resourceID;
    private int currentQuantity;
    private int minQuantity;
    private int maxQuantity;
    private int producerNum;
    private int consumerNum;
    private int overFlowQTT;
    private int underFlowQTT;
    private ResourceState state;

    public ResourceType(int minQuantity, int maxQuantity) {
        this.resourceID = idCounter++;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
        this.currentQuantity = 0;
        this.producerNum = 0;
        this.consumerNum = 0;
        this.overFlowQTT = 0;
        this.underFlowQTT = 0;
        this.state = ResourceState.ACTIVE;
    }

    public synchronized void addResource() {
        if (currentQuantity + 1 > maxQuantity) {
            overFlowQTT++;
            return;
        }
        currentQuantity++;
        notifyAll();
    }

    public synchronized void consumeResource() {
        if (currentQuantity - 1 < minQuantity) {
            underFlowQTT++;
            return;
        }
        currentQuantity--;
        notifyAll();
    }

    public void addProducer() {
        producerNum++;
    }

    public  void addConsumer() {
        consumerNum++;
    }

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


    public static void resetIdCounter() {
        idCounter = 1;
    }

    public int getResourceID() {
        return resourceID;
    }

}


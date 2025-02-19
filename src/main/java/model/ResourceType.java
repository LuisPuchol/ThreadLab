package model;

public class ResourceType {
    private int quantity;
    private final int minLimit;
    private final int maxLimit;

    public ResourceType(int minLimit, int maxLimit) {
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.quantity = minLimit; // Inicializa en el límite mínimo
    }

    public synchronized void addResources() {
        if (quantity < maxLimit) {
            quantity++;
        }
    }

    public synchronized boolean consumeResource() {
        if (quantity > minLimit) {
            quantity--;
            return true;
        }
        return false;
    }

    public synchronized int getQuantity() {
        return quantity;
    }
}


package model;

public class ResourceType {
    private Integer maxQuantity;
    private Integer minQuantity;
    private Integer quantity;

    public ResourceType() {
        quantity = 0;
    }

    public void addResources(){
        quantity++;
    }

    public void removeResource(){
        quantity--;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

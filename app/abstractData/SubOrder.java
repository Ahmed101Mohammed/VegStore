package app.abstractData;

public class SubOrder {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double totalPrice;

    public SubOrder(int productId, int quantity, double totalPrice)
    {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setId(int id) {
        this.id = id;
    }
}

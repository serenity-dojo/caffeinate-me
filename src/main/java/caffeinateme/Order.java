package caffeinateme;

public class Order {

    private final long clientId;
    private final int quantity;
    private final String product;
    private OrderPriority priority;

    public Order(long clientId, int quantity, String product) {
        this.clientId = clientId;
        this.quantity = quantity;
        this.product = product;
        this.priority = OrderPriority.Normal;
    }

    public long getClientId() {
        return clientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    public OrderReceipt getReceipt() {
        return new OrderReceipt(clientId, quantity, product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                '}';
    }

    public static Order matchingReceipt(OrderReceipt orderReceipt) {
        return new Order(orderReceipt.getClientId(), orderReceipt.getQuanity(), orderReceipt.getProduct());
    }
}

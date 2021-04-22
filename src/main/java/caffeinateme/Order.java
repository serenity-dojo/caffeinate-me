package caffeinateme;

public class Order {
    private final long customerId;
    private final int quantity;
    private final String product;
    private int etaInMinutes;
    private String orderStatus;


    public Order(long customerId, int quantity, String product) {

        this.customerId = customerId;
        this.quantity = quantity;
        this.product = product;
        this.orderStatus = "Pending";
    }

    public OrderReceipt getReceipt() {
        return new OrderReceipt(customerId, quantity, product);
    }

    public long getCustomerId() {
        return customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public void completeOrder()
    {
        this.orderStatus="Complete";
    }
    public String getOrderStatus()
    {
        return orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (customerId != order.customerId) return false;
        if (quantity != order.quantity) return false;
        return product != null ? product.equals(order.product) : order.product == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                '}';
    }

    public static Order matching(OrderReceipt orderReceipt) {
        return new Order(orderReceipt.getCustomerId(), orderReceipt.getQuantity(), orderReceipt.getProduct());
    }

    public void updateETATo(int etaInMinutes) {

        this.etaInMinutes = etaInMinutes;
    }

    public Urgency getUrgency() {
        if (etaInMinutes < 5) return Urgency.Urgent;
        if (etaInMinutes <= 10) return Urgency.High;
        return Urgency.Normal;
    }
}

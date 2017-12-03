package caffeinateme;

public class OrderReceipt {
    private final long customerId;
    private final int quantity;
    private final String product;

    public OrderReceipt(long customerId, int quantity, String product) {
        this.customerId = customerId;
        this.quantity = quantity;
        this.product = product;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderReceipt that = (OrderReceipt) o;

        if (customerId != that.customerId) return false;
        if (quantity != that.quantity) return false;
        return product != null ? product.equals(that.product) : that.product == null;
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
        return "OrderReceipt{" +
                "customerId=" + customerId +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                '}';
    }
}

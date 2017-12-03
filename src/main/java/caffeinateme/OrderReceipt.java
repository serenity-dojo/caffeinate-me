package caffeinateme;

public class OrderReceipt {

    private final long clientId;
    private final int quanity;
    private final String product;

    public OrderReceipt(long clientId, int quanity, String product) {
        this.clientId = clientId;
        this.quanity = quanity;
        this.product = product;
    }

    public long getClientId() {
        return clientId;
    }

    public int getQuanity() {
        return quanity;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "an order for " + quanity + " " + product + " for client number " + clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderReceipt that = (OrderReceipt) o;

        if (clientId != that.clientId) return false;
        if (quanity != that.quanity) return false;
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + quanity;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}

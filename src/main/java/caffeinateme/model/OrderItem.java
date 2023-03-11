package caffeinateme.model;

public class OrderItem {
    private final int quantity;
    private  final String product;

    public OrderItem(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

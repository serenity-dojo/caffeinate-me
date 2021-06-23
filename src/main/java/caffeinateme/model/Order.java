package caffeinateme.model;

import java.util.Objects;

public class Order {
    private final int quantity;
    private final String product;
    private final Customer customer;
    private final OrderStatus status;

    public Order(int quantity, String product, Customer customer) {
        this(quantity,product, customer, OrderStatus.Normal);
    }

    public Order(int quantity, String product, Customer customer, OrderStatus status) {
        this.quantity = quantity;
        this.product = product;
        this.customer = customer;
        this.status = status;
    }

    public Order withStatus(OrderStatus status) {
        return new Order(quantity,product,customer, status);
    }
    public OrderStatus getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static OrderBuilder of(int quantity, String product) {
        return new OrderBuilder(quantity, product);
    }

    public static class OrderBuilder {

        private final int quantity;
        private final String product;

        public OrderBuilder(int quantity, String product) {
            this.quantity = quantity;
            this.product = product;
        }

        public Order forCustomer(Customer customerName) {
            return new Order(quantity, product, customerName);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity &&
                Objects.equals(product, order.product) &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, product, customer);
    }
}

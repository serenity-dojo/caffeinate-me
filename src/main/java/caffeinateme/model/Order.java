package caffeinateme.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private final int quantity;
    private final String comment;
    private final String product;
    private final Customer customer;
    private final List<OrderItem> items;
    private final OrderStatus status;

    public Order(int quantity, String product, Customer customer) {
        this(quantity,product, customer, OrderStatus.Normal, "");
    }

    public Order(int quantity, String product, Customer customer, String comment) {
        this(quantity,product, customer, OrderStatus.Normal, comment);
    }

    public Order(int quantity, String product, Customer customer, OrderStatus status, String comment) {
        this.quantity = quantity;
        this.product = product;
        this.items = null;
        this.customer = customer;
        this.status = status;
        this.comment = comment;
    }

    public Order(List<OrderItem> items, Customer customer) {
        this(items, customer, OrderStatus.Normal, "");
    }

    public Order(List<OrderItem> items, Customer customer, OrderStatus status, String comment) {

        this.quantity = 0;
        this.product = null;
        this.items = items;
        this.customer = customer;
        this.status = status;
        this.comment = comment;

    }

    public Order withComment(String comment) {
        return new Order(quantity, product, customer, status, comment);
    }

    public Order withStatus(OrderStatus status) {
        return new Order(quantity,product,customer, status, comment);
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

    public String getComment() { return comment; }

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

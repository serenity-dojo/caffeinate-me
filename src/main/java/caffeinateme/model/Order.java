package caffeinateme.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private final int quantity;
    private final String comment;
    private final String product;
    private final Customer customer;
    private final OrderStatus status;
    private static List<OrderItem> orderItems;

    public Order(int quantity, String product, Customer customer, List<OrderItem> orderItems) {
        this(quantity,product, customer, OrderStatus.Normal, "", orderItems);
    }

    public Order(int quantity, String product, Customer customer, String comment, List<OrderItem> orderItems) {
        this(quantity,product, customer, OrderStatus.Normal, comment, orderItems);
    }

    public Order(int quantity, String product, Customer customer, OrderStatus status, String comment, List<OrderItem> orderItems) {
        this.quantity = quantity;
        this.product = product;
        this.customer = customer;
        this.status = status;
        this.comment = comment;
        Order.orderItems = orderItems;
    }

    public Order(List<OrderItem> orderItems, Customer customer, int quantity, String product, OrderStatus status, String comment) {
        this.quantity = quantity;
        this.product = product;
        this.customer = customer;
        this.status = status;
        this.comment = comment;
        Order.orderItems = orderItems;
    }

    public Order(List<OrderItem> orderItems, Customer customer) {
        this.orderItems = orderItems;
        this.customer = customer;
    }


    public Order withComment(String comment) {
        return new Order(quantity, product, customer, status, comment, orderItems);
    }

    public Order withStatus(OrderStatus status) {
        return new Order(quantity,product,customer, status, comment, orderItems);
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

    public String getComment()
    {
        return comment;
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
            return new Order(quantity, product, customerName, orderItems);
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

package caffeinateme.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private final int quantity;
    private final String comment;
    private final String product;
    private final Customer customer;
    private final OrderStatus status;
    private final List<OrderItem> orderItems;

    public Order(int quantity, String comment, String product, Customer customer, OrderStatus status, List<OrderItem> orderItems) {
        this.quantity = quantity;
        this.comment = comment;
        this.product = product;
        this.customer = customer;
        this.status = status;
        this.orderItems = orderItems;
    }

    public Order withComment(String comment) {
        return new Order(quantity,
                comment,
                product,
                customer,
                status,
                orderItems);
    }

    public Order withStatus(OrderStatus status) {
        return new Order(quantity,
                comment,
                product,
                customer,
                status,
                orderItems);
    }

    public Order withOrderList(List<OrderItem> orderItems) {
        return new Order(quantity,
                comment,
                product,
                customer,
                status,
                orderItems);
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
        return "Double sugar";
    }

    public Customer getCustomer() {
        return customer;
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

    public Order withCustomer(Customer customer) {
        return new Order(quantity,
                comment,
                product,
                customer,
                status,
                orderItems);
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }


    public static class OrderBuilder {
        private int quantity;
        private String comment;
        private String product;
        private Customer customer;
        private OrderStatus status;
        private List<OrderItem> orderItems;

        public OrderBuilder() {
        }

        public OrderBuilder(Order order) {
            this.quantity = order.quantity;
            this.comment = order.comment;
            this.product = order.product;
            this.customer = order.customer;
            this.status = order.status;
            this.orderItems = order.orderItems;
        }

        public OrderBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public OrderBuilder setProduct(String product) {
            this.product = product;
            return this;
        }

        public OrderBuilder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public OrderBuilder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderBuilder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Order build() {
            return new Order(quantity, comment, product, customer, status, orderItems);
        }
    }
}

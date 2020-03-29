package caffeinateme.model;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CoffeeShop {

    private Queue<Order> orders = new LinkedList<>();
    private final ProductCatalog productCatalog;

    public CoffeeShop(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public void placeOrder(Order order, float distanceInMetres) {
        if (distanceInMetres <= 200) {
            order = order.withStatus(OrderStatus.Urgent);
        }
        orders.add(order);
    }

    public List<Order> getPendingOrders() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> getOrderFor(Customer customer) {
        return orders.stream()
                .filter( order -> order.getCustomer().equals(customer))
                .findFirst();
    }

    public void setCustomerETA(Customer customer, int etaInMinutes) {
        getOrderFor(customer).ifPresent(
                order -> {
                    if (etaInMinutes < 5) {
                        order.setStatus(OrderStatus.Urgent);
                    } else if (etaInMinutes > 10) {
                        order.setStatus(OrderStatus.Normal);
                    } else {
                        order.setStatus(OrderStatus.High);
                    }
                }
        );
    }

    public Receipt getReceiptFor(Customer customer) {
        List<Order> customerOrders = orders.stream()
                .filter( order -> order.getCustomer().equals(customer))
                .collect(Collectors.toList());

        double subTotal = customerOrders.stream()
                .mapToDouble(this::subtotalFor)
                .sum();

        List<ReceiptLineItem> lineItems = customerOrders.stream()
                .map(order -> new ReceiptLineItem(order.getProduct(), order.getQuantity(), subtotalFor(order)))
                .collect(Collectors.toList());

        double serviceFee = subTotal * 5 / 100;
        double total = subTotal + serviceFee;
        return new Receipt(roundedTo2DecimalPlaces(subTotal),
                roundedTo2DecimalPlaces(serviceFee),
                roundedTo2DecimalPlaces(total),
                lineItems);
    }

    private double roundedTo2DecimalPlaces(double value) {
        return new BigDecimal(Double.toString(value)).setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private double subtotalFor(Order order) {
        return productCatalog.priceOf(order.getProduct()) * order.getQuantity();
    }

}

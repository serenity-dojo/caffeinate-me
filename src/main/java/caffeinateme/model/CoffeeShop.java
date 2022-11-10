package caffeinateme.model;

import java.util.*;

public class CoffeeShop {

    private static final int MAX_DISTANCE = 10000;
    private Queue<Order> orders = new LinkedList<>();
    private Map<String, Customer> registeredCustomers = new HashMap<>();

    public void placeOrder(Order order) {
        placeOrder(order, MAX_DISTANCE);
    }

    public void placeOrder(Order order, int distanceInMetres) {
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

    public Customer registerNewCustomer(String customerName) {
        Customer newCustomer = Customer.named(customerName);
        registeredCustomers.put(customerName, newCustomer);
        return newCustomer;
    }
}

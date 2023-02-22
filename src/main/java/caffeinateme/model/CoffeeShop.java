package caffeinateme.model;

import java.util.*;

public class CoffeeShop {

    private Queue<Order> orders = new LinkedList<>();
    private Map<String, Customer> registeredCustomers = new HashMap<>();

    public void placeOrder(Order order, int distanceInMetres) {
        if (distanceInMetres <= 200) {
            order = order.withStatus(OrderStatus.Urgent);
        }
        orders.add(order);
    }

    public String getPendingOrders() {

        List<String> order = new ArrayList<>();
        order.add("cappuccino");
        return order;
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

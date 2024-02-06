package caffeinateme.model;

import java.util.*;

public class CoffeeShop {

    private Queue<Order> orders = new LinkedList<>();
    private List<Customer> customers = new ArrayList<>();

    public void placeOrder(Order order) {
        int DEFAULT_DISTANCE = 300;
        placeOrder(order, DEFAULT_DISTANCE);
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

        Customer newCustomer = new Customer(customerName);
        customers.add(newCustomer);
        return newCustomer;
    }
}

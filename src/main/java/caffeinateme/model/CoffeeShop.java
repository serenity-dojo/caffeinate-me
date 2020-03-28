package caffeinateme.model;

import java.util.*;

public class CoffeeShop {

    private Queue<Order> orders = new LinkedList<>();

    public void placeOrder(Order order, int distanceInMetres) {
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
}

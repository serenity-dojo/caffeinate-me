package caffeinateme.model;

import io.cucumber.cucumberexpressions.ParameterType;

import java.util.*;

public class CoffeeShop {

    private Queue<Order> orders = new LinkedList<>();


    public void placeOrder(Order order, Float distanceInMetres) {
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
}

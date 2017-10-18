package caffeinateme.steps;

import caffeinateme.Order;
import caffeinateme.OrderReceipt;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrdersClient {

    List<Order> orders = new ArrayList<>();

    @Step("Place order for customer {0} for {1} x {2}")
    public OrderReceipt placeOrder(long customerId, int quantity, String product) {
        Order order = new Order(customerId, quantity, product);
        orders.add(order);
        return order.getReceipt();
    }

    @Step
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    @Step("Notify updated ETA for customer {0} who is {1} minutes away from the shop")
    public void updateCustomerEta(long customerId, int minutesAway) {
        orders.stream().filter( order -> order.getCustomerId() == customerId)
                       .forEach( order -> order.updateETATo(minutesAway));
    }
}

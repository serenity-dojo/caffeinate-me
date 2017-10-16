package caffeinateme.steps;

import caffeinateme.Order;
import caffeinateme.OrderReceipt;
import caffeinateme.OrderPriority;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrdersClient {

    private List<Order> pendingOrders = new ArrayList<>();

    @Step("Place an order for user {0} for {1} {2}")
    public OrderReceipt placeOrder(long clientId, int quantity, String order) {
        Order newOrder = new Order(clientId, quantity, order);
        pendingOrders.add(newOrder);
        return newOrder.getReceipt();
    }

    public List<Order> getPendingOrders() {
        return new ArrayList<>(pendingOrders);
    }

    @Step("Update ETA for client number {0} to {1} minutes")
    public void updateETAForCustomer(long clientId, int etaInMinutes) {
//        OrderReceipt clientOrderReceipt = pendingOrders.stream()
//                .filter(order -> order.getClientId() == clientId)
//                .findFirst()
//                .get();
//
//        clientOrderReceipt.updateETATo(etaInMinutes);
    }
}

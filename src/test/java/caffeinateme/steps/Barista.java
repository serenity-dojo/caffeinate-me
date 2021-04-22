package caffeinateme.steps;

import caffeinateme.Order;
import caffeinateme.OrderReceipt;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.stream.Collectors;

public class Barista extends ScenarioActor {

    @Steps(shared = true)
    CoffeeOrdersClient coffeeOrders;

    public List<Order> pendingOrders() {
        return coffeeOrders.getOrders().stream()
                .filter(order -> order.getOrderStatus() != "Complete")
                .collect(Collectors.toList());
    }

    public void completeOrder(OrderReceipt orderReceipt) {
        coffeeOrders.getOrders().stream().filter(order -> order.getCustomerId()==orderReceipt.getCustomerId())
                .forEach(order ->order.completeOrder());
                       // coffeeOrders.getOrders().stream().filter(order -> order.getCustomerId()==orderReceipt.getCustomerId())
                //.forEach(order -> coffeeOrders.setOrderHistory(order));
        //coffeeOrders.getOrders().removeIf(order -> order.getCustomerId() == orderReceipt.getCustomerId());
    }
}

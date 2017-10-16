package caffeinateme.steps;

import caffeinateme.Order;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class Barista extends ScenarioActor {

    @Steps(shared = true)
    CoffeeOrdersClient coffeeOrders;

    public List<Order> pendingOrders() {
        return coffeeOrders.getOrders();
    }
}

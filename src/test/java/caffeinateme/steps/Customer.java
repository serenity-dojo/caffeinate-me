package caffeinateme.steps;

import caffeinateme.OrderReceipt;
import caffeinateme.Receipt;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class Customer extends ScenarioActor {

    private long customerId;

    @Steps(shared = true)
    CoffeeOrdersClient coffeeOrders;

    public void hasACustomerIdOf(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }

    @Step("#actor places an order for {0} {1}")
    public OrderReceipt placesAnOrderFor(int quantity, String product) {
        return coffeeOrders.placeOrder(customerId, quantity, product);
    }

    @Step("#actor updates her ETA to {0}")
    public void updatesHerETATo(int minutesAway) {
        coffeeOrders.updateCustomerEta(customerId, minutesAway);
    }

    @Step("#actor requests a receipt")
    public Receipt requestsAReceipt() {
        return coffeeOrders.getReceiptFor(customerId);
    }
}

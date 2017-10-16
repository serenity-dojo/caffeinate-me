package caffeinateme.steps;

import caffeinateme.OrderReceipt;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class Customer extends ScenarioActor {

    long id;

    public void hasACustomerIdOf(long id) {
        this.id = id;
    }

    @Steps(shared = true)
    CoffeeOrdersClient coffeeOrders;

    @Step("#actor places an order for {0} {1}")
    public OrderReceipt placesOrderFor(int quantity, String order) {
        return coffeeOrders.placeOrder(id, quantity, order);
    }

    @Step("#actor's device updates the ETA to {0} minutes")
    public void updatesHerETA(int etaInMinutes) {
        coffeeOrders.updateETAForCustomer(id, etaInMinutes);
    }

    public long getId() { return id; }

    @Override
    public String toString() {
        return getActorName();
    }
}

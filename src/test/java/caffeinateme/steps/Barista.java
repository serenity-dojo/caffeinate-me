package caffeinateme.steps;

import caffeinateme.Order;
import caffeinateme.OrderReceipt;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Condition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class Barista extends ScenarioActor {

    @Steps(shared = true)
    CoffeeOrdersClient coffeeOrders;

    @Step
    public void shouldHaveAnOrderFor(OrderReceipt placedOrderReceipt) {
//        Optional<Order> matchingOrder = coffeeOrders.getPendingOrders().stream()
//                                                    .filter(order -> order.getReceipt().equals(placedOrderReceipt))
//                                                    .findAny();
//
//        assertThat(matchingOrder).isPresent();

        assertThat(coffeeOrders.getPendingOrders()).contains(Order.matchingReceipt(placedOrderReceipt));
    }

    @Step("#actor views the pending orders")
    public List<Order> viewsOrders() {
        return coffeeOrders.getPendingOrders();
    }

}

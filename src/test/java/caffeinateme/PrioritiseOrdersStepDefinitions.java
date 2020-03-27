package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PrioritiseOrdersStepDefinitions {

    @Steps(shared=true)
    Customer sarah;

    @Steps
    Barista barry;

    @Given("^Sarah is (\\d+) minutes away from the shop")
    public void notifyETA(int minutesAway) {
        sarah.updatesHerETATo(minutesAway);
    }

    List<Order> pendingOrders;

    @When("^Barry reviews the pending orders$")
    public void barryReviewsThePendingOrders() throws Throwable {
        pendingOrders = barry.pendingOrders();
    }

    @Then("^Sarah's order should have an urgency of (.*)$")
    public void sarahsOrderShouldHaveAnUrgencyOf(Urgency urgency) throws Throwable {
        Optional<Order> sarahsOrder = sarahsOrderIn(pendingOrders);

        assertThat(sarahsOrder).isPresent();

        assertThat(sarahsOrder.get().getUrgency()).isEqualTo(urgency);

    }

    private Optional<Order> sarahsOrderIn(List<Order> pendingOrders) {

        OrderReceipt orderReceipt = Serenity.sessionVariableCalled("orderReceipt");

        return pendingOrders.stream()
                            .filter( order -> order.equals(Order.matching(orderReceipt)))
                            .findFirst();
    }
}

package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PrioritisingOrderSteps {

    @Steps
    Barista barry;

    @Steps
    Customer sarah;

    @Given("^(?:.*) has ordered (\\d+) (.*)$")
    public void sarah_has_ordered(int quantity, String order) throws Throwable {
        sarah.placesOrderFor(quantity, order);
    }

    @Given("^(?:.*) is (\\d+) minutes away from the shop$")
    public void sarah_is_minutes_away_from_the_shop(int etaInMinutes) throws Throwable {
        sarah.updatesHerETA(etaInMinutes);
    }

    List<Order> ordersViewedByBarry;

    @When("(?:.*) reviews the pending orders$")
    public void barry_reviews_the_pending_orders() throws Throwable {
        ordersViewedByBarry = barry.viewsOrders();
    }

    @Then("^(?:.*)'s order should have an priority of (.*)")
    public void sarah_s_order_should_have_an_priority_of(OrderPriority expectedPriority) throws Throwable {

        Optional<OrderPriority> sarahsOrderPriority = ordersViewedByBarry.stream()
                .filter(order -> order.getClientId() == sarah.getId())
                .map(order -> order.getPriority())
                .findFirst();

        assertThat(sarahsOrderPriority).isPresent();
        assertThat(sarahsOrderPriority.get()).isEqualTo(expectedPriority);
    }
}

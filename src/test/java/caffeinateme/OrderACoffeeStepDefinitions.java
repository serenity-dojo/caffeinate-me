package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.UserRegistrationClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderACoffeeStepDefinitions {

    @Steps(shared = true)
    Customer customer;

    @Steps
    Barista barry;

    OrderReceipt orderReceipt;

    @When("^(?:.*) (?:orders|has ordered) an? (.*)$")
    public void sheOrdersA(String order) throws Throwable {
        orderReceipt = customer.placesAnOrderFor(1, order);

        Serenity.setSessionVariable("orderReceipt").to(orderReceipt);
    }

    @Then("^Barry should receive the order$")
    public void barryShouldReceiveTheOrder() throws Throwable {
        assertThat(barry.pendingOrders()).contains(Order.matching(orderReceipt));
    }
}

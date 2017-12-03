package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.UserRegistrationClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderACoffeeStepDefinitions {

    @Steps
    UserRegistrationClient userRegistrations;

    @Steps
    Customer cathy;

    @Steps
    Barista barry;

    @Given("Cathy has a Caffeinate-Me account")
    public void userHasACaffeinateMeAccount() {
        userRegistrations.registerUser(cathy);
    }

    OrderReceipt orderReceipt;

    @When("^s?he orders a (.*)$")
    public void sheOrdersA(String order) throws Throwable {
        orderReceipt = cathy.placesAnOrderFor(1, order);
    }

    @Then("^Barry should receive the order$")
    public void barryShouldReceiveTheOrder() throws Throwable {
        assertThat(barry.pendingOrders()).contains(Order.matching(orderReceipt));
    }
}

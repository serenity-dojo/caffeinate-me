package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.CustomerRegistrationClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;


public class OrderCoffeeSteps {

    @Steps
    Customer customer;

    @Steps
    Barista barry;

    OrderReceipt orderReceipt;

    @Steps(shared = true)
    CustomerRegistrationClient customerRegistrations;

    @Given("(.*) has a Caffeinate-Me account")
    public void hasACaffeinateMeAccount(String name) {
        customer.isCalled(name);
        customerRegistrations.registerCustomer(customer);
    }

    @When("s?he (?:orders|has ordered) a (.*)$")
    public void orders_a(String order) throws Throwable {
        orderReceipt = customer.placesOrderFor(1, order);
    }

    @Then("Barry should receive the order$")
    public void barry_should_receive_the_order() throws Throwable {
        barry.shouldHaveAnOrderFor(orderReceipt);
    }
}
